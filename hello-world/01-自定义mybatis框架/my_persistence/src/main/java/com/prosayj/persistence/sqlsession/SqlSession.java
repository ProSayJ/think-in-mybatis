package com.prosayj.persistence.sqlsession;

import java.util.List;

/**
 * sql会话
 *
 * @author yangjian
 * @date 2021-05-12
 */
public interface SqlSession {

    /**
     * 查询所有
     *
     * @param statementid statementid
     * @param params      params
     * @param <E>         <E>
     * @return <E>        <E>
     * @throws Exception Exception
     */
    <E> List<E> selectList(String statementid, Object... params) throws Exception;

    /**
     * 根据条件查询单个
     *
     * @param statementid statementid
     * @param params      params
     * @param <T>         <T>
     * @return <T>        <T>
     * @throws Exception Exception
     */
    <T> T selectOne(String statementid, Object... params) throws Exception;


    /**
     * 为 Dao接口生成代理实现类
     *
     * @param mapperClass mapperClass
     * @param <T>         <T>
     * @return <T>        <T>
     */
    <T> T getMapper(Class<?> mapperClass);


}
