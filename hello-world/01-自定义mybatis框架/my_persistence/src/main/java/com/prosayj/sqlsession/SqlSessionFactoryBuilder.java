package com.prosayj.sqlsession;

import com.prosayj.config.XmlConfigBuilder;
import com.prosayj.pojo.Configuration;
import com.prosayj.sqlsession.impl.DefaultSqlSessionFactory;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * sql会话工厂builder工厂
 *
 * @author yangjian
 * @date 2021-05-12
 */
public class SqlSessionFactoryBuilder {

    /**
     * 建造  SqlSessionFactory
     *
     * @param in 输入流配置文件
     * @return SqlSessionFactory
     * @throws DocumentException     异常
     * @throws PropertyVetoException 异常
     */
    public SqlSessionFactory build(InputStream in) throws DocumentException, PropertyVetoException {
        // 第一：使用dom4j解析配置文件，将解析出来的内容封装到 Configuration 中
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);

        // 第二：创建sqlSessionFactory对象：工厂类：生产sqlSession:会话对象
        return new DefaultSqlSessionFactory(configuration);
    }

}
