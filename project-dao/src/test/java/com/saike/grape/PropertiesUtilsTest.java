package com.saike.grape;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.saike.grape.base.BaseEnvironment;
import com.saike.grape.base.configure.CustomizedPropertyPlaceholderConfigurer;
import com.saike.grape.service.genertic.ServiceBasicTest;

public class PropertiesUtilsTest extends ServiceBasicTest {

    @Test
    public void test01() throws Exception {
        Map<String, String> map = System.getenv();
        System.out.println(System.getProperty("10002"));// null

        map = CustomizedPropertyPlaceholderConfigurer.ctxPropertiesMap;
        if (map != null) {
            for (String key : map.keySet()) {
                String value = map.get(key);
                System.out.println(key + "====>>>>" + value);
            }
        } else {
            LoggerFactory.getLogger(this.getClass()).info("传递的参数map为空！");
        }
    }

    @Test
    public void testBaseEnvironment() throws Exception {
        System.out.println(BaseEnvironment.SYS_ENV);
        Assert.assertEquals("LOCAL", BaseEnvironment.SYS_ENV);
    }
}
