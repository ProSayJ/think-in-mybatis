package prosayj.mybatis.cache.mapper;

import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;
import prosayj.mybatis.cache.pojo.User;

import java.util.List;

//@CacheNamespace(implementation = PerpetualCache.class)  //开启二级缓存
@CacheNamespace(implementation = RedisCache.class)  //开启redis 二级缓存
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


    @Select("select * from user")
    List<User> findAllUserAndRole();


}
