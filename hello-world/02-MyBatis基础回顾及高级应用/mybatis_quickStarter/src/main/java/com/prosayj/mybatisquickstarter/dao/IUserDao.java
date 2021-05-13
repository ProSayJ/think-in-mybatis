package com.prosayj.mybatisquickstarter.dao;

import com.prosayj.mybatisquickstarter.pojo.SimpleUser;

import java.io.IOException;
import java.util.List;

public interface IUserDao {

    /**
     * 查询所有用户
     *
     * @return List<User>
     * @throws IOException IOException
     */
    List<SimpleUser> findAll() throws IOException;

    /**
     * 多条件组合查询：演示if
     *
     * @param user user
     * @return List<User>
     */
    List<SimpleUser> findByCondition(SimpleUser user);


    /**
     * 多值查询：演示foreach
     *
     * @param ids ids
     * @return List<User>
     */
    List<SimpleUser> findByIds(int[] ids);


}
