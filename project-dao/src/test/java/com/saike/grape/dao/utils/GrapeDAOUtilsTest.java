package com.saike.grape.dao.utils;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.saike.grape.dao.entity.others.Maintenance;
import com.saike.grape.dao.utils.DAOConstants;
import com.saike.grape.dao.utils.DAOUtils;


public class GrapeDAOUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
    public void testPopulateValues() {
        
        Map<String, Object> mapValues = new HashMap<>();
        mapValues.put( "name", "Test" );
        mapValues.put( "periodUnit", "km" );
        
        Maintenance maintenance = new Maintenance();
        
        try {
            DAOUtils.populate( maintenance, mapValues );
            
            assertEquals( "Test", maintenance.getName() );
            
            assertEquals( DAOConstants.MaintenancePeriodUnit.KM, 
                    maintenance.getPeriodUnit() );
            
        } catch ( Exception ex ) {
            ex.printStackTrace();
            assertTrue( false );
        }
    }

    @Test
    public void testCopyPropertities() {
        
        String timeStr = "2014-09-09 14:23:00";
        Timestamp datetime = DAODatetimeUtils.parseTimestamp( timeStr );
        
        assertEquals( timeStr, DAODatetimeUtils.formatTimestamp( datetime ) );
        
        ObjectA a = new ObjectA();
        a.setId( 100L );
        a.setName( "aaa" );
        a.setValue( 100 );
        a.setFloatValue( 123.4f );
        a.setUnit( "km" );
        a.setDatetime( datetime );
        a.setCreatedDatetime( timeStr );
//        a.setUpdatedDatetime( "" );
        a.setActived( true );
        
        ObjectB b = new ObjectB();
        
        DAOUtils.copyProperties( b, a );
        
        assertEquals( a.getName(), b.getName() );
        assertTrue( a.getValue() == b.getValue() );
        assertEquals( DAOConstants.MaintenancePeriodUnit.KM, b.getUnit() );
        assertEquals( DAOConstants.BOOLEAN_TRUE_IN_STR, b.getActived() );
        assertTrue( DAODatetimeUtils.isTimestampZero( 
                b.getUpdatedDatetime() ) );
        
        ObjectA a2 = new ObjectA();
        b.setActived( DAOConstants.BOOLEAN_FALSE_IN_STR );
        
        DAOUtils.copyProperties( a2, b );
        
        assertNull( a2.getId() );
        assertEquals( a.getName(), a2.getName() );
        assertEquals( a.getUnit(), a2.getUnit() );
        assertFalse( a2.isActived() );
        assertEquals( "", a2.getUpdatedDatetime() );
        
        ObjectC c = new ObjectC();
        DAOUtils.copyProperties( c, a );
        assertEquals( "100", c.getValue() );
        assertEquals( "123.4", c.getFloatValue() );
        assertEquals( timeStr, c.getDatetime() );
        
        ObjectA a3 = new ObjectA();
        DAOUtils.copyProperties( a3, c );
        assertTrue( 100 == a3.getValue() );
        assertTrue( 123.4f == a3.getFloatValue() );
        assertEquals( datetime, a3.getDatetime() );
    }
    
//    @Test
    public void testUuid() {
        String uuid = DAOUtils.uuid();
        System.out.println( "uuid = " + uuid );
        assertNotNull( uuid );
        assertTrue( 32 == uuid.length() );
    }

//    @Test
    public void testDescribe() {
        
        Maintenance maintenance = new Maintenance();
        maintenance.setCode( "001" );
        maintenance.setPeriodUnit( DAOConstants.MaintenancePeriodUnit.KM );
        
        try {
            
            Map<String, List<?>> mapFieldsAndValues
                    = DAOUtils.describe( maintenance );
            
            assertNotNull( mapFieldsAndValues );
            
            List<String> fields 
                = ( List<String> )mapFieldsAndValues.get( "fields" );
            
            List<Object> values 
                = ( List<Object> )mapFieldsAndValues.get( "values" );
            
            for( int i = 0; i < fields.size(); i++ ) {
                System.out.println( fields.get( i ) 
                        + " = >" + values.get( i ) + "<" );
            }
            
        } catch (IllegalAccessException | InvocationTargetException
                | NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
