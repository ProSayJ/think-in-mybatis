package com.prosayj.persistence.sqlsession.impl;

import com.prosayj.persistence.test.pojo.Configuration;
import com.prosayj.persistence.sqlsession.SqlSession;
import com.prosayj.persistence.sqlsession.SqlSessionFactory;
import lombok.AllArgsConstructor;

/**
 * 默认的 sql会话工厂实现
 *
 * @author yangjian
 * @date 2021-05-12
 */
@AllArgsConstructor
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration;


    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
