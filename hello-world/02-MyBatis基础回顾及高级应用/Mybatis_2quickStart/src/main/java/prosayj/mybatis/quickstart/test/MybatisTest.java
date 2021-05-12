package prosayj.mybatis.quickstart.test;


import prosayj.mybatis.quickstart.dao.UserDao;
import prosayj.mybatis.quickstart.dao.UserDaoImpl;
import prosayj.mybatis.quickstart.mapper.UserMapper;
import prosayj.mybatis.quickstart.pojo.SimpleUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    private static InputStream inputStream = null;
    private static SqlSessionFactory build = null;

    @BeforeAll
    public static void BeforeTest() throws IOException {
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        build = new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Test
    public void test() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = build.openSession();
        List<SimpleUser> users = sqlSession.selectList("findAll");
        users.forEach(System.out::println);
    }

    @Test
    public void test1() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = build.openSession();
        SimpleUser user = new SimpleUser(5, "insert");
        sqlSession.insert("insertUser", user);
        sqlSession.commit();
    }

    @Test
    public void test2() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = build.openSession();
        SimpleUser user = new SimpleUser(2, "update");
        sqlSession.update("updateUser", user);
        sqlSession.commit();

    }

    @Test
    public void test3() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = build.openSession();
        sqlSession.delete("deleteUser", 1);
        sqlSession.commit();

    }


    @Test
    public void test4() throws IOException {
        UserDao userDao = new UserDaoImpl();
        List<SimpleUser> users = userDao.findAll();
        users.forEach(System.out::println);
    }

    @Test
    public void test5() throws IOException {
        SqlSession sqlSession = build.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<SimpleUser> users = mapper.findAll();
        users.forEach(System.out::println);
    }

    @Test
    public void test6() throws Exception {
        SqlSession sqlSession = build.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        SimpleUser user = new SimpleUser();
        user.setId(2);
        user.setUsername("123");
        List<SimpleUser> users = mapper.findByIf(user);
        users.forEach(System.out::println);
    }

    @Test
    public void test7() throws Exception {
        SqlSession sqlSession = build.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        SimpleUser user = new SimpleUser();
        Integer[] arr = {2, 3, 4};
        List<SimpleUser> users = mapper.findByIds(arr);
        users.forEach(System.out::println);
    }


}
