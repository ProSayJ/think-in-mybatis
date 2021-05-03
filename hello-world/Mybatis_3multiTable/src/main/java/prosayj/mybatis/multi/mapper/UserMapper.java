package prosayj.mybatis.multi.mapper;


import prosayj.mybatis.multi.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> findAll();

    List<User> findAllUserAndRole();

}
