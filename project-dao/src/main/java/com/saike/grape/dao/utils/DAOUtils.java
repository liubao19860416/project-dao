package com.saike.grape.dao.utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean2;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saike.grape.dao.entity.basic.BaseEntity;

/**
 *  DAO 工具类
 */
public final class DAOUtils {
    
    private static final Logger logger = 
            LoggerFactory.getLogger( DAOUtils.class );
    
    // hidden from initiation
    private DAOUtils() {}
    
    static {
        //注册了转换器（日期，枚举和字符串）
        BeanUtilsBean.setInstance(
                new BeanUtilsBean( new CustomizedConvertUtilsBean() ) );
    }
    
    public static String uuid() {
        return UUID.randomUUID().toString().replace( "-", "" );
    }
    
    public static void copyProperties( Object dest, Object orig ) {
        
        try {
            //BeanUtils.copyProperties(dest, orig);
            //底层实现就是下面的代码，可以在这里注册日期格式等的转换器，在上面的static静态代码块
            BeanUtilsBean.getInstance().copyProperties( dest, orig );
        }catch( Exception ex ) {
            throw new IllegalArgumentException( ex );
        }
    }
    
    public static <E extends BaseEntity> void populate( 
            E bean, Map<String, Object> properties ) 
        throws IllegalAccessException, InvocationTargetException {
        
        BeanUtilsBean.getInstance().populate( bean, properties );
        
    }
    
    public static <E extends BaseEntity> int getPeriodValue( E entity ) {
        if( entity == null ) {
            throw new IllegalArgumentException( 
                            "Argument entity is null!!" );
        }
        
        PropertyUtilsBean propertyUtils = BeanUtilsBean.getInstance()
                .getPropertyUtils();
        
        try {
            
            return ( int )propertyUtils.getProperty( entity, "periodValue" );
            
        } catch ( Exception ex ) {
            logger.error( "GrapeDAOUtils.getPeriodValue exception:", ex );
        }
        
        throw new IllegalArgumentException( 
                "Cannot get period value from entity: " 
                            + entity.getClass().getName() );
    }
    
    public static <E extends BaseEntity> 
            DAOConstants.MaintenancePeriodUnit getPeriodUnit( E entity ) {
        
        if( entity == null ) {
            throw new IllegalArgumentException( 
                            "Argument entity is null!!" );
        }
        
        PropertyUtilsBean propertyUtils = BeanUtilsBean.getInstance()
                .getPropertyUtils();
        
        try {
            
            return ( DAOConstants.MaintenancePeriodUnit )
                        propertyUtils.getProperty( entity, "periodUnit" );
            
        } catch ( Exception ex ) {
            logger.error( "GrapeDAOUtils.getPeriodUnit exception:", ex );
        }
        
        throw new IllegalArgumentException( 
                "Cannot get period unit from entity: " 
                            + entity.getClass().getName() );
    }
    
    public static <E extends BaseEntity> 
        Map<String, List<?>> describe( E entity ) 
                            throws IllegalAccessException, 
                                   InvocationTargetException, 
                                   NoSuchMethodException {
        
        Map<String, List<?>> mapList = new HashMap<>();
        
        List<String> fields = new ArrayList<>();
        mapList.put( "fields", fields );
        
        List<Object> values = new ArrayList<>();
        mapList.put( "values", values );
        
        PropertyUtilsBean propertyUtils = BeanUtilsBean.getInstance()
                .getPropertyUtils();
        
        Map<String, Object> map = propertyUtils.describe( entity );
        
        if( map.size() > 0 ) {
            for( String key : map.keySet() ) {
                
                if( "class".equals( key ) ) {
                    continue; // ignore the Class property
                }
                
                Class<?> pClazz = 
                        propertyUtils.getPropertyType( entity, key );
                
                if( pClazz.isInterface()
                        || pClazz.isArray() 
                        || Collection.class.isAssignableFrom( pClazz )
                        || BaseEntity.class.isAssignableFrom( pClazz ) ) {
                    
                    continue;
                }
                
                Object value = map.get( key );
                
                if( pClazz.isEnum() && ( value != null ) ) {
                    value = value.toString();
                }
                
                fields.add( key );
                values.add( value );
            }
        }
        
        return mapList;
    }
    
    // class CustomizedConvertUtilsBean
    private static class CustomizedConvertUtilsBean extends ConvertUtilsBean2 {

