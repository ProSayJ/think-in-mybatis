package prosayj.thinking.mybatis.simple;

import java.util.List;

import prosayj.thinking.mybatis.simple.model.Country;

public interface SimpleMapper {

	Country selectCountry(Long id);

	List<Country> selectAll();
}
