package com.victor.seafoodback.seafoodorder9006.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.victor.seafoodback.entity.Address;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Order;
import com.victor.seafoodback.entity.Seafood;
import com.victor.seafoodback.seafoodorder9006.dao.OrderDao;
import com.victor.seafoodback.seafoodorder9006.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    @Transactional
    public CommonResult addOrder1(Integer userId,Integer seafoodId,Integer addrId,String serialNumber) {
        Order order = new Order();
        order.setUserId(userId);
        order.setCreateDate(new Date());
        Address address = orderDao.getAddressById(addrId);
        order.setUserAddr(address.getAddr());
        order.setRealName(address.getRealName());
        order.setSerialNumber(serialNumber);

        Seafood seafood = orderDao.getSeafood(seafoodId);
        Double outPrice = seafood.getOutPrice();
        order.setCost(outPrice);
        order.setPay(outPrice);

        orderDao.addOrder(order);
        orderDao.addOrderSeafood(order.getId(), seafoodId, 1);
        return new CommonResult(200, "生成订单成功");
    }

    @Override
    @Transactional
    public CommonResult addOrder2(Integer userId, String[] seafoodList, Integer addrId, Double pay) {
        //TODO 还没有减少库存,销量增加
        Order order = new Order();
        order.setUserId(userId);
        order.setCreateDate(new Date());
        Address address = orderDao.getAddressById(addrId);
        order.setUserAddr(address.getAddr());
        order.setRealName(address.getRealName());

        Double cost = 0.0;
        System.out.println(Arrays.toString(seafoodList));
        for (String s : seafoodList) {

            String[] split = s.split("-");
            //查找海鲜价格
            Seafood seafood = orderDao.getSeafoodById(Integer.parseInt(split[0]));
            if (split.length == 1) {
                System.out.println(seafood);
                cost += seafood.getOutPrice();
            } else {
                cost += seafood.getOutPrice() * Integer.parseInt(split[1]);
            }

        }

        order.setCost(cost);
        order.setPay(pay);
        order.setSerialNumber(UUID.randomUUID().toString());

        orderDao.addOrder(order);
        for (String s : seafoodList) {
            String[] split = s.split("-");
            if (split.length == 1) {
                orderDao.addOrderSeafood(order.getId(), Integer.parseInt(split[0]), 1);
            } else {
                orderDao.addOrderSeafood(order.getId(), Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            }

        }

        return new CommonResult(200, "生成订单成功");
    }

    @Override
    public CommonResult getAllOrder(String serialNumber, Date lowDate, Date highDate,Integer pageNo,Integer pageSize,String status) {
        PageHelper.startPage(pageNo,pageSize);
        List<Order> allOrder = orderDao.getAllOrder(serialNumber, lowDate, highDate,status);
        return new CommonResult(200, "获取所有订单成功", new PageInfo<>(allOrder));
    }

    @Override
    public CommonResult getAllOrderByUserId(Integer userId) {
        return new CommonResult(200, "获取当前用户订单成功", orderDao.getAllOrderByUserId(userId));
    }

    @Override
    public CommonResult updateOrderStatus(Integer orderId) {
        orderDao.updateOrder(orderId);
        return new CommonResult(200,"已将此产品更换为运输中状态");
    }

    @Override
    public CommonResult getSeafood(Integer seafoodId) {
        return new CommonResult(200,"获取海鲜成功",orderDao.getSeafood(seafoodId));
    }
}
