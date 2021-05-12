package com.prosayj.config;

import com.prosayj.io.Resources;
import com.prosayj.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.RequiredArgsConstructor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * mybatis-xml配置文件读取器
 *
 * @author yangjian
 * @date 2021-05-11
 */
public class XmlConfigBuilder {
    /**
     * 自定义配置类
     */
    private final Configuration configuration;
    /**
     * 文档解析器
     */
    private Document document;

    /**
     * 构造注入
     */
    public XmlConfigBuilder() {
        this.configuration = new Configuration();
    }

    /**
     * 该方法就是使用dom4j对配置文件进行解析，封装Configuration
     *
     * @param inputStream inputStream
     * @return Configuration
     * @throws DocumentException     DocumentException
     * @throws PropertyVetoException PropertyVetoException
     */
    public Configuration parseConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
        //初始化文档解析器
        this.initDocumentParser(inputStream);
        //加载数据源配置
        this.loadDatasourceProperties();
        //加载sql-mapper配置文件
        this.loadMappersList();
        return configuration;
    }

    /**
     * 初始化文档解析器
     *
     * @param inputStream inputStream
     * @throws DocumentException DocumentException
     */
    private void initDocumentParser(InputStream inputStream) throws DocumentException {
        //<configuration>
        this.document = new SAXReader().read(inputStream);
    }

    /**
     * 获取SqlMapConfig 中的 mappers配置
     */
    @SuppressWarnings("unchecked")
    private void loadMappersList() throws DocumentException {
        Element rootElement = document.getRootElement();
        List<Element> rootMapper = rootElement.selectNodes("//mappers");
        //获取SqlMapConfig.xml 配置中 sql.xml 配置文件 如：UserMapper.xml、ProductMapper.xml 等
        List<Element> mappers = rootMapper.get(0).selectNodes("//mapper");
        for (Element mapper : mappers) {
            String mapperPath = mapper.attributeValue("resource");
            InputStream resourceAsSteam = Resources.getResourceAsSteam(mapperPath);
            XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsSteam);
        }
    }

    /**
     * 加载数据源配置
     */
    @SuppressWarnings("unchecked")
    private void loadDatasourceProperties() throws PropertyVetoException {
        Element rootElement = document.getRootElement();
        List<Element> dataSourceMatedatas = rootElement.selectNodes("//dataSource");
        List<Element> dataSourceMatedata = dataSourceMatedatas.get(0).selectNodes("//property");
        Properties datasourceProperties = new Properties();
        for (Element element : dataSourceMatedata) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            datasourceProperties.setProperty(name, value);
        }
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(datasourceProperties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(datasourceProperties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(datasourceProperties.getProperty("user"));
        comboPooledDataSource.setPassword(datasourceProperties.getProperty("password"));
        configuration.setDataSource(comboPooledDataSource);
    }


}
