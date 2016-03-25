package com.saike.grape;

import java.util.HashMap;
import java.util.Map;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

/**
 *
 */
public class SparePartSQLMakerPluginImpl implements SQLMakerPlugin {

    private final Map<String, String> spareParts = new HashMap<>();

    private final Map<String, Object> sparePartHandled = new HashMap<>();
    
    @Override
    public boolean isSupported(String templateName, 
            Template tpl, 
            VelocityContext vtx) {
        
        return "t_basic_spare_part_grouped".equals( templateName ) 
                || "t_basic_spare_part".equals( templateName );
    }
    
    @Override
    public boolean beforeMakeWithTemplate( String templateName, Template tpl, VelocityContext vtx) {
        if( "t_basic_spare_part_grouped".equals( templateName ) ) {
            processGrouped( templateName, tpl, vtx );
            return true; // process 'grouped'
        }else if( "t_basic_spare_part".equals( templateName ) ) {
            return processSparePart( tpl, vtx );
        }
        return true;
    }

    protected boolean processGrouped( String templateName, Template tpl, VelocityContext vtx ) {
        
        String sparePartCode = ( String )vtx.get( "sparePartCode" );
        if( ! spareParts.containsKey( sparePartCode ) ) {
            spareParts.put( sparePartCode , ( String )vtx.get( "price" ) );
            return true; // continue processing by SQLMaker
        }
        String price = (String)vtx.get( "price" );
        if( spareParts.containsKey( sparePartCode + "（" + price + "）" ) ) {
            return false; // already been processed
        }
        
        if( Float.valueOf( spareParts.get( sparePartCode ) ).equals( 
                Float.valueOf( price ) ) ) {
            return false;
        }else {
            System.out.println( "   --> [ sparePartCode: " + sparePartCode 
                    + ", " + spareParts.get( sparePartCode ) 
                    + ", " + price 
                    + "]" );
            String code = sparePartCode + "（" + price + "）";
            vtx.put( "sparePartCode", code ); // sparePartCode+price as new sparePartCode
            spareParts.put( code, price );
            return true;
        }
    }
    
    protected boolean processSparePart( Template tpl, VelocityContext vtx ) {
        String sparePartCode = ( String )vtx.get( "sparePartCode" );
        boolean b = sparePartHandled.containsKey( sparePartCode );
        sparePartHandled.put( sparePartCode, Boolean.TRUE );
        return !b;
    }
    
}
