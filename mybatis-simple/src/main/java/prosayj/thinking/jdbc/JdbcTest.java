package prosayj.thinking.jdbc;

import prosayj.thinking.mybatis.simple.model.SysUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author yangjian
 * @date 2021-03-12 下午 05:13
 * @since 1.0.0
 */
public class JdbcTest {
    public static final String QUERY_SQL = "select id,user_name,user_password,user_email,user_info,head_img,create_time from sys_user;";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
         * 存储持久化数据常常会用到数据库
         * 传统数据库操作:
         * 加载驱动程序包
         * 与数据库建立连接
         * 拼装和执行 SQL语句
         * 获取数据库结果，数据转化操作
         * 关闭连接
         * */
        String url = "jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC";
        String userName = "root";
        String password = "root";
        //创建一个 user 对象
        SysUser insert = new SysUser();
        insert.setUserName("test-selective");
        insert.setUserPassword("123456");
        insert.setUserInfo("test info");
        insert.setCreateTime(new Date());

        // 第一步：加载驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 第二步：获得数据库的连接
        Connection conn = DriverManager.getConnection(url, userName, password);

        // 第三步：创建语句并执行
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(QUERY_SQL);

        // 第四步：处理数据库操作结果
        List<SysUser> userList = new ArrayList<>();
        while (resultSet.next()) {
            userList.add(new SysUser() {{
                setUserName(resultSet.getString("id"));
                setUserPassword(resultSet.getString("user_name"));
                setUserEmail(resultSet.getString("user_email"));
                setUserInfo(resultSet.getString("user_info"));
                setHeadImg(resultSet.getBytes("head_img"));
                setCreateTime(resultSet.getTime("create_time"));
            }});
        }
        // 第五步：关闭连接
        stmt.close();
        userList.forEach(System.out::println);

    }
}
