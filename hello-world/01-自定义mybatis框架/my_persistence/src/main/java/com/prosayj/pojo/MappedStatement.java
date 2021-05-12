package com.prosayj.pojo;

import lombok.Data;

/**
 * mybatis-sql映射
 *
 * @author yangjian
 * @date 2021-05-11
 */
@Data
public class MappedStatement {
    /**
     * xml 中 sql 语句的 id标识
     */
    private String id;
    /**
     * 返回值类型
     */
    private String resultType;
    /**
     * 参数值类型
     */
    private String paramterType;
    /**
     * sql语句
     */
    private String sql;
}
