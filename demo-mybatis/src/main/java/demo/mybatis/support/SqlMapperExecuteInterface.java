package demo.mybatis.support;

import org.apache.ibatis.session.SqlSession;


@FunctionalInterface
public interface SqlMapperExecuteInterface {
    void executeSql();

    default void doExecuteSql(SqlSession sqlSession, Boolean isRollback) {
        try {
            System.out.println("开始执行sql");
            executeSql();
        } catch (Exception e) {
            System.out.println("执行sql 出现异常");
            e.printStackTrace();
            assert sqlSession != null;
            sqlSession.rollback();
            System.out.println("执行sql 出现异常 回滚 成功");
        } finally {
            if (sqlSession != null) {
                if (isRollback) {
                    sqlSession.rollback();
                    System.out.println("执行sql rollback 成功");
                } else {
                    //默认的 sqlSessionFactory.openSession() 是不自动提交的，
                    sqlSession.commit();
                    System.out.println("执行sql commit 成功");
                }
                sqlSession.close();
                System.out.println("执行sql sqlSessionClose 成功");
            }
            System.out.println("执行sql 结束");
        }
    }
}
