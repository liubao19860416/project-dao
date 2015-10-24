package com.saike.grape.service.utils;

import com.saike.grape.dao.utils.DAOUtils;

/**
 * Grape工具类
 */
public final class ServiceUtils {

    // hide from initiating
    private ServiceUtils() {}
    
    
    public static void copyProperties( Object dest, Object orig ) {
        
        try {
            
            DAOUtils.copyProperties( dest, orig );
            
        } catch ( Exception ex ) {
            throw new IllegalArgumentException( ex );
        }
    }
    
}
