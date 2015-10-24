package com.saike.grape.dao.utils.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.saike.grape.dao.utils.DAOConstants;


/**
 * 值为字符串的枚举类型转换类
 */
public class StringValueEnumTypeHandler <T extends Enum<T>>
        extends BaseTypeHandler<T> {

    protected Class<T> enumTypeClass;
    
    public StringValueEnumTypeHandler( Class<T> enumTypeClass ) {
        
        if( enumTypeClass == null ) {
            throw new IllegalArgumentException( 
                    "Argument enumTypeClass is empty!!" );
        }
        
        this.enumTypeClass = enumTypeClass;
    }
    
    @Override
    public T getNullableResult(ResultSet rs,
            String columnName) throws SQLException {
        
        if( rs.wasNull() ) {
            return null;
        }
        
        return locateEnum( rs.getString( columnName ) );
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        
        if( rs.wasNull() ) {
            return null;
        }
        
        return locateEnum( rs.getString( columnIndex ) );
    }

    @Override
    public T getNullableResult(CallableStatement cs,
            int columnIndex) throws SQLException {
        
        if( cs.wasNull() ) {
            return null;
        }
        
        return locateEnum( cs.getString( columnIndex ) );
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter,
            JdbcType jdbcType) throws SQLException {
        
        ps.setString( i, parameter.toString() );
        
    }

    @SuppressWarnings("unchecked")
    protected T locateEnum( String value ) {
        return ( T )DAOConstants.getEnumByValue( enumTypeClass, value );
    }
    
}
