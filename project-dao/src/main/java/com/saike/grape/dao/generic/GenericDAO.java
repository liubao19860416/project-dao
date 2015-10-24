package com.saike.grape.dao.generic;

import java.util.List;

import com.saike.grape.dao.entity.basic.BaseEntity;
/**
 * 基础通用的DAO接口
 */
public interface GenericDAO<E extends BaseEntity> {
    
    /**
     * 查询所有数据集合列表
     */
    public List<E> selectAll();
    /**
     * 根据id（自动增长的id主键）查询对应的实体Bean
     */
    public E findById( Long id );
    /**
     * 根据code（业务主键，uuid）查询对应的实体Bean
     */
    public E findByCode( String code );
    /**
     * 根据code集合（业务主键，uuid）查询对应的实体Bean
     */
    public List<E> findByCode( List<String> codeList );
    /**
     * insert方法，默认返回插入记录条数
     */
    public int insert( E entity );
    /**
     * 批量insert方法
     */
    public void insert( List<E> entities );
    /**
     * update方法，默认返回更新记录条数
     */
    public int update( E entity );
    /**
     * 批量update方法
     */
    public void update( List<E> entities );
    /**
     * 根据逻辑主键id删除对象（逻辑删除）
     */
    public int deleteById( Long id );
    /**
     * 批量删除对象方法（逻辑删除）
     */
    public int deleteById(List<String> idList);

    /**
     * 根据业务主键code删除对象（逻辑删除）
     */
    public int deleteByCode( String code );
    /**
     * 根据业务主键code批量删除对象（逻辑删除）
     */
    public int deleteByCode(List<String> codeList);
    /**
     * 根据实体bean对象，删除该对象，一般是根据其code或者id等唯一标识就可以（逻辑删除）
     */
    public int delete( E entity );
    /**
     * 根据实体bean对象集合，删除该对象集合，一般是根据其code或者id等唯一标识就可以（逻辑删除）
     */
    public void delete( List<E> entities );
    /**
     * 根据idList集合，物理删除对应的对象信息
     */
    public int deletePhysicalByIdList(List<String> idList);
    /**
     * 根据codeList集合，物理删除对应的对象信息
     */
    public int deletePhysicalByCodeList(List<String> codeList);
    

}
