package com.prosayj.test;

import com.prosayj.dao.IUserDao;
import com.prosayj.pojo.SimpleUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    @Test
    public void test1() throws IOException {
        //1.Resources工具类，配置文件的加载，把配置文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.解析了配置文件，并创建了sqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.生产sqlSession，默认开启一个事务，但是该事务不会自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //在进行增删改操作时，要手动提交事务
        //4.sqlSession调用方法：查询所有selectList;查询单个：selectOne; 添加：insert;修改：update;删除：delete
        //List<User> users = sqlSession.selectList("user.findAll");
        List<SimpleUser> users = sqlSession.selectList("findAll");
        for (SimpleUser user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }


    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //事务自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        SimpleUser user = new SimpleUser();
        user.setUserName("tom");
        //sqlSession.insert("user.saveUser", user);
        sqlSession.insert("saveUser", user);
        sqlSession.close();
    }

    @Test
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SimpleUser user = new SimpleUser();
        user.setId(10);
        user.setUserName("lucy");
        //sqlSession.update("user.updateUser", user);
        sqlSession.update("updateUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test4() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        sqlSession.delete("com.prosayj.dao.IUserDao.deleteUser", 10);
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
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
    public void test6() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao mapper = sqlSession.getMapper(IUserDao.class);

        SimpleUser user1 = new SimpleUser();
        user1.setId(10);
        user1.setUserName("lucy");

        List<SimpleUser> all = mapper.findByCondition(user1);
        for (SimpleUser user : all) {
            System.out.println(user);
        }


    }

    @Test
    public void test7() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        int[] arr = {2, 10};
        List<SimpleUser> all = mapper.findByIds(arr);
        for (SimpleUser user : all) {
            System.out.println(user);
        }

    }


}
