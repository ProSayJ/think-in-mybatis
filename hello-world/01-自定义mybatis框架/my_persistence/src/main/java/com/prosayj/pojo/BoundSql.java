package com.prosayj.pojo;

import com.prosayj.utils.ParameterMapping;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 动态参数 绑定 SQL 模板
 *
 * @author yangjian
 * @date 2021-05-11
 */
@Data
@AllArgsConstructor
public class BoundSql {
    /**
     * 解析过后[]的sql:select * from user where id = ? and username = ?
     */
    private String sqlText;
    /**
     * 参数映射集合:[{content=id},{content=username}]
     */
    private List<ParameterMapping> parameterMappingList;

}
