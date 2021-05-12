package com.prosayj.sqlsession.impl;


import com.prosayj.pojo.BoundSql;
import com.prosayj.pojo.Configuration;
import com.prosayj.pojo.MappedStatement;
import com.prosayj.sqlsession.Executor;
import com.prosayj.utils.GenericTokenParser;
import com.prosayj.utils.ParameterMapping;
import com.prosayj.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * sql执行器-默认实现
 *
 * @author yangjian
 * @date 2021-05-12
 */
public class SimpleExecutor implements Executor {


    @Override
    @SuppressWarnings("unchecked")
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        // 1. 注册驱动，获取连接
        Connection connection = configuration.getDataSource().getConnection();

        // 2. 获取sql语句 :
        //2.1:原始sql：select * from user where id = #{id} and username = #{username}
        String orangeSql = mappedStatement.getSql();

        //2.2：转换sql语句： select * from user where id = ? and username = ? ，转换的过程中，还需要对#{}里面的值进行解析存储
        BoundSql boundSql = getBoundSql(orangeSql);

        // 3.获取预处理对象：preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        // 4. 设置参数
        //获取到了参数的全路径
        String paramterType = mappedStatement.getParamterType();
        Class<?> paramtertypeClass = getClassType(paramterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            //反射
            Field declaredField = paramtertypeClass.getDeclaredField(content);
            //暴力访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);
            preparedStatement.setObject(i + 1, o);
        }

        // 5. 执行sql
        ResultSet resultSet = preparedStatement.executeQuery();

        // 6. 封装返回结果集
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);
        List<Object> resultList = new ArrayList<>();
        while (resultSet.next()) {
            Object resultObject = resultTypeClass.newInstance();
            //元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                // 字段名和字段值
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(columnName);
                try {
                    //使用反射或者内省，根据数据库表和实体的对应关系，完成封装
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    writeMethod.invoke(resultObject, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            resultList.add(resultObject);
        }
        return (List<E>) resultList;
    }

    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        if (paramterType != null) {
            return Class.forName(paramterType);
        }
        return null;

    }


    /**
     * 完成对 #{}的解析工作：1.将 #{} 使用？进行代替，2.解析出#{}里面的值进行存储
     *
     * @param sql sql
     * @return BoundSql
     */
    private BoundSql getBoundSql(String sql) {
        //标记处理类：配置标记解析器来完成对占位符的解析处理工作
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        //解析出来的sql
        String parseSql = genericTokenParser.parse(sql);
        //#{}里面解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        return new BoundSql(parseSql, parameterMappings);

    }


}
