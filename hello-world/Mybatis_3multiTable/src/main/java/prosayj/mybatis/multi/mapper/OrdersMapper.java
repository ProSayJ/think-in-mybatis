package prosayj.mybatis.multi.mapper;


import prosayj.mybatis.multi.pojo.Orders;

import java.io.IOException;
import java.util.List;

public interface OrdersMapper {

     List<Orders> findOrderAndUser() throws IOException;



}
