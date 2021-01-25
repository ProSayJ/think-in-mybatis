package prosayj.thinking.mybatis.simple.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import prosayj.thinking.mybatis.simple.model.Country;
import prosayj.thinking.mybatis.simple.model.CountryExample;

public interface CountryMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table country
	 * @mbggenerated
	 */
	int countByExample(CountryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table country
	 * @mbggenerated
	 */
	int deleteByExample(CountryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table country
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table country
	 * @mbggenerated
	 */
	int insert(Country record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table country
	 * @mbggenerated
	 */
	int insertSelective(Country record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table country
	 * @mbggenerated
	 */
	List<Country> selectByExample(CountryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table country
	 * @mbggenerated
	 */
	Country selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table country
	 * @mbggenerated
	 */
	int updateByExampleSelective(@Param("record") Country record, @Param("example") CountryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table country
	 * @mbggenerated
	 */
	int updateByExample(@Param("record") Country record, @Param("example") CountryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table country
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(Country record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table country
	 * @mbggenerated
	 */
	int updateByPrimaryKey(Country record);
	
	/**
	 * 执行 Oracle 中的存储过程
	 * 
	 * @param params
	 * @return
	 */
	Object selectCountries(Map<String, Object> params);
}
