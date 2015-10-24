package com.saike.grape.dao.datas.transfer.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.saike.grape.dao.datas.transfer.abs.Transfer;

@Repository
public class TransferMain implements ApplicationContextAware {

    private static final Logger logger = 
            LoggerFactory.getLogger( TransferMain.class );
    
    private ApplicationContext appCtx;
    
    public final void transfer() {
        
        Connection conn = null;
        
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
            
            String url = "jdbc:mysql://localhost:3306/grape-prd"
                    + "?useUnicode=true"
                    + "&characterEncoding=UTF-8"
                    + "&allowMultiQueries=true";
            
            String userName = "root";
            String password = "root";
            
            conn = DriverManager.getConnection( url, 
                    userName, password ) ; 
            
            Map<String, Transfer> transfers = 
                    appCtx.getBeansOfType( Transfer.class );
            
            for( Transfer tf : transfers.values() ) {
                logger.warn( "transfer >>> " + tf.getClass().getName() );
                tf.transfer( conn );
            }
            
            for( Transfer tf : transfers.values() ) {
                logger.warn( "post transfer >>> " + tf.getClass().getName() );
                tf.postTransfer( conn );
            }
            
        }catch( Throwable ex ) {
            logger.error( "TransferMain.transfer exception: ", ex );
        }finally {
            if( conn != null ) {
                try {
                    conn.close();
                }catch( Exception ex ) {
                    
                }
            }
        }
        
        logger.info( "done!!!" );
    }

    @Override
    public void setApplicationContext( ApplicationContext appCtx )
            throws BeansException {
        
        this.appCtx = appCtx;
    }

}
