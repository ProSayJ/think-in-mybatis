package prosayj.mybatis.lite.sqlSession;

import prosayj.mybatis.lite.pojo.Configuration;
import prosayj.mybatis.lite.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

public interface Executor {

    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws SQLException, Exception;
}
