package com.victor.seafoodback.seafoodgood9004.controller;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.seafoodgood9004.service.SeafoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SeafoodController {

    @Autowired
    private SeafoodService seafoodService;

    @PostMapping("/getAllSeafood")
    public CommonResult getAllSeafoods(@RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "lowPrice", required = false) Double lowPrice,
                                       @RequestParam(value = "highPrice", required = false) Double highPrice,
                                       @RequestParam(value = "lowDate", required = false) Date lowDate,
                                       @RequestParam(value = "highDate", required = false) Date highDate,
                                       @RequestParam(value = "category", required = false) Integer category,
                                       @RequestParam("pageNo")Integer pageNo,
                                       @RequestParam("pageSize")Integer pageSize) {
        return seafoodService.getAllSeafoods(name, lowPrice, highPrice, lowDate, highDate, category,pageNo,pageSize);
    }

    @PostMapping("/getSeafoodById")
    public CommonResult getSeafoodById(@RequestParam("id")Integer id){
        return seafoodService.getSeafoodById(id);
    }
}
