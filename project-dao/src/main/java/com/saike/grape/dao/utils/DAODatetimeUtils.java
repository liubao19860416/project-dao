package com.saike.grape.dao.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class DAODatetimeUtils {

    private static final Logger logger = LoggerFactory
            .getLogger(DAODatetimeUtils.class);
    
    private static final long ONE_DAY_IN_MILISECONDS = 24 * 60 * 60 * 1000;
    
    private static final String[] WEEK_NAMES = { 
        "", "周日", "周一", "周二", "周三", "周四", "周五", "周六" };

    public static Timestamp TIMESTAMP_ZERO = 
            parseTimestamp( "1970-01-01 00:00:00" );

    public static Date DATETIME_ZERO = 
            parseDatetime( "1970-01-01 00:00:00" );
    
    public static boolean isTimestampZero( Timestamp timestamp ) {
        return TIMESTAMP_ZERO.equals( timestamp );
    }
    
    public static boolean isNotTimestampZero( Timestamp timestamp ) {
        return ! isTimestampZero( timestamp );
    }
    
    public static boolean isDatetimeZero( Date datetime ) {
        if( datetime != null && datetime instanceof Timestamp ) {
            return isTimestampZero( ( Timestamp )datetime );
        }
        return DATETIME_ZERO.equals( datetime );
    }
    
    public static Timestamp afterOneWeek(Timestamp datetime) {

        if (datetime == null) {
            throw new IllegalArgumentException("Argument datetime is null!!");
        }

        Calendar c = Calendar.getInstance();
        c.setTime(datetime);
        c.add(Calendar.WEEK_OF_YEAR, 1);
        return new Timestamp(c.getTime().getTime());
    }

    public static long daysBetween(Timestamp datetime1, Timestamp datetime2) {

        Timestamp currentTimestamp = currentTimestamp();

        datetime1 = (datetime1 == null) ? currentTimestamp : datetime1;
        datetime2 = (datetime2 == null) ? currentTimestamp : datetime2;

        long delta = Math.abs(datetime2.getTime() - datetime1.getTime());
        return (long) Math.ceil(delta * 1.0 / ONE_DAY_IN_MILISECONDS);
    }

    public static Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp parseTimestamp(String timestamp) {

        SimpleDateFormat sdf = new SimpleDateFormat(DAOConstants.DATETIME_PATTERN);
        try {
            return new Timestamp(sdf.parse(timestamp).getTime());
        } catch (Exception ex) {
            logger.error("\"" + timestamp + "\" is invalid,"
                    + " it should be in pattern " + " \""
                    + DAOConstants.DATETIME_PATTERN + "\"", ex);
        }
        return null;
    }

    public static Date parseDatetime(String datetime) {

        SimpleDateFormat sdf = new SimpleDateFormat(DAOConstants.DATETIME_PATTERN);
        try {
            return sdf.parse(datetime);
        } catch (Exception ex) {
            logger.error("\"" + datetime + "\" is invalid,"
                    + " it should be in pattern " + " \""
                    + DAOConstants.DATETIME_PATTERN + "\"", ex);
        }
        return null;
    }

    public static String formatDate(Date timestamp) {
        return timestamp == null ? "" : new SimpleDateFormat(
                DAOConstants.DATETIME_PATTERN).format(timestamp);
    }

    public static String formatTimestamp(Timestamp timestamp) {
        return formatDate(timestamp);
    }

    public static String formatTimestamp(Timestamp timestamp, 
            String parttern) {
       return formatTimestamp(timestamp, 
                parttern);
    }

    public static String formatDate(Date date, String parttern) {
        parttern = (StringUtils.isEmpty(parttern)) ? DAOConstants.DATETIME_PATTERN
                : parttern;
        SimpleDateFormat sdf = new SimpleDateFormat(parttern);
        try {
            return sdf.format(date);
        } catch (Exception ex) {
            logger.error("\"" + date + "\" is invalid,"
                  + " it should be in pattern " + " \"" + parttern + "\"", ex);
        }
        return null;
    }
    
    public static Timestamp dayStartDatetime( Timestamp timestamp ) {
        String date = formatDate( timestamp, DAOConstants.DATE_PATTERN );
        return parseTimestamp( date + " 00:00:00" );
    }
    
    public static Timestamp dayEndDatetime( Timestamp timestamp ) {
        String date = formatDate( timestamp, DAOConstants.DATE_PATTERN );
        return parseTimestamp( date + " 23:59:59" );
    }
    
    public static Timestamp nextDay( Timestamp timestamp ) {
        return dayPlus( timestamp, 1 );
    }
    
    public static Timestamp dayPlus( Timestamp timestamp, int days ) {
        Calendar c = Calendar.getInstance();
        c.setTime( timestamp );
        c.add( Calendar.DAY_OF_MONTH, days );
        return new Timestamp( c.getTimeInMillis() );
    }
    
    public static String getWeekInString( Timestamp timestamp ) {
        Calendar c = Calendar.getInstance();
        c.setTime( timestamp );
        return WEEK_NAMES[ c.get( Calendar.DAY_OF_WEEK ) ];
    }
    
}
