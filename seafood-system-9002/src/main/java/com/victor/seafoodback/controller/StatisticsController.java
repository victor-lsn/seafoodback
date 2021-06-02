package com.victor.seafoodback.controller;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.service.impl.StatisticsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    @Autowired
    private StatisticsServiceImpl statisticsService;

    @PostMapping("/getOrderCountByMonth")
    public CommonResult getOrderCountByMonth(){
        return statisticsService.getOrderCountByMonth();
    }

    @PostMapping("/getOrderMoneyByMonth")
    public CommonResult getOrderMoneyByMonth(){
        return statisticsService.getOrderMoneyByMonth();
    }

    @PostMapping("/getSaleCountByCategory")
    public CommonResult getSaleCountByCategory(){
        return statisticsService.getSaleCountByCategory();
    }

    @PostMapping("/getOrderCountMap")
    public CommonResult getOrderCountMap(){
        return statisticsService.getOrderCountMap();
    }
}
