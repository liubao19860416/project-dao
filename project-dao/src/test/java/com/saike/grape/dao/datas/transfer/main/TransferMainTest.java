package com.saike.grape.dao.datas.transfer.main;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saike.grape.dao.generic.GenericDAOTest;

public class TransferMainTest extends GenericDAOTest {

    @Autowired
    TransferMain transferMain;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testTransferDatas() {
        transferMain.transfer();
        assertTrue(true);
    }

}