        private static final CustomizedStringConverter
                    STRING_CONVERTER = new CustomizedStringConverter();
        
        private static final EnumConverter 
                    ENUM_CONVERTER = new EnumConverter();
        
        private static final DatetimeConverter
                    DATE_CONVERTER = new DatetimeConverter();

        @Override
        @SuppressWarnings({ "rawtypes" })
        public Converter lookup( Class pClazz ) {
            
            if( String.class.equals( pClazz ) ) {
                return STRING_CONVERTER;
            }else if( pClazz.isEnum() ) {
                return ENUM_CONVERTER;
            }else if( Date.class.isAssignableFrom( pClazz ) ) {
                return DATE_CONVERTER;
            }else {
                return super.lookup( pClazz );
            }
            
        }
    }
    
    private static class CustomizedStringConverter extends AbstractConverter {

        StringConverter strConverter = new StringConverter();
        
        @Override
        protected String convertToString( final Object pValue ) 
                throws Throwable {
            
            if( pValue != null ) {
                if( Timestamp.class.isAssignableFrom( pValue.getClass() ) ) {
                    if( DAODatetimeUtils.isTimestampZero( 
                            ( Timestamp )pValue ) ) {
                        return "";
                    }
                    return DAODatetimeUtils.formatTimestamp( 
                            ( Timestamp )pValue );
                }else if( Date.class.isAssignableFrom( 
                        pValue.getClass() ) ) {
                    
                    if( DAODatetimeUtils.isDatetimeZero( 
                            ( Date )pValue ) ) {
                        return "";
                    }
                    return DAODatetimeUtils.formatDate( ( Date )pValue );
                    
                }else if( Boolean.class.equals( pValue.getClass() ) ) {
                    return Boolean.TRUE.equals( pValue ) ? 
                            DAOConstants.BOOLEAN_TRUE_IN_STR 
                            : DAOConstants.BOOLEAN_FALSE_IN_STR;
                }else {
                    return pValue.toString();
                }
            }else {
                return null;
            }
        }

        @Override
        @SuppressWarnings({ "rawtypes", "unchecked" })
        protected Class getDefaultType() {
            return String.class;
        }

        @Override
        @SuppressWarnings({ "rawtypes", "unchecked" })
        protected Object convertToType( Class type, Object value )
                throws Throwable {
            
            if( value == null ) {
                return Date.class.isAssignableFrom( type ) 
                    ? ( Timestamp.class.isAssignableFrom( type ) 
                            ? DAODatetimeUtils.TIMESTAMP_ZERO 
                                    : DAODatetimeUtils.DATETIME_ZERO )
                    : null;
            }
            
            if( type.isEnum() ) {           
                return DAOConstants.getEnumByValue( type, value.toString() );
            }
            
            return strConverter.convert( type, value );
        }

    }    
    
    private static class EnumConverter extends AbstractConverter {

        @Override
        protected String convertToString( final Object pValue ) 
                throws Throwable {
            
            return ( ( Enum<?> ) pValue ).toString();
        }

        @Override
        @SuppressWarnings({ "rawtypes", "unchecked" })
        protected Class getDefaultType() {
            return null;
        }

        @Override
        @SuppressWarnings({ "rawtypes", "unchecked" })
        protected Object convertToType( Class type, Object value )
                throws Throwable {
            
            if( value == null ) {
                return null;
            }
            
            return DAOConstants.getEnumByValue( type, value.toString() );
        }

    }    
    
    private static class DatetimeConverter extends AbstractConverter {

        @Override
        protected String convertToString( final Object pValue ) 
                throws Throwable {
            
            if( DAODatetimeUtils.isDatetimeZero( ( Date )pValue ) ) {
                return "";
            }
            
            return DAODatetimeUtils.formatDate( ( Date )pValue );
        }

        @Override
        @SuppressWarnings({ "rawtypes", "unchecked" })
        protected Class getDefaultType() {
            return null;
        }

        @Override
        @SuppressWarnings({ "rawtypes", "unchecked" })
        protected Object convertToType( Class type, Object value )
                throws Throwable {
            
            if( value == null || "".equals( value ) ) {
                return DAODatetimeUtils.TIMESTAMP_ZERO;
            }
            
            return DAODatetimeUtils.parseTimestamp( ( String )value );
        }

    }    
    
}
