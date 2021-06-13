package com.victor.seafoodback.controller;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.service.impl.StatisticsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/getOrderCountMap2")
    public CommonResult getOrderCountMap2(@RequestParam("year") Integer year){
        return statisticsService.getOrderCountMap2(year);
    }

    @PostMapping("/getOrderCountByMonth2")
    public CommonResult getOrderCountByMonth2(@RequestParam("year") String year){
        return statisticsService.getOrderCountByMonth2(year);
    }

    @PostMapping("/getOrderMoneyByMonth2")
    public CommonResult getOrderMoneyByMonth2(@RequestParam("year") String year){
        return statisticsService.getOrderMoneyByMonth2(year);
    }
}
