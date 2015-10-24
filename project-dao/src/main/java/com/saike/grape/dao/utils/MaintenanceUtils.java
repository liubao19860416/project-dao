package com.saike.grape.dao.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 保养项目工具方法类
 */
public final class MaintenanceUtils {

    private static Pattern P_CODE = Pattern.compile( "[a-zA-Z0-9\\-]{3}" );
        
    private static final float MAINTENANCE_TYPE_VALVE_VALUE_LOW = 0.65f;
    private static final float MAINTENANCE_TYPE_VALVE_VALUE_MEDIUM = 0.75f;
    private static final float MAINTENANCE_TYPE_VALVE_VALUE_HIGH = 0.85f;

    /**
     * 该方法用来拆分品牌或车辆编号
     * <br/>for example:
     * <br/>RW-550-s-MAT => [ "RW-", "550", "-s-", "MAT" ]
     */
    public static String[] splitCode( String code ) {
        if( code == null || "".equals( code ) ) {
            throw new IllegalArgumentException( 
                    "Argument code is null or empty!!" );
        }
        
        int i = 0;
        List<String> ss = new ArrayList<>();
        Matcher m = P_CODE.matcher( code );
        while( m.find( i ) ) {
            ss.add( code.substring( m.start(), ( i = m.end() ) ) );
        }
        
        return ss.toArray( new String[]{ } );
    }
    
    /**
     * 该方法用来拆分品牌编号并添加通配符
     * <br/>for example:
     * <br/>RW-550-s- => 
     * [ "RW-*", "RW-550*", "RW-550-s-*" ]
     */
    public static String[] splitAndWildBrandCode( String brandCode ) {
        return splitAndWildCode( brandCode );
    }
    
    /**
     * 该方法用来拆分车辆品牌编号并添加通配符
     * <br/>for example:
     * <br/>RW-550-s-MAT => 
     * [ "RW-*", "RW-550*", "RW-550-s-*" ]
     */
    public static String[] splitAndWildVehicleCode( String vehicleCode ) {
        String[] ss = splitAndWildCode( vehicleCode );
        
        if( ss == null || ss.length <= 0 ) {
            return new String[0];
        }
        
        // discard the last one item
        String[] ss2 = new String[ ss.length - 1 ];
        
        System.arraycopy( ss, 0, ss2, 0, ss2.length );
        
        return ss2;
    }
    
    /**
     * 该方法用来拆分品牌或车辆编号并添加通配符
     * <br/>for example:
     * <br/>RW-550-s- => 
     * [ "RW-*", "RW-550*", "RW-550-s-*" ]
     */
    public static String[] splitAndWildCode( String code ) {
        String[] ss = splitCode( code );
        String[] ss2 = new String[ ss.length ];
        String prefix = "";
        for( int i = 0; i < ss.length; i++ ) {
            prefix = prefix + ss[i];
            ss2[i] = prefix + DAOConstants.CODE_WILDCARD;
        }
        
        return ss2;
    }
    
    /**
     * 该方法用来根据当前值判断保养类型
     * 
     * @param lastValue 上次保养时的值（公里数或年数，年数按日计算）
     * @param currentValue 当前保养时的值（公里数或年数，年数按日计算）
     * @param periodValue 保养周期值（公里数或年数，年数按日计算）
     * 
     * @return int 返回值为：
     * <li>Constants.MAINTENANCE_TYPE_NONE &nbsp;&nbsp; 无需保养</li>
     * <li>Constants.MAINTENANCE_TYPE_LOW &nbsp;&nbsp; 小保 </li>
     * <li>Constants.MAINTENANCE_TYPE_MEDIUM &nbsp;&nbsp; 大保或小保 </li>
     * <li>Constants.MAINTENANCE_TYPE_HIGH &nbsp;&nbsp; 大保 </li>
     */
    public static int maintenanceTypeVerify(
            int lastValue,
            int currentValue, 
            int periodValue ) {
        
        if( lastValue < 0 ) {
            throw new IllegalArgumentException( 
                    "Argument lastValue is less than 0!!" );
        }
        
        if( currentValue <= 0 ) {
            throw new IllegalArgumentException( 
                    "Argument currentValue is less than or equals to 0!!" );
        }
        
        if( periodValue <= 0 ) {
            throw new IllegalArgumentException( 
                    "Argument periodValue is less than or equals to 0!!" );
        }
        
        int deltaValue = currentValue - lastValue;
        
        if( deltaValue < 0 ) {
            throw new IllegalArgumentException( 
                 "Argument currentValue should be greater than lastValue!!" );
        }
        
        if( deltaValue == 0 ) {
            return DAOConstants.MAINTENANCE_TYPE_LOW;
        }
        
        if( ( deltaValue * 1.0 / periodValue ) >= 1.0 ) {
            return DAOConstants.MAINTENANCE_TYPE_HIGH;
        }
        
        double dCurrentValue = deltaValue - 0.000001;
        double ratio = ( dCurrentValue % periodValue ) / periodValue;

        if(  ratio < MAINTENANCE_TYPE_VALVE_VALUE_LOW ) {
            return DAOConstants.MAINTENANCE_TYPE_LOW;
        }else {
            return ratio < MAINTENANCE_TYPE_VALVE_VALUE_MEDIUM
                    ? DAOConstants.MAINTENANCE_TYPE_MEDIUM
                            : DAOConstants.MAINTENANCE_TYPE_HIGH;
        }
    }
    
