package prosayj.thinking.mybatis.simple.base;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ExecuteInterface
 *
 * @author yangjian201127@credithc.com
 * @date 2021-01-05 9:44
 * @since 1.0.0
 */
@FunctionalInterface
public interface ExecuteInterface {
    Logger logger = LogManager.getLogger(ExecuteInterface.class.getName());

    void executeSql();

    default void doExecuteSql(SqlSession sqlSession, Boolean isRollback) {
        try {
            logger.info("开始执行sql");
            executeSql();
        } catch (Exception e) {
            logger.info("执行sql 出现异常");
            e.printStackTrace();
            assert sqlSession != null;
            sqlSession.rollback();
            logger.info("执行sql 出现异常 回归 成功");
        } finally {
            if (sqlSession != null) {
                if (isRollback) {
                    sqlSession.rollback();
                    logger.info("执行sql rollback 成功");
                } else {
                    //默认的 sqlSessionFactory.openSession() 是不自动提交的，
                    sqlSession.commit();
                    logger.info("执行sql commit 成功");
                }
            }
            logger.info("执行sql 结束");
        }
    }
}
