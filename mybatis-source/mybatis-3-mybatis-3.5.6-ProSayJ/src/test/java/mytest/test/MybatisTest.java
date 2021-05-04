package mytest.test;

import mytest.mapper.IOrderMapper;
import mytest.mapper.IUserMapper;
import mytest.pojo.Order;
import mytest.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

  /*
      测试一对一查询
   */
  @Test
  public void test1() throws IOException {
    InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    IOrderMapper mapper = sqlSession.getMapper(IOrderMapper.class);
    List<Order> orderAndUser = mapper.findOrderAndUser();
    for (Order order : orderAndUser) {
      System.out.println(order);
    }
  }

  /*
      测试一对多查询
   */
  @Test
  public void test2() throws IOException {
    InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);

    List<User> users = mapper.findAll();
    for (User user : users) {
      System.out.println("user.getUsername() = " + user.getUsername());
      System.out.println("user.getOrderList() = " + user.getOrderList());
      System.out.println("============");
    }
  }
}