    /**
     * 该方法用来根据当前值判断周期性保养类型
     * 
     * @param currentValue 当前保养时的值（公里数或年数，年数按日计算）
     * @param periodValue 保养周期值（公里数或年数，年数按日计算）
     * 
     * @return int 返回值为：
     * <li>Constants.MAINTENANCE_TYPE_NONE &nbsp;&nbsp; 无需保养</li>
     * <li>Constants.MAINTENANCE_TYPE_LOW &nbsp;&nbsp; 小保 </li>
     * <li>Constants.MAINTENANCE_TYPE_MEDIUM &nbsp;&nbsp; 大保或小保 </li>
     * <li>Constants.MAINTENANCE_TYPE_HIGH &nbsp;&nbsp; 大保 </li>
     */
    public static int maintenanceTypeVerifyPeriodically(
            int currentValue, 
            int periodValue ) {
        
        
        if( currentValue <= 0 ) {
            throw new IllegalArgumentException( 
                    "Argument currentValue is less than or equals to 0!!" );
        }
        
        return maintenanceTypeVerify( 0, 
                ( int )Math.ceil( ( currentValue - 0.000001 ) % periodValue ),
                periodValue );
    }
    
    /**
     * 该方法用来根据当前值判断保养项目是否符合周期保养条件
     * 
     * @param lastValue 上次保养时的值（公里数或年数，年数按日计算）
     * @param currentValue 当前保养时的值（公里数或年数，年数按日计算）
     * @param periodValue 保养周期值（公里数或年数，年数按日计算）
     * 
     * @return boolean 返回值为：
     * true: 需要保养；
     * false: 无需保养 
     */
    public static boolean isPeriodMaintenanceFitted(
            int lastValue,
            int currentValue, 
            int periodValue ) {
        
        if( lastValue < 0 ) {
            throw new IllegalArgumentException( 
                    "Argument lastValue is less than 0!!" );
        }
        
        if( currentValue <= 0 ) {
            throw new IllegalArgumentException( 
                    "Argument currentValue is less than or equals to 0!!" );
        }
        
        if( periodValue <= 0 ) {
            throw new IllegalArgumentException( 
                    "Argument periodValue is less than or equals to 0!!" );
        }
        
        int deltaValue = currentValue - lastValue;
        
        if( deltaValue < 0 ) {
            throw new IllegalArgumentException( 
                 "Argument currentValue should be greater than lastValue!!" );
        }
        
        if( deltaValue == 0 ) {
            return false;
        }
        
        if( ( deltaValue * 1.0 / periodValue ) >= 1.0 ) {
            return true;
        }
        
        double dCurrentValue = deltaValue - 0.000001;
        double ratio = ( dCurrentValue % periodValue ) / periodValue;
        
        return ratio > MAINTENANCE_TYPE_VALVE_VALUE_HIGH;
    }
    
    /**
     * 该方法用来根据周期值过滤记录，符合保养条件或保养周期的记录
     */
    /*public static <E extends Maintenance> List<E> filterByPeriodValue( 
            UserVehicle userVehicle, 
            List<E> maintenances,
            List<UserVehicleMaintenanceHistory> histories ) {
        
        List<E> entitiesResult = new ArrayList<>();
        
        if( maintenances == null || maintenances.size() == 0 ) {
            return entitiesResult;
        }
    
        Map<String, UserVehicleMaintenanceHistory> historiesMap =
                new HashMap<>();
        
        if( histories != null && histories.size() > 0 ) {
            for( UserVehicleMaintenanceHistory h : histories ) {
                historiesMap.put( h.getMaintenanceCode(), h );
            }
        }
        
        for( E maintenance : maintenances ) {
            
            if( maintenance.getPeriodUnit() == null ) {
                entitiesResult.add( maintenance );
                continue;
            }
            
            int lastValue = -1, currentValue = -1, periodValue = -1;
            UserVehicleMaintenanceHistory history = 
                    historiesMap.get( maintenance.getCode() );
            
            switch( maintenance.getPeriodUnit() ) {
            case KM:
                lastValue = ( history != null ? history.getKmTraveled() :
                        userVehicle.getLastMaintainedKm() );
                currentValue = userVehicle.getKmTraveled();
                periodValue = maintenance.getPeriodValue();
                break;
            case DAY:
                lastValue = 0;
                Timestamp lastTimestamp = null;
                if( history != null ) {
                    lastTimestamp = history.getMaintainedDatetime();
                }else if( DatetimeUtils.isNotTimestampZero( 
                        userVehicle.getBoughtInYear() ) ) {
                    lastTimestamp = userVehicle.getBoughtInYear();
                }else if( DatetimeUtils.isNotTimestampZero( 
                        userVehicle.getVehicle().getOnMarketDatetime() ) ) {
                    lastTimestamp = 
                            userVehicle.getVehicle().getOnMarketDatetime();
                }
                
                currentValue = ( int )DatetimeUtils.daysBetween( 
                    DatetimeUtils.currentTimestamp(), lastTimestamp );

                periodValue = maintenance.getPeriodValue();
                
                break;
            case NONE: default:
                break;
            }
            
            if( lastValue < 0 || isPeriodMaintenanceFitted( lastValue, 
                        currentValue, periodValue ) ) {
                entitiesResult.add( maintenance );
            }
        }

        return entitiesResult;
    }*/

}
