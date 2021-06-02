package com.victor.seafoodback.seafoodgood9004.controller;

import com.alibaba.excel.EasyExcel;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Seafood;
import com.victor.seafoodback.seafoodgood9004.config.SeafoodExcelListener;
import com.victor.seafoodback.seafoodgood9004.service.SeafoodService;
import com.victor.seafoodback.vo.SeafoodVoExcel;
import feign.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RestController
public class SeafoodController {

    @Autowired
    private SeafoodService seafoodService;

    @Autowired
    private SeafoodExcelListener seafoodExcelListener;


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

    @PostMapping("/addSeafood")
    public CommonResult addSeafood(@RequestParam("name") String name,@RequestParam("inPrice") Double inPrice,
                                   @RequestParam("outPrice")Double outPrice,@RequestParam("count")Integer count,
                                   @RequestParam("discount")Double discount,@RequestParam("categoryId")Integer categoryId,
                                   @RequestParam("title")String title,@RequestParam("info")String info,
                                   @RequestParam("picName")String picName,@RequestParam("weight")Float weight,
                                   @RequestParam("seafoodSource")String seafoodSource){
        Seafood seafood = new Seafood();
        seafood.setName(name);
        seafood.setInPrice(inPrice);
        seafood.setOutPrice(outPrice);
        seafood.setCount(count);
        seafood.setInfo(info);
        seafood.setTitle(title);
        seafood.setCategoryId(categoryId);
        seafood.setDiscount(discount);
        seafood.setSaleDate(new Date());
        seafood.setWeight(weight);
        seafood.setSeafoodSource(seafoodSource);
        return seafoodService.addSeafood(seafood,picName);
    }

    @PostMapping("/updateSeafood")
    public CommonResult updateSeafood(@RequestParam("name") String name,@RequestParam("inPrice") Double inPrice,
                                      @RequestParam("outPrice")Double outPrice,@RequestParam("count")Integer count,
                                      @RequestParam("discount")Double discount,@RequestParam("categoryId")Integer categoryId,
                                      @RequestParam("title")String title,@RequestParam("info")String info,
                                      @RequestParam(value = "picName",required = false)String picName,@RequestParam("id")Integer id,
                                      @RequestParam("weight")Float weight,
                                      @RequestParam("seafoodSource")String seafoodSource){
        Seafood seafood = new Seafood();
        seafood.setId(id);
        seafood.setName(name);
        seafood.setInPrice(inPrice);
        seafood.setOutPrice(outPrice);
        seafood.setCount(count);
        seafood.setInfo(info);
        seafood.setTitle(title);
        seafood.setCategoryId(categoryId);
        seafood.setDiscount(discount);
        seafood.setSaleDate(new Date());
        seafood.setWeight(weight);
        seafood.setSeafoodSource(seafoodSource);
        return seafoodService.updateSeafood(seafood,picName);
    }

    @PostMapping("/deleteSeafoodPic")
    public CommonResult deleteSeafoodPic(@RequestParam("id")Integer id,@RequestParam("name")String name){
        return seafoodService.deleteSeafoodPic(id,name);
    }

    @PostMapping("/deleteSeafood")
    public CommonResult deleteSeafood(@RequestParam("id")Integer id){
        return seafoodService.deleteSeafood(id);
    }

    @PostMapping("/uploadExcel")
    public CommonResult uploadExcel(@RequestPart(value = "file") MultipartFile file){
        EasyExcel.read(file.getOriginalFilename(), SeafoodVoExcel.class, new SeafoodExcelListener()).sheet().doRead();
        return new CommonResult(200,"批量插入成功");
    }

    @PostMapping("/batchAddSeafood")
    @Headers("Content-Type: multipart/form-data")
    public CommonResult batchAddSeafood(@RequestParam MultipartFile file){
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\victor\\projects\\SpringCloud\\apache-tomcat-file\\webapps\\files\\批量插入模板.xlsx");
            EasyExcel.read(file.getInputStream(), SeafoodVoExcel.class,seafoodExcelListener).sheet().doRead();
            /*FileInputStream fileInputStream = new FileInputStream("C:\\Users\\victor\\projects\\SpringCloud\\apache-tomcat-file\\webapps\\files\\批量插入模板.xlsx");
            ExcelReader excelReader = EasyExcel.read(fileInputStream, SeafoodVoExcel.class, new SeafoodExcelListener()).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
            // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
            excelReader.finish();*/

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CommonResult(200,"批量插入成功");
    }

    @PostMapping("/getFireGood")
    public CommonResult getFireGood(){
        return seafoodService.getFireGood();
    }

    @PostMapping("/getAllGoods")
    public CommonResult getAllGoods(@RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "lowPrice", required = false) Double lowPrice,
                                       @RequestParam(value = "highPrice", required = false) Double highPrice,
                                       @RequestParam(value = "paixu") String paixu,
                                       @RequestParam("pageNo")Integer pageNo,
                                       @RequestParam("pageSize")Integer pageSize) {
        return seafoodService.getAllGoods(name, lowPrice, highPrice, paixu, pageNo, pageSize);
    }

    @PostMapping("/getSearchGoods")
    public CommonResult getSearchGoods(@RequestParam(value = "keywords",required = false) String keywords,
                                       @RequestParam("paixu") String paixu,
                                       @RequestParam(value = "categoryId",required = false)Integer categoryId) {
        System.out.println(keywords);
        return seafoodService.getSearchGoods(keywords, paixu,categoryId);
    }

    @PostMapping("/getSeafoodCount")
    public CommonResult getSeafoodCount(@RequestParam("id") Integer id) {
        return seafoodService.getSeafoodCount(id);
    }

    @PostMapping("/getSeafoodCountByList")
    public CommonResult getSeafoodCountByList(@RequestParam("seafoodList[]") String[] seafoodList) {
        return seafoodService.getSeafoodCountByList(seafoodList);
    }
}
