package com.victor.seafoodback.controller;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.service.OrderService;
import com.victor.seafoodback.util.MyTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/admin")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder1")
    public CommonResult addOrder1(@RequestParam("userId") Integer userId, @RequestParam("seafoodId") Integer seafoodId,
                                  @RequestParam("addrId") Integer addrId, @RequestParam("serialNumber")String serialNumber) {
        return orderService.addOrder1(userId, seafoodId, addrId,serialNumber);
    }

    @PostMapping("/addOrder2")
    public CommonResult addOrder2(@RequestParam("userId") Integer userId, @RequestParam("seafoodList[]") String[] seafoodList,
                                  @RequestParam("addrId") Integer addrId, @RequestParam("pay") Double pay,@RequestParam("serialNumber")String serialNumber) {
        return orderService.addOrder2(userId, seafoodList, addrId, pay,serialNumber);
    }

    @PostMapping("/getAllOrder")
    public CommonResult getAllOrder(@RequestParam(value = "serialNumber",required = false)String serialNumber,
                                    @RequestParam(value = "lowDate",required = false) String lowDate,
                                    @RequestParam(value = "highDate",required = false)String highDate,
                                    @RequestParam("pageNo")Integer pageNo,@RequestParam("pageSize")Integer pageSize,
                                    @RequestParam(value = "status",required = false)String status) {

        return orderService.getAllOrder(serialNumber, MyTimeUtil.dealDateFormat(lowDate), MyTimeUtil.dealDateFormat(highDate),pageNo,pageSize,status);
    }

    @PostMapping("/getOrderByUser")
    public CommonResult getOrderByUser(@RequestParam("userId") Integer userId) {
        System.out.println(orderService.getOrderByUser(userId));
        return orderService.getOrderByUser(userId);
    }

    @PostMapping("/updateOrderStatus")
    public CommonResult updateOrderStatus(@RequestParam("orderId") Integer orderId) {
        return orderService.updateOrderStatus(orderId);
    }
}
