package com.prosayj.mybatismultitable.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@Table(name = "user")
public class User implements Serializable {
    /**
     * 对应的是注解id
     * <p>
     * 设置主键的生成策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private List<Order> orderList;
    private List<Role> roleList;
}
