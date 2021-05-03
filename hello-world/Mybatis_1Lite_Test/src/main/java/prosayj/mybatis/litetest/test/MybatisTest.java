package prosayj.mybatis.litetest.test;

import prosayj.mybatis.lite.io.Resources;
import prosayj.mybatis.lite.sqlSession.SqlSession;
import prosayj.mybatis.lite.sqlSession.SqlSessionFactory;
import prosayj.mybatis.lite.sqlSession.SqlSessionFactoryBuilder;
import prosayj.mybatis.litetest.pojo.User;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {


    @Test
    public void test() throws Exception {
        InputStream inputStream = Resources.getResourcesAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectAll("UserMapper.selectAll");
        users.forEach(System.out::println);
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        User queryResult = sqlSession.selectOne("UserMapper.selectOne", user);
        System.out.println(queryResult);
    }


}
