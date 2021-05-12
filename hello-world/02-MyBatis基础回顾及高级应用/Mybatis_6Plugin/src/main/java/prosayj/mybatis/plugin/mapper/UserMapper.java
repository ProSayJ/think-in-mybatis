package prosayj.mybatis.plugin.mapper;

import prosayj.mybatis.plugin.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> selectUsers();
}
