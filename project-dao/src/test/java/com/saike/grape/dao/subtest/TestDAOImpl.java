package com.saike.grape.dao.subtest;

import com.saike.grape.dao.generic.AbstractTestDAO;
/**
 * 简单测试类（父类型）
 */
public class TestDAOImpl<E extends TestEntity, T extends TestDAOImpl<E, T>>
        extends AbstractTestDAO<E, T> {
}
