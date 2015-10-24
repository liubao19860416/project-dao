package com.saike.grape.dao.interceptor;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mybatis物理分页拦截类 
 * 
 * 不需要设置分页参数，它会自动进行添加的
 * 
 * @Signature，即拦截点。
 * 定义了该Interceptor将拦截StatementHandler接口中参数类型为Connection的prepare方法；
 * 
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {

    //工厂类
    private ObjectFactory objectFactory = new DefaultObjectFactory();
    //包装工厂类
    private ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @SuppressWarnings("unused")
    private Properties properties;

    /**
     * 拦截器首先需要执行的方法；就是要进行拦截的时候要执行的方法
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //获取代理对象
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, objectFactory, objectWrapperFactory);

        RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");

        if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
            return invocation.proceed();
        }

        //获取当前访问的sql语句
        String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

        String boundSql = originalSql + " limit " + rowBounds.getOffset() + "," + rowBounds.getLimit();

        metaStatementHandler.setValue("delegate.boundSql.sql", boundSql);
        metaStatementHandler.setValue("delegate.rowBounds.offset",RowBounds.NO_ROW_OFFSET);
        metaStatementHandler.setValue("delegate.rowBounds.limit",RowBounds.NO_ROW_LIMIT);

        //继续执行
        return invocation.proceed();
    }

    /**
     * plugin方法是拦截器用于封装目标对象的；通过该方法我们可以返回目标对象本身，也可以返回一个它的代理。
     * 当返回的是代理的时候我们可以对其中的方法进行拦截来调用intercept方法，当然也可以调用其他方法
     * 在plugin方法中我们可以决定是否要进行拦截进而决定要返回一个什么样的目标对象
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 用于在Mybatis配置文件中指定一些属性的
     */
    @Override
    public void setProperties(Properties properties) {
        this.properties=properties;

    }
    

}
