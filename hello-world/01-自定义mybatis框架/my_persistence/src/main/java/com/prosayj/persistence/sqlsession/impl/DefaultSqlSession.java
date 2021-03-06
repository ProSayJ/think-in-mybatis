package com.prosayj.persistence.sqlsession.impl;

import com.prosayj.persistence.test.pojo.Configuration;
import com.prosayj.persistence.test.pojo.MappedStatement;
import com.prosayj.persistence.sqlsession.SqlSession;
import lombok.AllArgsConstructor;

import java.lang.reflect.*;
import java.util.List;

/**
 * sql会话默认实现
 *
 * @author yangjian
 * @date 2021-05-12
 */
@SuppressWarnings("unchecked")
@AllArgsConstructor
public class DefaultSqlSession implements SqlSession {
    private final Configuration configuration;


    @Override
    public <E> List<E> selectList(String statementid, Object... params) throws Exception {
        //将要去完成对simpleExecutor里的query方法的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, params);
        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementid, Object... params) throws Exception {
        List<Object> objects = selectList(statementid, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            //  throw new RuntimeException("查询结果为空或者返回结果过多");
            return null;
        }


    }

    /**
     * 使用JDK动态代理来为 Dao 接口生成代理对象，并返回
     */
    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        Object proxyInstance = Proxy.newProxyInstance(
                DefaultSqlSession.class.getClassLoader(),
                new Class[]{mapperClass}, (proxy, method, args) -> {
                    // 底层都还是去执行JDBC代码 //根据不同情况，来调用selctList或者selectOne
                    // 准备参数 1：statmentid :sql语句的唯一标识：namespace.id= 接口全限定名.方法名
                    // 方法名：findAll
                    String methodName = method.getName();
                    String className = method.getDeclaringClass().getName();
                    String statementId = className + "." + methodName;
                    // 准备参数2：params:args
                    // 获取被调用方法的返回值类型
                    Type genericReturnType = method.getGenericReturnType();
                    // 判断是否进行了 泛型类型参数化
                    if (genericReturnType instanceof ParameterizedType) {
                        return selectList(statementId, args);
                    }
                    return selectOne(statementId, args);
                });
        return (T) proxyInstance;
    }
}
