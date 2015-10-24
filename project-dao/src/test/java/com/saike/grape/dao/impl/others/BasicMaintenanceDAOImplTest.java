package com.saike.grape.dao.impl.others;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saike.grape.dao.api.others.BasicMaintenanceDAO;
import com.saike.grape.dao.generic.GenericDAOTest;


public class BasicMaintenanceDAOImplTest extends GenericDAOTest {

    @Autowired
    private BasicMaintenanceDAO basicMaintenanceDAO;
    
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSelectByUserVehicle() {
    }

}
