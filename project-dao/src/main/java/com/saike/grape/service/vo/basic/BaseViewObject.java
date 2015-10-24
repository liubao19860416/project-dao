package com.saike.grape.service.vo.basic;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * 视图类基类
 */
public class BaseViewObject<V extends BaseViewObject<V>> 
        implements Serializable {

    private static final long serialVersionUID = 8950660914544659827L;

    private Class<V> viewObjectClass;
    
    private Long id;
    private String code;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @SuppressWarnings("unchecked")
    public BaseViewObject() {
        Type type = this.getClass().getGenericSuperclass();
        if( type != null && type instanceof ParameterizedType ) {
            viewObjectClass = ( Class<V> )( ( ParameterizedType )type )
                    .getActualTypeArguments()[0];
        }else {
            throw new IllegalArgumentException( 
                    "Type argument <V> is empty!!" );
        }
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString( this, 
                ToStringStyle.MULTI_LINE_STYLE );
    }
    
    @SuppressWarnings("unchecked")
    @Override
    final public boolean equals( Object object ) {
        boolean b = false;
        if( object == null ) {
            b = false;
        }else if( object == this ) {
            b = true;
        }else if( object.getClass() == this.viewObjectClass ) {
            b = equals( ( V )object );
        }
        return b;
    }
    
    public boolean equals( V object ) {
        return super.equals( object );
    }
    
    
}
