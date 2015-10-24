package com.saike.grape.sql.generator;


import org.junit.Test;

import com.saike.grape.service.genertic.ServiceBasicTest;
/**
 * 调用方法
 * @author Liubao
 * @2014年12月5日
 *
 */
public class GeneratorSqlMapperTest extends ServiceBasicTest {
    
    @Test
    public void testGeneratorSqlMapper() {
        try {
            GenerateSqlMapper generatorSqlmap = new GenerateSqlMapper();
            generatorSqlmap.generator();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换失败!!!");
        }

    }
}
