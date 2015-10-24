package com.saike.grape.dao.interceptor2;

/**
 * 使用于Oracle数据库，拼装分页参数信息
 * 
 * @author Liubao
 * @2014年12月3日
 * 
 */
public class OracleDialect extends Dialect {

    @Override
    public String getLimitString(String sql, int offset, int limit) {

        sql = sql.trim();
        StringBuffer stringBuffer = new StringBuffer(sql.length() + 100);

        stringBuffer.append("select * from ( select row_.*, rownum rownum_ from ( ");

        stringBuffer.append(sql);

        stringBuffer.append(" ) row_ ) where rownum_ > ").append(offset)
                .append(" and rownum_ <= ").append(offset + limit);

        return stringBuffer.toString();
    }

}
