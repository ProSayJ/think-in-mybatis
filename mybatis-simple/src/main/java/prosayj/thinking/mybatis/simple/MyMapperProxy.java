package prosayj.thinking.mybatis.simple;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

/**
 * Mapper接口动态代理实现原理
 *
 * @param <T>
 */
public class MyMapperProxy<T> implements InvocationHandler {
    private final Class<T> mapperInterface;
    private final SqlSession sqlSession;

    public MyMapperProxy(Class<T> mapperInterface, SqlSession sqlSession) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //针对不同的 sql 类型，需要调用 sqlSession 不同的方法
        //参数也有很多情况，这里只考虑一个参数的情况
        //返回值也有很多情况
        return sqlSession.selectList(mapperInterface.getCanonicalName() + "." + method.getName());
    }
}
