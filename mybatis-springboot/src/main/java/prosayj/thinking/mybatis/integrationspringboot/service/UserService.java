package prosayj.thinking.mybatis.integrationspringboot.service;

import prosayj.thinking.mybatis.simple.model.SysUser;

import java.util.List;

public interface UserService {

    /**
     * 通过 id 查询用户
     *
     * @param id id
     * @return SysUser
     */
    SysUser findById(Long id);

    /**
     * 查询全部用户
     *
     * @return List<SysUser>
     */
    List<SysUser> findAll();
}
