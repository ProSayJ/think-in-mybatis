package com.prosayj.mybatisquickstarter.test;

import com.prosayj.mybatisquickstarter.dao.IUserDao;
import com.prosayj.mybatisquickstarter.pojo.SimpleUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 通过 mapper接口代理 获取sql
 *
 * @author yangjian
 * @date 2021-05-13
 */
public class MybatisTest02 {

    @Test
    @DisplayName("单表操作-通过mapper接口-查询所有用户")
    public void test5() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<SimpleUser> all = mapper.findAll();
        for (SimpleUser user : all) {
            System.out.println(user);
        }

    }

    @Test
    @DisplayName("单表操作-通过mapper接口-条件查询用户")
    public void test6() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        SimpleUser user1 = new SimpleUser();
        user1.setId(12);
        user1.setUserName("tom");
        List<SimpleUser> all = mapper.findByCondition(user1);
        for (SimpleUser user : all) {
            System.out.println(user);
        }


    }

    @Test
    @DisplayName("单表操作-通过mapper接口-条件查询用户")
    public void test7() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        int[] arr = {1, 12};
        List<SimpleUser> all = mapper.findByIds(arr);
        for (SimpleUser user : all) {
            System.out.println(user);
        }
    }


}
