package com.saike.grape.service.genertic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.saike.grape.dao.entity.basic.BaseEntity;
import com.saike.grape.dao.generic.GenericDAO;
import com.saike.grape.dao.utils.DAODatetimeUtils;
import com.saike.grape.dao.utils.DAOUtils;
import com.saike.grape.service.utils.ServiceUtils;
import com.saike.grape.service.vo.basic.BaseViewObject;


/**
 * 服务层抽象通用基类
 * 
 * @param <V> BaseViewObject子视图类型
 * @param <E> BaseEntity子实体类型
 * @param <T> GenericServiceImpl子类型
 * 
 * @author zeng wei
 * @version 2.0
 *
 */
public abstract class GenericServiceImpl<V extends BaseViewObject<V>, 
                                 E extends BaseEntity,
                                 T extends GenericServiceImpl<V, E, T>>
    implements GenericService<V, E> {

    private static final Logger logger = 
            LoggerFactory.getLogger( GenericServiceImpl.class );
    
    private static final Map<Class<?>, Logger> loggersMap =
            new ConcurrentHashMap<>();
    
    private GenericDAO<E> genericDAO;
    
    private Class<V> viewObjectClass;
    private Class<E> entityClass;
    private Class<T> serviceImplClass;
    
    @SuppressWarnings("unchecked")
    public GenericServiceImpl( final GenericDAO<E> genericDAO ) {
        
        this.genericDAO = genericDAO;
        
        Type[] types = ( ( ParameterizedType )getClass()
                .getGenericSuperclass() ).getActualTypeArguments();
        
        viewObjectClass = ( Class<V> )types[0];
        entityClass = ( Class<E> )types[1];
        serviceImplClass = ( Class<T> )types[2];
        
    }
    
    @Override
    @Transactional( propagation = Propagation.REQUIRED, readOnly = true )
    public V getById( Long id ) {
        E entity = this.genericDAO.findById( id );
        return entity != null ? this.transToViewObject( entity ) : null;
    }
    
    @Override
    @Transactional( propagation = Propagation.REQUIRED, readOnly = true )
    public V getByCode( String code ) {
        E entity = this.genericDAO.findByCode( code );
        return entity != null ? this.transToViewObject( entity ) : null;
    }
    
    @Override
    @Transactional( propagation = Propagation.REQUIRED, readOnly = true )
    public List<V> getByCodes( List<String> codes ) {
        return this.transToViewObject( this.genericDAO.findByCode( codes ) );
    }
    
    @Override
    @Transactional( propagation = Propagation.REQUIRED )
    public boolean save( V viewObject ) {
        if( viewObject == null ) {
            throw new IllegalArgumentException( 
                    "Argument viewObject is empty!!" );
        }
        
        E entity = this.transToEntity( viewObject );
        
        if( entity.getCreatedDatetime() == null ) {
            entity.setCreatedDatetime( DAODatetimeUtils.currentTimestamp() );
        }
        
        if( entity.getUpdatedDatetime() == null ) {
            entity.setUpdatedDatetime( DAODatetimeUtils.currentTimestamp() );
        }
        
        if( hasCode( entity ) || hasId( entity ) ) {
            return this.genericDAO.update( entity ) == 1;
        }else {
            if( ! hasCode( entity ) ) {
                entity.setCode( DAOUtils.uuid() );
            }
            return this.genericDAO.insert( entity ) == 1;
        }
    }
    
    @Override
    @Transactional( propagation = Propagation.REQUIRED )
    public void save( List<V> viewObjects ) {
        if( viewObjects == null || viewObjects.size() == 0 ) {
            throw new IllegalArgumentException( 
                    "Argument viewObjects is empty!!" );
        }
        
        List<E> entities = this.transToEntity( viewObjects );
        
        List<E> insertList = new ArrayList<>();
        List<E> updateList = new ArrayList<>();
        
        for( E e : entities ) {
            if( hasCode( e ) || hasId( e ) ) {
                updateList.add( e );
            }else {
                if( ! hasCode( e ) ) {
                    e.setCode( DAOUtils.uuid() );
                }
                insertList.add( e );
            }
        }
        
        if( insertList.size() > 0 ) {
            this.genericDAO.insert( entities );
        }
        
        if( updateList.size() > 0 ) {
            this.genericDAO.update( updateList );
        }
    }
    
    @Override
    @Transactional( propagation = Propagation.REQUIRED )
    public boolean insert( V viewObject ) {
        if( viewObject == null ) {
            throw new IllegalArgumentException( 
                    "Argument viewObject is empty!!" );
        }
        
        E entity = this.transToEntity( viewObject );
        return this.genericDAO.insert( entity ) == 1;
    }
    
    @Override
    @Transactional( propagation = Propagation.REQUIRED )
    public void insert( List<V> viewObjects ) {
        if( viewObjects == null || viewObjects.size() == 0 ) {
            throw new IllegalArgumentException( 
                    "Argument viewObjects is empty!!" );
        }
        
        List<E> entities = this.transToEntity( viewObjects );
        this.genericDAO.insert( entities );
    }
    
    @Override
    @Transactional( propagation = Propagation.REQUIRED )
    public boolean deleteById( Long id ) {
        return this.genericDAO.deleteById( id ) == 1;
    }
    
    @Override
    @Transactional( propagation = Propagation.REQUIRED )
    public boolean deleteByCode( String code ) {
        return this.genericDAO.deleteByCode( code ) == 1;
    }
    
    @Override
    @Transactional( propagation = Propagation.REQUIRED )
    public boolean deleteByCodeList( List<String> codeList) {
    	 return this.genericDAO.deleteByCode(codeList) >0;
    }
    
    protected boolean hasCode( E entity ) {
        String code = entity.getCode();
        return code != null && ! "".equals( code );
    }
    
    protected boolean hasId( E entity ) {
        Long id = entity.getId();
        return id != null && id > 0;
    }
    
    protected Logger getLogger() {
        return getLogger( this.serviceImplClass );
    }
    
    private static Logger getLogger( Class<?> clazz ) {
        Logger logger = loggersMap.get( clazz );
        if( logger == null ) {
            logger = LoggerFactory.getLogger( clazz );
            loggersMap.put( clazz, logger );
        }
        return logger;
    }
    
    protected V newViewObject() {
        V obj = null;
        try {
            obj = this.viewObjectClass.newInstance();
        }catch( Exception ex ) {
            logger.error( "GenericServiceImpl.newViewObject exception" , ex );
        }
        return obj;
    }
    
    protected E newEntityObject() {
        E obj = null;
        try {
            obj = this.entityClass.newInstance();
        }catch( Exception ex ) {
            logger.error( "GenericServiceImpl.newViewObject exception" , ex );
            throw new RuntimeException( ex );
        }
        return obj;
    }
    
    protected E transToEntity( V object ) {
        return transToEntity( object, ( Object[] )null );
    }
    
    protected V userCollectViewObject( E entity ) {
        return transToViewObject( entity, ( Object[] )null );
    }
    
    protected List<E> transToEntity( List<V> objects ) {
        return transToEntity( objects, ( Object[] )null );
    }
    
    protected List<E> transToEntity( List<V> objects, Object... options ) {
        List<E> entities = new ArrayList<>();
        if( objects != null ) {
            for( V obj : objects ) {
                entities.add( transToEntity( obj, options ) );
            }
        }
        return entities;
    }
    
    protected List<V> transToViewObject( List<E> entities ) {
        return transToViewObject( entities, ( Object[] )null );
    }
    
    protected List<V> transToViewObject( List<E> entities, Object... options ) {
        List<V> viewObjects = new ArrayList<>();
        if( entities != null ) {
            for( E entity : entities ) {
                viewObjects.add( transToViewObject( entity, options ) );
            }
        }
        return viewObjects;
    }
    
    protected E transToEntity( V viewObject, Object... options ) {
        
        if( viewObject == null ) {
            return null;
        }
        
        E entity = this.newEntityObject();
        
        ServiceUtils.copyProperties( entity, viewObject );
        
        return entity;
    }
    
    protected V transToViewObject( E entity, Object... options ) {
        if( entity == null ) {
            return null;
        }
        
        V viewObject = this.newViewObject();
        
        ServiceUtils.copyProperties( viewObject, entity );
        
        return viewObject;
    }
    
}
