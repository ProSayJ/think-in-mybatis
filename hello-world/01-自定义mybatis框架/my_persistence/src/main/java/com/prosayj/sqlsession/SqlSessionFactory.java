package com.prosayj.sqlsession;

/**
 * sql会话工厂实现
 *
 * @author yangjian
 * @date 2021-05-12
 */
public interface SqlSessionFactory {

    /**
     * 获取会话
     *
     * @return SqlSession
     */
    SqlSession openSession();


}
