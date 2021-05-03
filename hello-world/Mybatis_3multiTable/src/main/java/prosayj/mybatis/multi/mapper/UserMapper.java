package prosayj.mybatis.lite.mapper;

import prosayj.mybatis.lite.pojo.User;

import java.util.List;

public interface UserMapper {

    public List<User> findAll();

    public List<User> findAllUserAndRole();

}
