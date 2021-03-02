package com.victor.seafoodback.service;

import com.victor.seafoodback.entity.Category;
import com.victor.seafoodback.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@FeignClient("seafood-good-9004")
public interface GoodService {

    @PostMapping("/getAllCategory")
    public CommonResult getAllCategory();

    @PostMapping("/addCategory")
    public CommonResult addCategory(@RequestParam("name") String name, @RequestParam("desc") String desc,
                                    @RequestParam(value = "parent", required = false) Integer parent);

    @PostMapping("/deleteCategory")
    public CommonResult deleteCategory(@RequestParam("categoryId") Integer categoryId);

    @PostMapping("/getAllSeafood")
    public CommonResult getAllSeafoods(@RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "lowPrice", required = false) Double lowPrice,
                                       @RequestParam(value = "highPrice", required = false) Double highPrice,
                                       @RequestParam(value = "lowDate", required = false) Date lowDate,
                                       @RequestParam(value = "highDate", required = false) Date highDate,
                                       @RequestParam(value = "category", required = false) Integer category,
                                       @RequestParam("pageNo")Integer pageNo,
                                       @RequestParam("pageSize")Integer pageSize);

    @PostMapping("/getSeafoodById")
    public CommonResult getSeafoodById(@RequestParam("id")Integer id);
}
