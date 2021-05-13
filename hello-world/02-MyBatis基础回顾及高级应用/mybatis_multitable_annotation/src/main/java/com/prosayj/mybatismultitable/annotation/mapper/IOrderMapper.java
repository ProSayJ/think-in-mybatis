package com.prosayj.mybatismultitable.annotation.mapper;

import com.prosayj.mybatismultitable.annotation.pojo.Order;
import com.prosayj.mybatismultitable.annotation.pojo.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IOrderMapper {

    /**
     * 查询订单的同时还查询该订单所属的用户
     */
    @Select("select id,ordertime,total,uid from orders")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "total", column = "total")
            ,
            @Result(property = "user", column = "uid",
                    javaType = User.class,
                    one = @One(select = "com.prosayj.mybatismultitable.annotation.mapper.IUserMapper.findUserById",
                            fetchType = FetchType.EAGER
                    ))
    })
    List<Order> findOrderAndUser();


    @Select("select * from orders where uid = #{uid}")
    List<Order> findOrderByUid(Integer uid);


}
