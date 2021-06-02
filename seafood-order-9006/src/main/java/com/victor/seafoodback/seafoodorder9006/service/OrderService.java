package com.victor.seafoodback.seafoodorder9006.service;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Order;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

public interface OrderService {

    CommonResult addOrder1(Integer userId,Integer seafoodId,Integer addrId,String serialNumber);

    CommonResult addOrder2(Integer userId,String[] seafoodList,Integer addrId,Double pay,String serialNumber);

    CommonResult getAllOrder(String serialNumber, Date lowDate,Date highDate,Integer pageNo,Integer pageSize,String status);

    CommonResult getAllOrderByUserId(Integer userId);

    CommonResult updateOrderStatus(Integer orderId);

    CommonResult getSeafood(Integer seafoodId);
}
