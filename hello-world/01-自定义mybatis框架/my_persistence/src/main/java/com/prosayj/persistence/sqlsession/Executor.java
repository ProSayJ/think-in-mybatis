package com.prosayj.persistence.sqlsession;

import com.prosayj.persistence.test.pojo.Configuration;
import com.prosayj.persistence.test.pojo.MappedStatement;

import java.util.List;

/**
 * sql执行器
 *
 * @author yangjian
 * @date 2021-05-12
 */
public interface Executor {

    /**
     * 通用sql执行接口
     *
     * @param configuration   配置类信息
     * @param mappedStatement sql映射
     * @param params          参数
     * @param <E>             返回值
     * @return 返回值
     * @throws Exception Exception
     */
    <E> List<E> query(
            Configuration configuration,
            MappedStatement mappedStatement, Object... params) throws Exception;

}
