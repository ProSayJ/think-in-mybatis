package prosayj.mybatis.annotation.mapper;

import prosayj.mybatis.annotation.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Insert("insert into user (id,username) values(#{id},#{username})")
    void addUser(User user);

    @Update("update user set username = #{username} where id = #{id}")
    void updateUser(User user);

    @Select("select * from user")
    List<User> selectAllUser();

    @Delete("delete from user where id = #{id}")
    void deleteUser(Integer id);

    @Select("select * from user where id = #{id}")
    User selectUserById(Integer integer);


    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "ordersList", column = "id", javaType = List.class,
                    many = @Many(select = "prosayj.mybatis.annotation.mapper.OrdersMapper.findOrderByUid")),
    })
    @Select("select * from user")
    List<User> findAll();


    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "roleList", column = "id", javaType = List.class,
                    many = @Many(select = "prosayj.mybatis.annotation.mapper.RoleMapper.findRoleByUid")),

    })
    @Select("select * from user")
    List<User> findAllUserAndRole();


}
