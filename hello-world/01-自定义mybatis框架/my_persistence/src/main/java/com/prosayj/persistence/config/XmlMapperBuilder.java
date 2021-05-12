package com.prosayj.persistence.config;

import com.prosayj.persistence.test.pojo.Configuration;
import com.prosayj.persistence.test.pojo.MappedStatement;
import lombok.AllArgsConstructor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * xml映射构建
 *
 * @author yangjian
 * @date 2021-05-12
 */
@AllArgsConstructor
public class XmlMapperBuilder {

    /**
     * mybatis-配置类信息
     */
    private final Configuration configuration;

    /**
     * 解析xml-mapper配置并缓存到配置类Configuration中
     *
     * @param inputStream xml源
     * @throws DocumentException 文档解析异常
     */
    @SuppressWarnings("unchecked")
    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> list = rootElement.selectNodes("//select");
        for (Element element : list) {
            MappedStatement mappedStatement = new MappedStatement();
            String sqlStatementId = element.attributeValue("id");
            mappedStatement.setId(sqlStatementId);
            mappedStatement.setResultType(element.attributeValue("resultType"));
            mappedStatement.setParamterType(element.attributeValue("paramterType"));
            mappedStatement.setSql(element.getTextTrim());
            String key = namespace + "." + sqlStatementId;
            configuration.getMappedStatementMap().put(key, mappedStatement);
        }
    }


}
