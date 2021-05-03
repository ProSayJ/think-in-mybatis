package prosayj.mybatis.lite.mapper;

import prosayj.mybatis.lite.pojo.Orders;

import java.io.IOException;
import java.util.List;

public interface OrdersMapper {

     List<Orders> findOrderAndUser() throws IOException;



}
