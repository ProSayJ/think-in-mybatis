package prosayj.mybatis.lite.sqlSession;

import prosayj.mybatis.lite.config.XMLConfigBuilder;
import prosayj.mybatis.lite.pojo.Configuration;
import prosayj.mybatis.lite.sqlSession.impl.DefaultSqlSessionFactory;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws Exception {

        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();

        Configuration configuration = xmlConfigBuilder.parse(inputStream);

        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);

        return defaultSqlSessionFactory;
    }

}
