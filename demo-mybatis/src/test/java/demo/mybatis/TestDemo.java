package demo.mybatis;

import demo.mybatis.dao.ProductMapper;
import demo.mybatis.domain.Product;
import demo.mybatis.support.SqlMapperExecuteInterface;
import demo.mybatis.support.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * TestDemo
 *
 * @author yangjian
 * @date 2021-03-25 下午 04:36
 * @since 1.0.0
 */
public class TestDemo {
    public SqlSession sqlSession = null;

    @Before
    public void init() {
        sqlSession = SqlSessionFactoryUtil.openSqlSession("mybatis-config.xml");
    }

    @Test
    public void testInsert() {
        ((SqlMapperExecuteInterface) () -> {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            List<Product> result = productMapper.findByName("MyBatis课程");
            System.out.println(result);
        }).doExecuteSql(sqlSession, false);
    }
}
