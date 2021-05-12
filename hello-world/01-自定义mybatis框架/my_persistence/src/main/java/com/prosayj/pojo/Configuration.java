package com.prosayj.pojo;

import lombok.Data;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * mybatis-配置类信息
 *
 * @author yangjian
 * @date 2021-05-11
 */
@Data
public class Configuration {

    /**
     * 数据源信息
     */
    private DataSource dataSource;

    /**
     * key: statementid  value:封装好的mappedStatement对象
     */
    private Map<String, MappedStatement> mappedStatementMap = new HashMap<>();

}
