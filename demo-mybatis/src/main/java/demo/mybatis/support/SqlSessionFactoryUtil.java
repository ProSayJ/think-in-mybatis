package demo.mybatis.support;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

public class SqlSessionFactoryUtil {

    public SqlSessionFactoryUtil() {
    }

    /**
     * 类线程锁
     */
    private static final Class<?> CLASS_LOCK = SqlSessionFactoryUtil.class;
    /**
     * SqlSessionFactory对象
     */
    private static SqlSessionFactory sqlSessionFactory = null;

    /**
     * 创建SqlSession实例
     *
     * @param configFileName configFileName
     * @return SqlSession
     */
    public static SqlSession openSqlSession(String configFileName) {
        if (sqlSessionFactory == null) {
            initInstance(configFileName);
        }
        return sqlSessionFactory.openSession();
    }


    /**
     * 初始化 SqlSessionFactory
     *
     * @param configFileName 配置文件名称
     */
    private static void initInstance(String configFileName) {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(configFileName);
        } catch (IOException e) {
            java.util.logging.Logger.getLogger(SqlSessionFactoryUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        synchronized (CLASS_LOCK) {
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }
        }
    }

}