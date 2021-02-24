package prosayj.thinking.mybatis.simple.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prosayj.thinking.mybatis.simple.support.ExecuteInterface;
import prosayj.thinking.mybatis.simple.support.MybatisEnvInit;
import prosayj.thinking.mybatis.simple.model.Country;
import prosayj.thinking.mybatis.simple.model.CountryExample;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountryMapperTest extends MybatisEnvInit {

    public CountryMapperTest() {
        super("mybatis-config.xml");
    }


    /**
     * {@link org.apache.ibatis.jdbc.SqlRunner#selectAll(String, Object...)} ()}
     */
    @Test
    @DisplayName("00-testSelectAll")
    public void testSelectAll() {
        ((ExecuteInterface) () -> {
            List<Country> countryList = sqlSession.selectList("prosayj.thinking.mybatis.simple.mapper.CountryMapper.selectAll");
//            List<Country> countryList = sqlSession.selectList("selectAll");
            printCountryList(countryList);
        }).doExecuteSql(sqlSession,false);

    }

    @Test
    @DisplayName("01-testExample")
    public void testExample() {
        ((ExecuteInterface) () -> {
            // 获取 CountryMapper 接口
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            //创建 Example 对象
            CountryExample example = new CountryExample();
            //设置排序规则
            example.setOrderByClause("id desc, countryname asc");
            //设置是否 distinct 去重
            example.setDistinct(true);
            //创建条件，只能有一个 createCriteria
            CountryExample.Criteria criteria = example.createCriteria();
            //id >= 1
            criteria.andIdGreaterThanOrEqualTo(1);
            //id < 4
            criteria.andIdLessThan(4);
            //countrycode like '%U%'
            //最容易出错的地方，注意 like 必须自己写上通配符的位置，不可能默认两边加 %，like 可以是任何情况
            criteria.andCountrycodeLike("%U%");
            //or 的情况，可以有多个 or
            CountryExample.Criteria or = example.or();
            //countryname = 中国
            or.andCountrynameEqualTo("中国");
            //执行查询
            List<Country> countryList = countryMapper.selectByExample(example);
            printCountryList(countryList);
        }).doExecuteSql(sqlSession,false);

    }

    @Test
    @DisplayName("02-testUpdateByExampleSelective")
    public void testUpdateByExampleSelective() {
        ((ExecuteInterface) () -> {
            // 获取 CountryMapper 接口
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            //创建 Example 对象
            CountryExample example = new CountryExample();
            //创建条件，只能有一个 createCriteria
            CountryExample.Criteria criteria = example.createCriteria();
            //更新所有 id > 2 的国家
            criteria.andIdGreaterThan(2);
            //创建一个要设置的对象
            Country country = new Country();
            //将国家名字设置为 China
            country.setCountryname("China");
            //执行查询
            countryMapper.updateByExampleSelective(country, example);
            //在把符合条件的结果输出查看
            printCountryList(countryMapper.selectByExample(example));
        }).doExecuteSql(sqlSession,false);
    }

    @Test
    @DisplayName("03-testDeleteByExample")
    public void testDeleteByExample() {
        ((ExecuteInterface) () -> {
            // 获取 CountryMapper 接口
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            //创建 Example 对象
            CountryExample example = new CountryExample();
            //创建条件，只能有一个 createCriteria
            CountryExample.Criteria criteria = example.createCriteria();
            //删除所有 id > 2 的国家
            criteria.andIdGreaterThan(2);
            //执行查询
            countryMapper.deleteByExample(example);
            //使用 countByExample 查询符合条件的数量，因为删除了，所以这里应该是 0
            assertEquals(0, countryMapper.countByExample(example));
        }).doExecuteSql(sqlSession,false);


    }

    @Test
    @DisplayName("04-testMapperWithStartPage3")
    public void testMapperWithStartPage3() {
        ((ExecuteInterface) () -> {
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            //获取第1页，10条内容，默认查询总数count
            Map<String, Object> params = new HashMap<>();
            countryMapper.selectCountries(params);
            List<Country> list1 = (List<Country>) params.get("list1");
            List<Country> list2 = (List<Country>) params.get("list2");
            assertNotNull(list1);
            assertNotNull(list2);
        }).doExecuteSql(sqlSession,false);
    }

    private void printCountryList(List<Country> countryList) {
        for (Country country : countryList) {
            System.out.printf("%-4d%4s%4s\n", country.getId(), country.getCountryname(), country.getCountrycode());
        }
    }
}
