package prosayj.mybatis.annotation.mapper;

import prosayj.mybatis.annotation.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {


    @Select("select * from sys_role r,sys_user_role ur where r.id = ur.role_id and ur.user_id = #{uid}")
    List<Role> findRoleByUid(Integer uid);
}
