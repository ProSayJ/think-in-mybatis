package mytest.mapper;


import mytest.pojo.Order;

import java.util.List;

public interface IOrderMapper {

  /**
   * 查询订单的同时还查询该订单所属的用户
   *
   * @return List<Order>
   */
  List<Order> findOrderAndUser();


}
