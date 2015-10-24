package com.saike.grape.dao.utils;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatetimeUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAfterOneWeek() {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
        try {
            Timestamp t = new Timestamp( 
                    sdf.parse( "2014-08-28 12:00:00" ).getTime() );
            
            Timestamp dateAfterOneWeek = new Timestamp( 
                    sdf.parse( "2014-09-04 12:00:00" ).getTime() );
            
            Timestamp t2 = DAODatetimeUtils.afterOneWeek( t );
            
            assertEquals( dateAfterOneWeek, t2 );
            
        }catch( Exception ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Test
    public void testDaysBetween() {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
        try {            
            assertEquals( 1, DAODatetimeUtils.daysBetween( 
                new Timestamp( sdf.parse( "2014-08-28 00:00:00" ).getTime() ), 
                new Timestamp( sdf.parse( "2014-08-29 00:00:00" ).getTime() ) ) 
            );
            
            assertEquals( 2, DAODatetimeUtils.daysBetween( 
                new Timestamp( sdf.parse( "2014-08-28 00:00:00" ).getTime() ), 
                new Timestamp( sdf.parse( "2014-08-29 01:00:00" ).getTime() ) ) 
            );
            
            assertEquals( 2, DAODatetimeUtils.daysBetween( 
                new Timestamp( sdf.parse( "2014-08-28 00:00:00" ).getTime() ), 
                new Timestamp( sdf.parse( "2014-08-29 12:59:00" ).getTime() ) ) 
            );
            
            assertEquals( 2, DAODatetimeUtils.daysBetween( 
                new Timestamp( sdf.parse( "2014-08-28 00:00:00" ).getTime() ), 
                new Timestamp( sdf.parse( "2014-08-29 23:59:00" ).getTime() ) ) 
            );
            
            assertEquals( 4, DAODatetimeUtils.daysBetween( 
                new Timestamp( sdf.parse( "2014-08-28 00:00:00" ).getTime() ), 
                new Timestamp( sdf.parse( "2014-08-31 21:59:00" ).getTime() ) ) 
            );
        }catch( Exception ex ) {
            throw new RuntimeException( ex );
        }
    }
    
    @Test
    public void testZeroTimestamp() {
        Timestamp zero = DAODatetimeUtils.TIMESTAMP_ZERO;
        assertEquals( "1970-01-01 10:00:00",
                DAODatetimeUtils.formatTimestamp( zero ) );
        
        assertTrue( DAODatetimeUtils.isTimestampZero( 
                DAODatetimeUtils.parseTimestamp( "1970-01-01 10:00:00" ) ) );
                
        assertFalse( DAODatetimeUtils.isTimestampZero( 
                DAODatetimeUtils.parseTimestamp( "1970-01-01 10:00:01" ) ) );
        
        assertTrue( DAODatetimeUtils.isDatetimeZero( 
                DAODatetimeUtils.parseTimestamp( "1970-01-01 10:00:00" ) ) );
        
        assertTrue( DAODatetimeUtils.isDatetimeZero( 
                DAODatetimeUtils.parseDatetime( "1970-01-01 10:00:00" ) ) );
        
    }
    
    @Test
    public void testNextDay() {
        
        Timestamp t = DAODatetimeUtils.parseTimestamp( "2014-09-18 10:00:01" );
        Timestamp t1 = DAODatetimeUtils.parseTimestamp( "2014-09-19 10:00:01" );
        Timestamp t2 = DAODatetimeUtils.parseTimestamp( "2014-09-20 10:00:01" );
        
        assertEquals( t1, DAODatetimeUtils.nextDay( t ) );
        assertEquals( t2, DAODatetimeUtils.nextDay( t1 ) );
        
        t = DAODatetimeUtils.parseTimestamp( "2014-09-30 10:00:01" );
        t1 = DAODatetimeUtils.parseTimestamp( "2014-10-01 10:00:01" );
        assertEquals( t1, DAODatetimeUtils.nextDay( t ) );
    }
    
    @Test
    public void testDayPlus() {
        Timestamp t = DAODatetimeUtils.parseTimestamp( "2014-09-18 10:00:01" );
        Timestamp t1 = DAODatetimeUtils.parseTimestamp( "2014-10-01 10:00:01" );
        Timestamp t2 = DAODatetimeUtils.parseTimestamp( "2014-09-15 10:00:01" );
        
        assertEquals( t1, DAODatetimeUtils.dayPlus( t, 13 ) );
        assertEquals( t2, DAODatetimeUtils.dayPlus( t, -3 ) );
    }
    
    @Test
    public void testGetWeekInString() {
        Timestamp t = DAODatetimeUtils.parseTimestamp( "2014-09-18 10:00:01" );
        assertEquals( "周四", DAODatetimeUtils.getWeekInString( t ) );
        
        t = DAODatetimeUtils.parseTimestamp( "2014-09-20 10:00:01" );
        assertEquals( "周六", DAODatetimeUtils.getWeekInString( t ) );
        
        t = DAODatetimeUtils.parseTimestamp( "2014-09-21 10:00:01" );
        assertEquals( "周日", DAODatetimeUtils.getWeekInString( t ) );
        
        t = DAODatetimeUtils.parseTimestamp( "2014-09-22 23:59:59" );
        assertEquals( "周一", DAODatetimeUtils.getWeekInString( t ) );
        
    }
    
}
