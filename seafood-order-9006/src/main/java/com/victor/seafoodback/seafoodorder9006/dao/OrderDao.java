package com.victor.seafoodback.seafoodorder9006.dao;

import com.victor.seafoodback.entity.Address;
import com.victor.seafoodback.entity.Order;
import com.victor.seafoodback.entity.Seafood;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface OrderDao {

    Integer addOrder(Order order);

    Integer addOrderSeafood(@Param("orderId") Integer orderId,@Param("seafoodId") Integer seafoodId,
                            @Param("seafoodCount") Integer seafoodCount);

    Address getAddressById(Integer id);

    Seafood getSeafoodById(Integer id);

    List<Order> getAllOrder(@Param("serialNumber")String serialNumber,@Param("lowDate") Date lowDate,
                            @Param("highDate") Date highDate,@Param("status")String status);

    List<Order> getAllOrderByUserId(Integer userId);

    Integer updateOrder(Integer orderId);

    Integer updateOrder2(@Param("serialNumber")String serialNumber,@Param("status") Integer status);

    Seafood getSeafood(Integer seafood_id);
}
