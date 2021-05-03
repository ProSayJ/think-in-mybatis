package prosayj.mybatis.lite.sqlSession.impl;

import prosayj.mybatis.lite.pojo.Configuration;
import prosayj.mybatis.lite.sqlSession.SqlSession;
import prosayj.mybatis.lite.sqlSession.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {


    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
