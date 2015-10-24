package com.saike.grape.dao.interceptor2;

/**
 * 自动判断当前数据库是mysql或者是Oracle类型
 * @author Liubao
 * @2014年12月3日
 *
 */
public abstract class Dialect {
    //枚举类型定义
    public static enum Type {
        MYSQL, ORACLE
    }

    public abstract String getLimitString(String sql, int skipResults,int maxResults);
}
