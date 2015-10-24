package com.saike.grape.service.genertic;

import java.util.List;

import com.saike.grape.dao.entity.basic.BaseEntity;
import com.saike.grape.service.vo.basic.BaseViewObject;

/**
 * 服务层基类接口
 * 
 * @param <V> BaseViewObject子视图类型
 * @param <E> BaseEntity子实体类型
 * 
 * @author zeng wei
 * @version 2.0
 *
 */
public interface GenericService<V extends BaseViewObject<V>, 
                         E extends BaseEntity> {

    public V getById( Long id );
    
    public V getByCode( String code );
    
    public List<V> getByCodes( List<String> codes );
    
    public boolean deleteById( Long id );
    
    public boolean deleteByCode( String code );
    
    public boolean deleteByCodeList( List<String> codeList);
    
    public boolean save( V viewObject );
    
    public void save( List<V> viewObjects );
    
    public boolean insert( V viewObject );
    
    public void insert( List<V> viewObjects );

}
