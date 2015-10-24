package com.saike.grape.dao.subtest;

import org.apache.ibatis.type.Alias;

import com.saike.grape.dao.entity.basic.BaseEntity;
/**
 * 简单测试bean
 */
@Alias( "testEntity" )
public class TestEntity extends BaseEntity {

    private static final long serialVersionUID = 2798923321657722570L;
    
}
