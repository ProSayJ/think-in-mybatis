package prosayj.mybatis.lite.sqlSession;

import java.util.List;

public interface SqlSession {

     <T> T selectOne(String statementId, Object... params) throws Exception;

     <T> List<T> selectAll(String statementId, Object... params) throws Exception;

     <T> T getMapper(Class<?> mapperClass) throws Exception;


}
