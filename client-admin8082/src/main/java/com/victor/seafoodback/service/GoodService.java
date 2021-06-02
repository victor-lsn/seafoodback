package com.victor.seafoodback.service;


import com.victor.seafoodback.entity.Category;
import com.victor.seafoodback.entity.CommonResult;
import feign.Headers;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
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

    @PostMapping("/uploadGoodPic")
    public CommonResult uploadGoodPic(@RequestParam(value = "file",required = false) MultipartFile file);

    @PostMapping("/addSeafood")
    public CommonResult addSeafood(@RequestParam("name") String name,@RequestParam("inPrice") Double inPrice,
                                   @RequestParam("outPrice")Double outPrice,@RequestParam("count")Integer count,
                                   @RequestParam("discount")Double discount,@RequestParam("categoryId")Integer categoryId,
                                   @RequestParam("title")String title,@RequestParam("info")String info,
                                   @RequestParam("picName")String picName,@RequestParam("weight")Float weight,
                                   @RequestParam("seafoodSource")String seafoodSource);

    @PostMapping("/updateSeafood")
    public CommonResult updateSeafood(@RequestParam("name") String name,@RequestParam("inPrice") Double inPrice,
                                      @RequestParam("outPrice")Double outPrice,@RequestParam("count")Integer count,
                                      @RequestParam("discount")Double discount,@RequestParam("categoryId")Integer categoryId,
                                      @RequestParam("title")String title,@RequestParam("info")String info,
                                      @RequestParam(value = "picName",required = false)String picName,@RequestParam("id")Integer id,
                                      @RequestParam("weight")Float weight,
                                      @RequestParam("seafoodSource")String seafoodSource);

    @PostMapping("/deleteSeafoodPic")
    public CommonResult deleteSeafoodPic(@RequestParam("id")Integer id,@RequestParam("name")String name);

    @PostMapping("/deleteSeafood")
    public CommonResult deleteSeafood(@RequestParam("id")Integer id);

    @PostMapping("/uploadExcel")
    public CommonResult uploadExcel(@RequestPart(value = "file") MultipartFile file);

    @PostMapping(value = "/batchAddSeafood", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResult batchAddSeafood(@RequestPart("file") MultipartFile file);

    @PostMapping("/getParentCategoryVo")
    public CommonResult getParentCategoryVo();

    @PostMapping("/getFireGood")
    public CommonResult getFireGood();

    @PostMapping("/getAllGoods")
    public CommonResult getAllGoods(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "lowPrice", required = false) Double lowPrice,
                                    @RequestParam(value = "highPrice", required = false) Double highPrice,
                                    @RequestParam(value = "paixu") String paixu,
                                    @RequestParam("pageNo")Integer pageNo,
                                    @RequestParam("pageSize")Integer pageSize);

    @PostMapping("/getSearchGoods")
    public CommonResult getSearchGoods(@RequestParam(value = "keywords",required = false) String keywords,
                                       @RequestParam("paixu") String paixu,
                                       @RequestParam(value = "categoryId",required = false)Integer categoryId);

    @RequestMapping("/getCategoryById")
    public CommonResult getCategoryById(@RequestParam("id") Integer id);

    @RequestMapping("/updateCategory")
    public CommonResult updateCategory(@RequestBody Category category);

    @PostMapping("/getSeafoodCount")
    public CommonResult getSeafoodCount(@RequestParam("id") Integer id);

    @PostMapping("/getSeafoodCountByList")
    public CommonResult getSeafoodCountByList(@RequestParam("seafoodList[]") String[] seafoodList);
}
