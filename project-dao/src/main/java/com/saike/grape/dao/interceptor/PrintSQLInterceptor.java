package com.saike.grape.dao.interceptor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saike.grape.dao.utils.DAODatetimeUtils;

/**
 * 拦截所有SQL ,当执行出错时把完整SQL打出来
 * 
 * @Signature，即拦截点。
 * 第一个@Signature我们定义了该Interceptor将拦截Executor
 * 中参数类型为MappedStatement和Object的update方法；
 * 第二个@Signature我们定义了该Interceptor将拦截Executor接口中参数类型为MappedStatement、
 * Object、RowBounds和ResultHandler的query方法；
 * 
 */

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {
                MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query", args = {
                MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class }) })
public class PrintSQLInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(PrintSQLInterceptor.class);

    @SuppressWarnings("unused")
    private Properties properties;
    
    /**
     * 拦截器首先需要执行的方法
     */
    public Object intercept(Invocation invocation) throws Throwable {
        
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }
        String sqlId = mappedStatement.getId();
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
        Object returnValue = null;
        String sql = getSql(configuration, boundSql, sqlId);
        try {
            //继续执行
            returnValue = invocation.proceed();
        } catch (Exception e) {
            logger.error(sql);
            logger.error("当前错误信息为："+sql);
            throw e;
        }

        return returnValue;
    }

    /**
     * 获取当前执行的sql语句
     */
    public static String getSql(Configuration configuration, BoundSql boundSql,String sqlId) {
        String sql = showSql(configuration, boundSql);
        StringBuilder str = new StringBuilder(100);
        //拼装需要显示的错误信息
        str.append(sqlId + "=====拦截器显示,操作失败,错误SQL为=======>");
        str.append(sql);
        return str.toString();
    }

    /**
     * 获取出错误的sql字符串
     */
    public static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        //将其他的[空白字符] 替换为一个空格
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                //一次或一次也没有
                sql = sql.replaceFirst("\\?",getParameterValue(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }

    /**
     * 获取当前传递的参数的值，可能有多个
     */
    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date || obj instanceof Timestamp) {
            value = "'" + DAODatetimeUtils.formatDate((Date) obj) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        return value;
    }

    /**
     * 包装类方法，对目标对象进一步的包装
     */
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 设置属性文件参数等
     */
    public void setProperties(Properties properties0) {
        this.properties = properties0;
    }
}