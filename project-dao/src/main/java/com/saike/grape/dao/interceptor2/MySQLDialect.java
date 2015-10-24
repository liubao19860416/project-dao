package com.saike.grape.dao.interceptor2;


/**
 * mysql的添加分页参数
 */
public class MySQLDialect extends Dialect{

    @Override
    public String getLimitString(String sql, int skipResults, int maxResults) {
        sql = sql.trim();
        StringBuffer stringBuffer = new StringBuffer(sql.length() + 100);

        stringBuffer.append(sql);
        stringBuffer.append(" limit "+skipResults+","+maxResults+" ");

        return stringBuffer.toString();
    }

  
}
