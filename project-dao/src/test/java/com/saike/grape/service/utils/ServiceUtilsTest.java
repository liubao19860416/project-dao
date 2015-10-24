package com.saike.grape.service.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.saike.grape.dao.utils.DAOConstants;
import com.saike.grape.dao.utils.DAODatetimeUtils;
import com.saike.grape.dao.utils.ObjectA;
import com.saike.grape.dao.utils.ObjectB;
import com.saike.grape.dao.utils.ObjectC;
import com.saike.grape.service.utils.ServiceUtils;

public class ServiceUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCopyProperties() {
        
        String timeStr = "2014-09-09 14:23:00";
        Timestamp datetime = DAODatetimeUtils.parseTimestamp( timeStr );
        
        ObjectA a = new ObjectA();
        a.setId( 100L );
        a.setName( "aaa" );
        a.setValue( 100 );
        a.setFloatValue( 123.4f );
        a.setUnit( "km" );
        a.setDatetime( datetime );
        a.setCreatedDatetime( timeStr );
        
        ObjectB b = new ObjectB();
        
        try {
            ServiceUtils.copyProperties( b, a );
        }catch( Exception ex ) {
            ex.printStackTrace();
            assertTrue( false );
        }
        
        assertEquals( a.getName(), b.getName() );
        assertTrue( a.getValue() == b.getValue() );
        assertEquals( DAOConstants.MaintenancePeriodUnit.KM, b.getUnit() );
        
        ObjectA a2 = new ObjectA();
        
        try {
            ServiceUtils.copyProperties( a2, b );
        }catch( Exception ex ) {
            ex.printStackTrace();
            assertTrue( false );
        }
        
        assertNull( a2.getId() );
        assertEquals( a.getName(), a2.getName() );
        assertEquals( a.getUnit(), a2.getUnit() );
        
        ObjectC c = new ObjectC();
        ServiceUtils.copyProperties( c, a );
        assertEquals( "100", c.getValue() );
        assertEquals( "123.4", c.getFloatValue() );
        assertEquals( timeStr, c.getDatetime() );
        
        ObjectA a3 = new ObjectA();
        ServiceUtils.copyProperties( a3, c );
        assertTrue( 100 == a3.getValue() );
        assertTrue( 123.4f == a3.getFloatValue() );
        assertEquals( datetime, a3.getDatetime() );
        
    }

}
