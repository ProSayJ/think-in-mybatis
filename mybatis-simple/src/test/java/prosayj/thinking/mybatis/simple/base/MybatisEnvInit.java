package prosayj.thinking.mybatis.simple.base;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import prosayj.thinking.mybatis.util.SqlSessionFactoryUtil;

/**
 * MybatisEnvInit
 *
 * @author yangjian201127@credithc.com
 * @ BeforeAll 和 @AfterAll默认是添加到静态方法上，
 * @ BeforeAll 注释的静态方法会在执行所有测试用例之前调用，
 * @ AfterAll 注释的静态方法会在所有的测试用例完成后调用。
 * 它们是在应用级别，应用于所有实例对象。
 * @date 2021-01-05 9:38
 * @since 1.0.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MybatisEnvInit {
    public static final Object object = new Object();
    public final Logger logger = LogManager.getLogger(getClass().getName());
    public SqlSession sqlSession = null;
    public String configFileName;

    public MybatisEnvInit(String configFileName) {
        this.configFileName = configFileName;
    }

    @BeforeAll
    public void initSqlSession() {
        if (configFileName == null) {
            logger.error("配置文件加载错误");
            return;
        }
        sqlSession = SqlSessionFactoryUtil.openSqlSession(configFileName);
        logger.info("初始化sqlSession成功");
    }


    @AfterAll
    public void distorySqlSession() {
        if (sqlSession != null) {
            sqlSession.close();
            logger.info("销毁sqlSession成功");
        }
    }

}
