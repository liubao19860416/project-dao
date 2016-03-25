package com.saike.grape;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import com.google.gson.Gson;
import com.saike.grape.dao.utils.DAOUtils;

/**
 *
 */
public class SQLMaker {
    
    private static final String SUB_SYMBOL = "$";
    
    private static Path ctxPath;
    
    private final static Pattern P_TEMPLATE_NAME =
            Pattern.compile( "(.+)\\.(v|V)$" );

    private final Path basePath;
    
    private String module;
    
    private final VelocityEngine velocityEngine;
    private final VelocityContext velocityContext;
    
    private Map<String, Object> json;
    
    private StringBuilder sbd;
    
    private SQLMakerPlugin plugin;
    
    public SQLMaker( String module, String jsonFileName, SQLMakerPlugin plugin ) {
        
        this.plugin = plugin;
        
        this.module = module;
        
        this.basePath = ctxPath.resolve( "sql-make" );

        velocityEngine = new VelocityEngine();

        Properties properties = new Properties();

        properties.setProperty( VelocityEngine.FILE_RESOURCE_LOADER_PATH, 
                this.basePath.toString() );
        properties.setProperty( Velocity.ENCODING_DEFAULT, "UTF-8" );
        properties.setProperty( Velocity.INPUT_ENCODING, "UTF-8" );
        properties.setProperty( Velocity.OUTPUT_ENCODING, "UTF-8" ); 

        velocityEngine.init( properties );

        velocityContext = new VelocityContext();
        velocityContext.put( "D", "$" );
        velocityContext.put( "currentDatetime", 
                new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" ).format(
                        Calendar.getInstance().getTime() ) );
        
        try {
            this.json = loadJson( jsonFileName );
            for( String key : json.keySet() ) {
                velocityContext.put( key, json.get( key ) );
            }
        }catch( Exception ex ) {
            throw new RuntimeException( 
                    "cannot load the file: " + jsonFileName, ex );
        }
    }
    
    public SQLMaker process() {
        try {        
            sbd = new StringBuilder();
            handleJson( 
                generateVelocityContext( this.velocityContext, this.json ), 
                this.json );
        }catch( Exception ex ) {
            throw new RuntimeException( ex );
        }
        return this;
    }
    
    public String text() {
        return sbd != null ? sbd.toString() : "";
    }
    
    private void handleJson( VelocityContext vtx, 
            Map<String, Object> jsonObj ) {
        
        Object objs = jsonObj.get( SUB_SYMBOL );
        
        if( objs == null || ! ( objs instanceof List ) ) {
            return;
        }
        
        try {
            Template tpl = null;
            String templateName = null;
            
            for( Object subObj : ( List )objs ) {
                if( subObj == null ) {
                    continue;
                }
                
                if( subObj instanceof String ) {
                    templateName = String.valueOf( subObj );
                    tpl = getTemplate( templateName );
                    continue;
                }
                
                VelocityContext subVtx = generateVelocityContext( vtx, 
                        ( Map<String, Object> )subObj );
                        
                if( this.plugin != null 
                        && this.plugin.isSupported( templateName, tpl, subVtx)
                        && ! this.plugin.beforeMakeWithTemplate( templateName, tpl, subVtx ) ) {
                    continue;
                }
                
                makeWithTemplate( tpl, subVtx );
                
                // handle sub json objects
                handleJson( subVtx, ( Map<String, Object> )subObj );
            }
        }catch( Exception ex ) {
            throw new RuntimeException( ex );
        }
    }
    
    private Template getTemplate( String name ) {

//        Path tfile = contextPath().resolve( 
//                "../../src\\test\\resources\\sql-make/" + name + ".v" );
        
        return velocityEngine.getTemplate( name + ".v" );
    }
    
    private VelocityContext generateVelocityContext( VelocityContext parentCtx, 
            Map<String, Object> jsonObj ) {
        
        VelocityContext vtx = new VelocityContext( parentCtx );
        vtx.put( "code", DAOUtils.uuid() );
        vtx.put( "parentCode", parentCtx.get( "code" ) );
        
        for( String key : jsonObj.keySet() ) {
            if( isProperty( key ) ) {
                vtx.put( key, jsonObj.get( key ) );
            }
        }
        return vtx;
    }
    
    private boolean isProperty( String key ) {
        return key.charAt( 0 ) != '_' && ! SUB_SYMBOL.equalsIgnoreCase( key );
    }
    
    private void makeWithTemplate( Template template, 
            VelocityContext vctx ) throws IOException {
        
        StringWriter writer = new StringWriter();
        
        template.merge( vctx, writer );
        
        if( this.sbd.length() > 0 ) {
            this.sbd.append( "\r\n" );
        }
        this.sbd.append( writer );
    }
    
    private String evaluate( VelocityContext vctx, String initStr ) {
        StringWriter swr = new StringWriter();
        // evaluate file name
        velocityEngine.evaluate( vctx, 
                        swr, 
                        "sql-make", 
                        initStr );
        return swr.toString();
    }

    private Map<String, Object> loadJson( String jsonFileName ) 
            throws IOException {
        
        return new Gson().fromJson( 
              Files.newBufferedReader( 
                      basePath.resolve( this.module + "/" + jsonFileName ), 
                      Charset.forName( "UTF-8" ) ), 
              HashMap.class );
    }
    
    public static Path contextPath() {
        if( ctxPath == null ) {
            String path = Thread.currentThread().getContextClassLoader()
                    .getResource( "" ).getPath();

            if( path.startsWith( "/" ) ) {
                path = path.substring( 1 );
            }
            ctxPath = Paths.get( path );
        }
        return ctxPath;
    }
    
    public static void main( String[] args ) {
        try {
            final Pattern p = Pattern.compile( 
               //"(lotteryRecord[^_]+)\\.json$", Pattern.CASE_INSENSITIVE );
                    "(lotteryRecord_2015+)\\.json$", Pattern.CASE_INSENSITIVE );
            
            BufferedWriter wr = Files.newBufferedWriter( 
               contextPath().resolve( "../../src/main/resources/sqlmake.sql" ), 
                    Charset.forName( "UTF-8" ) );
            
            String module = "lottery";
            
            File dir = contextPath().resolve( "sql-make/" +module ).toFile();
            
            SQLMakerPlugin plugin = new SparePartSQLMakerPluginImpl();
            
            for( String name : dir.list() ) {
                Matcher m = p.matcher( name );
                if( m.matches() ) {
                    System.out.println( ">>> handling: " + name );
                    wr.append( "/*==============================================================*\r\n" )
                      .append( " * " + m.group( 1 ) + " *\r\n" )
                      .append( " *==============================================================*/\r\n" )
                      .append( new SQLMaker( module, name, plugin )
                                    .process()
                                    .text() )
                      .append( "\r\n" );
                }
            }
            
            wr.flush();
            wr.close();
            
            System.out.println( "~ done!!" );
            
        }catch( Exception ex ) {
            ex.printStackTrace();
        }
    }

}
