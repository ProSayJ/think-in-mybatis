package prosayj.mybatis.multi.mapper;

import prosayj.mybatis.multi.pojo.User;

import java.util.List;

public interface UserMapper {

    public List<User> findAll();

    public List<User> findAllUserAndRole();

}
