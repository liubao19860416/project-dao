package com.saike.grape.dao.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.saike.grape.dao.entity.basic.BaseEntity;
import com.saike.grape.dao.utils.DAODatetimeUtils;
import com.saike.grape.dao.utils.DAOUtils;

/**
 * 基础通用的DAO接口，抽象实现基类
 * 
 * GenericDAOBatisImpl的实现子类型
 */
public abstract class GenericDAOBatisImpl<E extends BaseEntity, T extends GenericDAOBatisImpl<E, T>>
        extends SqlSessionDaoSupport implements GenericDAO<E> {

    private static final Map<Class<?>, Logger> loggersMap = new ConcurrentHashMap<>();

    protected static final String TABLE_NAME_PREFIX = "t_";
    private static final String NAME_SPACE_SUFFIX = "Mapper";

    protected static final String VAR_TABLE_NAME = "table_name";

    protected static final int SUCCESS = 1;
    protected static final int FAIL = -1;//原来为0

    private static final int PAGESIZE_MAX = 500;

    private static final int BATCH_SIZE = 100;

    private static final Pattern P_DAOIMPL_NAME = Pattern.compile("^(.+)DAOImpl$");

    @SuppressWarnings("rawtypes")
    private static final Map<Class, String> NAME_SPACE_MAP = new ConcurrentHashMap<>();

    @SuppressWarnings("rawtypes")
    private static final Map<Class, String> TABLE_NAME_MAP = new ConcurrentHashMap<>();

    protected Class<E> entityClass;
    protected Class<T> daoImplClass;

    private final ObjectFactory objectFactory = new DefaultObjectFactory();

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @SuppressWarnings("unchecked")
    public GenericDAOBatisImpl() {
        Type[] types = ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments();
        this.entityClass = (Class<E>) types[0];
        this.daoImplClass = (Class<T>) types[1];
    }

    @PostConstruct
    public void init() {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public final List<E> selectAll() {
        Map<String, Object> params=new HashMap<String, Object>();
        params.put(VAR_TABLE_NAME,this.getTableName());
        return selectList("selectAll",params);
    }

    @Override
    public final E findById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put(VAR_TABLE_NAME, getTableName());
        params.put("id", id);
        return genericSelectOne("findById", params);
    }

    @Override
    public final E findByCode(String code) {
        Map<String, Object> params = new HashMap<>();
        params.put(VAR_TABLE_NAME, getTableName());
        params.put("code", code);
        return genericSelectOne("findByCode", params);
    }

    //TODO 缺少mapper文件的sql语句
    @Override
    public final List<E> findByCode(List<String> codes) {
        if (codes == null || codes.isEmpty()) {
            return new ArrayList<E>();
        }
        return this.selectList(
                GenericDAOBatisImpl.class,
                "findByCodeList",
                newParams().put(VAR_TABLE_NAME, getTableName()).put("codes",
                        codes));
    }

    @Override
    public final int insert(E entity) {
        if (entity == null) {
            return FAIL;
        }
        List<E> entities = new ArrayList<>();
        entities.add(entity);
        insert(entities);
        return SUCCESS;
    }

    //TODO 缺少mapper文件的sql语句
    @Override
    public final void insert(List<E> entities) {
        genericInsert(GenericDAOBatisImpl.class, entities);
    }

    protected void genericInsert(List<? extends BaseEntity> entities) {
        genericInsert(GenericDAOBatisImpl.class, entities);
    }

    @SuppressWarnings({ "rawtypes" })
    protected void genericInsert(
            Class<? extends GenericDAOBatisImpl> daoImplclass,
            List<? extends BaseEntity> entities) {
        if (entities == null || entities.size() <= 0) {
            return;
        }
        Map<String, Object> params = new HashMap<>();
        params.put(VAR_TABLE_NAME, getTableName());
        List<List<?>> valuesList = new ArrayList<>();
      
        try {
            Timestamp currentTimestamp = DAODatetimeUtils.currentTimestamp();
            for (int i = 0; i < entities.size(); i++) {
                BaseEntity e = entities.get(i);
                if (e.getCreatedDatetime() == null) {
                    e.setCreatedDatetime(currentTimestamp);
                }
                if (e.getUpdatedDatetime() == null) {
                    e.setUpdatedDatetime(currentTimestamp);
                }
                Map<String, List<?>> mapFieldsAndValues = DAOUtils.describe(e);
                if (i == 0) {
                    params.put("fields", mapFieldsAndValues.get("fields"));
                }
                valuesList.add((List<?>) mapFieldsAndValues.get("values"));
            }

            //执行批量插入操作
            List<List<?>> tempValuesList = new ArrayList<>();
            for (List<?> list : valuesList) {
                tempValuesList.add(list);
                if (tempValuesList.size() % BATCH_SIZE == 0) {
                    params.put("values", tempValuesList);
                    getSqlSession().insert(
                            getNameSpace(daoImplclass, "batchInsert"), params);
                    tempValuesList.clear();
                }
            }

            //最后一次不足BATCH_SIZE=100的数据进行插入操作
            if (tempValuesList.size() > 0) {
                params.put("values", tempValuesList);
                getSqlSession().insert(
                        getNameSpace(daoImplclass, "batchInsert"), params);
            }
        } catch (Exception ex) {
            getLogger().error(
                    "insert entities failed with exception: "
                            + entities.get(0).getClass().getName(), ex);

            throw new RuntimeException(ex);
        }
    }

    protected int insert(String operate, E e) {
        Timestamp currentTimeStamp = DAODatetimeUtils.currentTimestamp();
        e.setCreatedDatetime(currentTimeStamp);
        e.setUpdatedDatetime(currentTimeStamp);
        return getSqlSession().insert(getNameSpace(operate), e);
    }

    protected int insert(String operate, Object param) {
        return getSqlSession().insert(getNameSpace(operate), param);
    }

    @Override
    public int update(E entity) {
        entity.setUpdatedDatetime(DAODatetimeUtils.currentTimestamp());
        return getSqlSession().update(getNameSpace("update"), entity);
    }
    
    @Override
    public void update(List<E> entities) {
        if (entities != null && entities.size() > 0) {
            getSqlSession().update(getNameSpace("batchUpdate"), entities);
        }
    }
    
    protected int update(String operate, E entity) {
        entity.setUpdatedDatetime(DAODatetimeUtils.currentTimestamp());
        return update(operate, (Object) entity);
    }

    protected int update(String operate, Object param) {
        return getSqlSession().update(getNameSpace(operate), param);
    }

    protected void update(String operate, List<E> entities) {
        update(operate, (Object) entities);
    }

    protected void update(Map<String, Object> map, String whereCondition) {
        if (map != null) {
            map.put("updatedDatetime", DAODatetimeUtils.currentTimestamp());
            List<String> fields = new ArrayList<>();
            List<Object> values = new ArrayList<>();
            for (String key : map.keySet()) {
                fields.add(key);
                values.add(map.get(key));
            }
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put(VAR_TABLE_NAME, getTableName());
            parameterMap.put("fields", fields);
            parameterMap.put("values", values);
            parameterMap.put("conditions", whereCondition);
            getSqlSession().update(
                    getNameSpace(GenericDAOBatisImpl.class, "updateWithMap"),
                    parameterMap);
        }
    }

    protected int updateByCode(Map<String, Object> map, String code) {
        if (map != null) {
            map.put("updatedDatetime", DAODatetimeUtils.currentTimestamp());
            List<String> fields = new ArrayList<>();
            List<Object> values = new ArrayList<>();
            for (String key : map.keySet()) {
                fields.add(key);
                values.add(map.get(key));
            }
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put(VAR_TABLE_NAME, getTableName());
            parameterMap.put("fields", fields);
            parameterMap.put("values", values);
            parameterMap.put("code", code);
            return getSqlSession().update(
                    getNameSpace(GenericDAOBatisImpl.class, "updateByCode"),
                    parameterMap);
        }
        return 0;
    }

    @Override
    public final int deleteById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put(VAR_TABLE_NAME, getTableName());
        params.put("id", id);
        return getSqlSession().update(
                getNameSpace(GenericDAOBatisImpl.class, "deleteById"), params);
    }

    @Override
    public final int deleteByCode(String code) {
        Map<String, Object> params = new HashMap<>();
        params.put(VAR_TABLE_NAME, getTableName());
        params.put("code", code);
        return getSqlSession()
                .update(getNameSpace(GenericDAOBatisImpl.class, "deleteByCode"),
                        params);
    }

    protected String getCodeStr(String code, String operate) {
        return getSqlSession().selectOne(
                getNameSpace(this.daoImplClass, operate), code);
    }

    @Override
    public final int delete(E entity) {
        return deleteById(entity.getId());
    }

    @Override
    public final void delete(List<E> entities) {
        List<Long> ids = new ArrayList<>();
        if (entities != null && entities.size() > 0) {
            for (E e : entities) {
                ids.add(e.getId());
            }
        }

        if (ids.size() > 0) {
            Map<String, Object> params = new HashMap<>();
            params.put(VAR_TABLE_NAME, getTableName());
            params.put("ids", ids);
            getSqlSession()
                    .update(getNameSpace(GenericDAOBatisImpl.class,
                            "batchDeleteByIds"), params);
        }

    }

    public String getNameSpace(String operate) {
        return getNameSpace(daoImplClass, operate);
    }

    @SuppressWarnings("rawtypes")
    public static String getNameSpace(Class daoImplClass, String operate) {
        String nameSpace = NAME_SPACE_MAP.get(daoImplClass);
        if (nameSpace == null || "".equals(nameSpace)) {
            nameSpace = daoImplClass.getName() + NAME_SPACE_SUFFIX;
            NAME_SPACE_MAP.put(daoImplClass, nameSpace);
        }
        return nameSpace + "." + operate;
    }

    public String getTableName() {
        return getTableName(this.daoImplClass);
    }

    @SuppressWarnings("rawtypes")
    public static String getTableName(Class daoImplClass) {
        String tableName = TABLE_NAME_MAP.get(daoImplClass);
        if (tableName != null && !"".equals(tableName)) {
            return tableName;
        }
        StringBuilder sbd = new StringBuilder();
        Matcher m = P_DAOIMPL_NAME.matcher(daoImplClass.getSimpleName());
        if (!m.matches()) {
            throw new IllegalArgumentException(daoImplClass.getSimpleName()
                    + " is illegal DAOImpl class!!");
        }
        for (char c : m.group(1).toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                if (sbd.length() > 0) {
                    sbd.append('_');
                }
                sbd.append(String.valueOf(c).toLowerCase());
            } else {
                sbd.append(c);
            }
        }
        tableName = TABLE_NAME_PREFIX + sbd.toString();
        TABLE_NAME_MAP.put(daoImplClass, tableName);
        return tableName;
    }

    protected Logger getLogger() {
        return getLogger(this.daoImplClass);
    }

    private static Logger getLogger(Class<?> clazz) {
        Logger logger = loggersMap.get(clazz);
        if (logger == null) {
            logger = LoggerFactory.getLogger(clazz);
            loggersMap.put(clazz, logger);
        }
        return logger;
    }

    protected List<E> selectList(String operate) {
        return selectList(operate, null);
    }

    protected List<E> selectList(String operate, Object param) {
        return selectList(this.daoImplClass, operate, param);
    }

    protected List<E> selectList(Class<?> clzss, String operate, Object param) {
        return getSqlSession().selectList(getNameSpace(clzss, operate), param);
    }

    protected E selectOne(Class<?> clzss, String operate, Object param) {
        return getSqlSession().selectOne(getNameSpace(clzss, operate), param);
    }

    protected List<E> selectList(String operate, int pageIndex, int pageSize) {
        return selectList(operate, null, pageIndex, pageSize);
    }

    protected List<E> selectList(String operate, Object param, int pageIndex,
            int pageSize) {
        return selectList(this.daoImplClass, operate, param, pageIndex,
                pageSize);
    }

    protected List<Map> selectMapList(String operate, Object param,
            int pageIndex, int pageSize) {
        return getSqlSession().selectList(
                getNameSpace(this.daoImplClass, operate), param,
                makeRowBounds(pageIndex, pageSize));
    }

    protected List<Map> selectMapList(String operate, Object param) {
        return getSqlSession().selectList(
                getNameSpace(this.daoImplClass, operate), param);
    }

    protected List<E> selectList(Class<?> clzss, String operate, Object param,
            int pageIndex, int pageSize) {
        return getSqlSession().selectList(getNameSpace(clzss, operate), param,
                makeRowBounds(pageIndex, pageSize));
    }

    protected E selectOne(String operate) {
        return selectOne(operate, null);
    }

    @SuppressWarnings("unchecked")
    protected E selectOne(String operate, Object param) {
        return (E) getSqlSession().selectOne(getNameSpace(operate), param);
    }

    // 查询记录总条数
    protected Long selectCount(String operate, Object param) {
        return (Long) getSqlSession().selectOne(getNameSpace(operate), param);
    }

    protected int selectCount(Class<?> clzss, String operate, Object param) {
        return getSqlSession().selectOne(getNameSpace(clzss, operate), param);
    }

    protected E genericSelectOne(String operate, Object param) {
        GenericResultHandler resultHandler = new GenericResultHandler();
        getSqlSession().select(
                getNameSpace(GenericDAOBatisImpl.class, operate), param,
                resultHandler);
        return resultHandler.getResult();
    }

    protected RowBounds makeRowBounds(int pageIndex, int pageSize) {
        pageIndex = pageIndex < 1 ? 1 : pageIndex;
        pageSize = (pageSize <= 0 || pageSize > PAGESIZE_MAX) ? PAGESIZE_MAX
                : pageSize;
        return new RowBounds((pageIndex - 1) * pageSize, pageSize);
    }

    /*
     * internal ResultHandler class
     */
    protected class GenericResultHandler implements ResultHandler {
        private E entity;
        @Override
        public void handleResult(ResultContext context) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) context
                    .getResultObject();
            if (map != null) {
                entity = objectFactory
                        .create(GenericDAOBatisImpl.this.entityClass);
                try {
                    DAOUtils.populate(entity, map);
                } catch (Exception ex) {
                    getLogger().error(
                            "GenericResultHandler.handleResult exception", ex);
                }
            }
        }

        public E getResult() {
            return entity;
        }

    }

    /**
     * 按传入的codeList批量删除商品信息，注意，此方法为物理删除，不可恢复
     */
    @Override
    public int deletePhysicalByCodeList(List<String> codeList) {
        Map<String, Object> params = new HashMap<>();
        params.put(VAR_TABLE_NAME, getTableName());
        params.put("codeList", codeList);
        return getSqlSession().delete(
                getNameSpace(GenericDAOBatisImpl.class,
                        "deletePhysicalByCodeList"), params);
    }

    /**
     * 按传入的idList批量删除商品信息，注意，此方法为物理删除，不可恢复
     */
    @Override
    public int deletePhysicalByIdList(List<String> idList) {
        Map<String, Object> params = new HashMap<>();
        params.put(VAR_TABLE_NAME, getTableName());
        params.put("idList", idList);
        return getSqlSession().delete(
                getNameSpace(GenericDAOBatisImpl.class,
                        "deletePhysicalByIdList"), params);
    }

    /**
     * 按传入的idList批量删除商品信息，逻辑删除，并不真正删除数据
     */
    @Override
    public int deleteById(List<String> idList) {
        Map<String, Object> params = new HashMap<>();
        params.put(VAR_TABLE_NAME, getTableName());
        params.put("idList", idList);
        return getSqlSession().delete(
                getNameSpace(GenericDAOBatisImpl.class, "deleteByIdList"),
                params);
    }

    /**
     * 按传入的codeList批量删除商品信息，逻辑删除，并不真正删除数据
     */
    @Override
    public int deleteByCode(List<String> codeList) {
        Map<String, Object> params = new HashMap<>();
        params.put(VAR_TABLE_NAME, getTableName());
        params.put("codeList", codeList);
        return getSqlSession().delete(
                getNameSpace(GenericDAOBatisImpl.class, "deleteByCodeList"),
                params);
    }

    protected Params newParams() {
        return new Params();
    }

    protected class Params extends HashMap<String, Object> {
        private static final long serialVersionUID = 5944659754462710346L;
        @Override
        public Params put(String key, Object value) {
            super.put(key, value);
            return this;
        }
    }

}
