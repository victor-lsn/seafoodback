package com.victor.seafoodback.service;

import com.victor.seafoodback.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Component
@FeignClient("seafood-order-9006")
public interface OrderService {

    @PostMapping("/addOrder1")
    public CommonResult addOrder1(@RequestParam("userId") Integer userId, @RequestParam("seafoodId") Integer seafoodId,
                                  @RequestParam("addrId") Integer addrId, @RequestParam("serialNumber")String serialNumber);

    @PostMapping("/addOrder2")
    public CommonResult addOrder2(@RequestParam("userId")Integer userId,@RequestParam("seafoodList[]")String[] seafoodList,
                                  @RequestParam("addrId")Integer addrId, @RequestParam("pay")Double pay);

    @PostMapping("/getAllOrder")
    public CommonResult getAllOrder(@RequestParam(value = "serialNumber",required = false)String serialNumber,
                                    @RequestParam(value = "lowDate",required = false) Date lowDate,
                                    @RequestParam(value = "highDate",required = false)Date highDate,
                                    @RequestParam("pageNo")Integer pageNo,@RequestParam("pageSize")Integer pageSize,
                                    @RequestParam(value = "status",required = false)String status);

    @PostMapping("/getOrderByUser")
    public CommonResult getOrderByUser(@RequestParam("userId")Integer userId);

    @PostMapping("/updateOrderStatus")
    public CommonResult updateOrderStatus(@RequestParam("orderId") Integer orderId);

    @PostMapping("/orderGetSeafood")
    public CommonResult orderGetSeafood(@RequestParam("seafoodId")Integer seafoodId);
}
