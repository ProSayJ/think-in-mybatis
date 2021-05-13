package prosayj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * JdbcTest
 *
 * @author yangjian
 * @date 2021-05-13 上午 10:54
 * @since 1.0.0
 */
public class JdbcTest {
    public static void main(String[] args) {
        try {
            // 1.加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.创建并获取数据库链接  &amp;  &
            Connection conn = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/mybatis?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=false&characterEncoding=utf-8",
                            "root", "root");
            // 3.设置SQL语句 ?表示占位符
            String sql = "select * from user where id = ?";
            // 4. 创建statement对象
            PreparedStatement statement = conn.prepareStatement(sql);
            // 5.设置参数，第一个参数为SQL语句中参数的序号（从1开始），第二个参数为设置的参数值
            statement.setInt(1, 1);
            // 6.执行SQL语句，得到结果集
            ResultSet resultSet = statement.executeQuery();
            // 7.遍历结果
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString("id") + "  " +
                                resultSet.getString("username") + "  " +
                                resultSet.getString("password") + "  " +
                                resultSet.getString("birthday"));
            }
            // 8.关闭资源
            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
