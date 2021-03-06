package com.prosayj.persistence.test;

import com.prosayj.persistence.io.Resources;
import com.prosayj.persistence.sqlsession.SqlSession;
import com.prosayj.persistence.sqlsession.SqlSessionFactory;
import com.prosayj.persistence.sqlsession.SqlSessionFactoryBuilder;
import com.prosayj.persistence.test.dao.IUserDao;
import com.prosayj.persistence.test.pojo.User;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();


        List<User> users = sqlSession.selectList("com.prosayj.persistence.test.dao.IUserDao.findAll");
        users.forEach(System.out::println);


        User user = new User();
        user.setId(2);
        user.setUsername("update");
        User queryResult = sqlSession.selectOne("com.prosayj.persistence.test.dao.IUserDao.findByCondition", user);
        System.out.println(queryResult);







        //调用
        User user2 = new User();
        user2.setId(1);
        user2.setUsername("张三");
      /*  User user2 = sqlSession.selectOne("user.selectOne", user);

        System.out.println(user2);*/

       /* List<User> users = sqlSession.selectList("user.selectList");
        for (User user1 : users) {
            System.out.println(user1);
        }*/

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }


    }


}
