package com.saike.grape.dao.generic;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.saike.grape.dao.subtest.SubTestDAOImpl;

/*
 * 以SubTestDAOImpl为主类，进行spring相关环境测试  OK
 */
public class GenericDAOBatisImplTest extends GenericDAOTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetDAOImplClass() {
        SubTestDAOImpl tDAO = new SubTestDAOImpl();
        assertEquals(tDAO.daoImplClass.getName(),"com.saike.grape.dao.subtest.SubTestDAOImpl");
        assertEquals(tDAO.entityClass.getName(),"com.saike.grape.dao.subtest.TestEntity");
    }

    @Test
    public void testGetTableName() {
        SubTestDAOImpl tDao = new SubTestDAOImpl();
        assertEquals(tDao.getTableName(), "t_sub_test");
    }

    @Test
    public void testGetNameSpace() {
        SubTestDAOImpl tDao = new SubTestDAOImpl();
        assertEquals(tDao.getNameSpace("insert"),
                "com.saike.grape.dao.subtest.SubTestDAOImplMapper.insert");

    }

}
