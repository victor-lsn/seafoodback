package com.victor.seafoodback.seafoodorder9006.controller;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.seafoodorder9006.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;

@RestController
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/addOrder1")
    public CommonResult addOrder1(@RequestParam("userId") Integer userId, @RequestParam("seafoodId") Integer seafoodId,
                                  @RequestParam("addrId") Integer addrId, @RequestParam("serialNumber")String serialNumber) {
        return orderService.addOrder1(userId, seafoodId, addrId,serialNumber);
    }

    @PostMapping("/addOrder2")
    public CommonResult addOrder2(@RequestParam("userId") Integer userId, @RequestParam("seafoodList[]") String[] seafoodList,
                                  @RequestParam("addrId") Integer addrId, @RequestParam("pay") Double pay) {
        return orderService.addOrder2(userId, seafoodList, addrId, pay);
    }

    @PostMapping("/getAllOrder")
    public CommonResult getAllOrder(@RequestParam(value = "serialNumber", required = false) String serialNumber,
                                    @RequestParam(value = "lowDate", required = false) Date lowDate,
                                    @RequestParam(value = "highDate", required = false) Date highDate,
                                    @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,
                                    @RequestParam(value = "status",required = false)String status) {
        System.out.println(status);
        return orderService.getAllOrder(serialNumber, lowDate, highDate, pageNo, pageSize,status);
    }

    @PostMapping("/getOrderByUser")
    public CommonResult getOrderByUser(@RequestParam("userId") Integer userId) {
        System.out.println(orderService.getAllOrderByUserId(userId));
        return orderService.getAllOrderByUserId(userId);
    }

    @PostMapping("/updateOrderStatus")
    public CommonResult updateOrderStatus(@RequestParam("orderId") Integer orderId) {
        return orderService.updateOrderStatus(orderId);
    }

    @PostMapping("/orderGetSeafood")
    public CommonResult orderGetSeafood(@RequestParam("seafoodId")Integer seafoodId){
        return orderService.getSeafood(seafoodId);
    }
}
