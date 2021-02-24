package prosayj.thinking.mybatis.integrationspringboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import prosayj.thinking.mybatis.integrationspringboot.model.Country;

import java.util.List;

@Mapper
public interface CountryMapper {
	/**
	 * 查询全部数据
	 *
	 * @return List<Country>
	 */
	List<Country> selectAll();
}
