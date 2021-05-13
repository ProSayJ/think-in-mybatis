package com.prosayj.mybatismultitable.test;

import com.prosayj.mybatismultitable.mapper.IOrderMapper;
import com.prosayj.mybatismultitable.pojo.Order;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MybatisTest {
    @Test
    @DisplayName("使用注解：一对多查询：查询订单和订单关联用户")
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

//    @Test
//    @DisplayName("一对多查询：查询用户和用户关联的订单")
//    public void test2() throws IOException {
//        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
//        List<User> all = mapper.findAll();
//        for (User user : all) {
//            System.out.println(user);
//        }
//
//    }
//
//    @Test
//    @DisplayName("一对多查询：用户和用户关联角色")
//    public void test3() throws IOException {
//        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
//        List<User> allUserAndRole = mapper.findAllUserAndRole();
//        for (User user : allUserAndRole) {
//            System.out.println(user);
//        }
//    }
//
//
//    private IUserMapper userMapper;
//    private IOrderMapper orderMapper;
//
//    @BeforeAll
//    public void befor() throws IOException {
//        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession(true);
//        userMapper = sqlSession.getMapper(IUserMapper.class);
//        orderMapper = sqlSession.getMapper(IOrderMapper.class);
//    }
//
//    @Test
//    @DisplayName("新增用户")
//    public void addUser() {
//        User user = new User();
//        user.setId(3);
//        user.setUsername("测试数据");
//        userMapper.addUser(user);
//    }
//
//    @Test
//    @DisplayName("更新用户")
//    public void updateUser() {
//        User user = new User();
//        user.setId(3);
//        user.setUsername("修改了测试数据");
//        userMapper.updateUser(user);
//
//    }
//
//    @Test
//    @DisplayName("查询所有用户")
//    public void selectUser() {
//        List<User> users = userMapper.selectUser();
//        for (User user : users) {
//            System.out.println(user);
//        }
//    }
//
//    @Test
//    @DisplayName("删除id=3的用户")
//    public void deleteUser() {
//        userMapper.deleteUser(3);
//    }
//
//
//    @Test
//    public void oneToOne() {
//        List<Order> orderAndUser = orderMapper.findOrderAndUser();
//        for (Order order : orderAndUser) {
//            System.out.println(order);
//        }
//
//    }
//
//    @Test
//    public void oneToMany() {
//        List<User> all = userMapper.findAll();
//        for (User user : all) {
//            System.out.println(user);
//        }
//
//    }
//
//    @Test
//    public void ManyToMany() {
//        List<User> all = userMapper.findAllUserAndRole();
//        for (User user : all) {
//            System.out.println(user);
//        }
//
//    }
//
//    @Test
//    public void pageHelperTest() {
//
//        PageHelper.startPage(1, 1);
//        List<User> users = userMapper.selectUser();
//        for (User user : users) {
//            System.out.println(user);
//        }
//
//        PageInfo<User> pageInfo = new PageInfo<>(users);
//        System.out.println("总条数：" + pageInfo.getTotal());
//        System.out.println("总页数：" + pageInfo.getPages());
//        System.out.println("当前页：" + pageInfo.getPageNum());
//        System.out.println("每页显示的条数：" + pageInfo.getPageSize());
//
//
//    }
//
//    @Test
//    public void mapperTest() throws IOException {
//        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        User user = new User();
//        user.setId(1);
//        User user1 = mapper.selectOne(user);
//        System.out.println(user1);
//
//
//        //2.example方法
//        Example example = new Example(User.class);
//        example.createCriteria().andEqualTo("id", 1);
//        List<User> users = mapper.selectByExample(example);
//        for (User user2 : users) {
//            System.out.println(user2);
//        }
//
//    }


}



