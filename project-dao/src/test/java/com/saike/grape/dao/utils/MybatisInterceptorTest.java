package com.saike.grape.dao.utils;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MybatisInterceptorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();
        Map<String, String> pluginedMap = 
                ( Map<String, String> )new TestInterceptor().plugin( map );
        assertEquals( "test", pluginedMap.get( "xxx" ) );
        assertNull( map.get( "xxx" ) );
    }

    
    @Intercepts({
        @Signature( type = Map.class, method = "get", args = {Object.class} )
    })
    private static class TestInterceptor implements Interceptor {

        @Override
        public Object intercept(Invocation invocation) throws Throwable {
            
            return "test";
        }

        @Override
        public Object plugin(Object target) {
            return Plugin.wrap(target, this);
        }

        @Override
        public void setProperties(Properties properties) {
            // TODO Auto-generated method stub
            
        }
        
    }
    
}
