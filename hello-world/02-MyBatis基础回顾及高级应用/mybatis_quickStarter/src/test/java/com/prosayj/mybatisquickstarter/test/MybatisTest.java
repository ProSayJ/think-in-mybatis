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
 * 通过xml中的sqlID 获取sql
 *
 * @author yangjian
 * @date 2021-05-13
 */
public class MybatisTest {

    @Test
    @DisplayName("单表操作-通过SqlId-查询所有用户")
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
    @DisplayName("单表操作-通过SqlId-保存用户")
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
    @DisplayName("单表操作-通过SqlId-更新用户")
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
    @DisplayName("单表操作-通过方法的全限定类型，获取sql-删除用户")
    public void test4() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("com.prosayj.mybatisquickstarter.dao.IUserDao.deleteUser", 10);
        sqlSession.commit();
        sqlSession.close();
    }


}
