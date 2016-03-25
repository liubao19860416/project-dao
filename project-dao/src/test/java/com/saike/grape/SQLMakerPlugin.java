package com.saike.grape;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

/**
 *
 * @author zengwei
 */
public interface SQLMakerPlugin {
    
    boolean isSupported( String templateName, 
            Template tpl, 
            VelocityContext vtx );
    
    boolean beforeMakeWithTemplate( String templateName, 
            Template tpl, 
            VelocityContext vtx );
    
}
