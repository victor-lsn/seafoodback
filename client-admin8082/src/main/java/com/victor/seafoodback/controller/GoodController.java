package com.victor.seafoodback.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.victor.seafoodback.entity.Category;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Seafood;
import com.victor.seafoodback.service.GoodService;
import com.victor.seafoodback.util.MyTimeUtil;
import com.victor.seafoodback.vo.SeafoodVoExcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/admin")
@Slf4j
public class GoodController {

    @Autowired
    private GoodService goodService;


    @PostMapping("/getAllCategory")
    public CommonResult getAllCategory() {
        return goodService.getAllCategory();
    }


    @PostMapping("/addCategory")
    public CommonResult addCategory(@RequestParam("name") String name, @RequestParam("desc") String desc,
                                    @RequestParam(value = "parent", required = false) Integer parent) {
        return goodService.addCategory(name, desc, parent);
    }

    @PostMapping("/deleteCategory")
    public CommonResult deleteCategory(@RequestParam("categoryId") Integer categoryId) {
        return goodService.deleteCategory(categoryId);
    }

    @PostMapping("/getAllSeafood")
    public CommonResult getAllSeafoods(@RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "lowPrice", required = false) Double lowPrice,
                                       @RequestParam(value = "highPrice", required = false) Double highPrice,
                                       @RequestParam(value = "lowDate", required = false) String lowDate,
                                       @RequestParam(value = "highDate", required = false) String highDate,
                                       @RequestParam(value = "category", required = false) String category,
                                       @RequestParam("pageNo") Integer pageNo,
                                       @RequestParam("pageSize") Integer pageSize) {
//        System.out.println("名字：" + name.length());
//        System.out.println("最低价格" + lowPrice);
//        System.out.println("最高价格" + highPrice);
//        System.out.println("最低日期" + MyTimeUtil.dealDateFormat(lowDate));
//        System.out.println("最高日期" + highDate);
//        System.out.println("分类" + category.length());
        if (category == null || category.length() == 0 || category.length() == 1) {
            return goodService.getAllSeafoods(name, lowPrice, highPrice,
                    MyTimeUtil.dealDateFormat(lowDate), MyTimeUtil.dealDateFormat(highDate), null, pageNo, pageSize);
        }

        String[] split = category.split(",");
        return goodService.getAllSeafoods(name, lowPrice, highPrice,
                MyTimeUtil.dealDateFormat(lowDate), MyTimeUtil.dealDateFormat(highDate), Integer.parseInt(split[1]), pageNo, pageSize);
    }

    @PostMapping("/getSeafoodById")
    public CommonResult getSeafoodById(@RequestParam("id") Integer id) {
        return goodService.getSeafoodById(id);
    }

    @PostMapping("/uploadGoodPic")
    public CommonResult uploadGoodPic(@RequestParam(value = "file", required = false) MultipartFile file) {
        if (file.isEmpty()) {
            return new CommonResult(444, "上传失败");
        }
        //创建输入输出流
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            //指定上传的位置为 d:/upload/
            String path = "C:/Users/victor/apache-tomcat-file/webapps/files/";
            //获取文件的输入流
            inputStream = file.getInputStream();
            //获取上传时的文件名
            String fileName = file.getOriginalFilename();
            //注意是路径+文件名
            File targetFile = new File(path + fileName);
            //如果之前的 String path = "d:/upload/" 没有在最后加 / ，那就要在 path 后面 + "/"

            //判断文件父目录是否存在
            if (!targetFile.getParentFile().exists()) {
                //不存在就创建一个
                targetFile.getParentFile().mkdir();
            }

            //获取文件的输出流
            outputStream = new FileOutputStream(targetFile);
            //最后使用资源访问器FileCopyUtils的copy方法拷贝文件
            FileCopyUtils.copy(inputStream, outputStream);

            //告诉页面上传成功了
            return new CommonResult(200, "上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            //出现异常，则告诉页面失败
            return new CommonResult(200, "上传失败");
        } finally {
            //无论成功与否，都有关闭输入输出流
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @PostMapping("/addSeafood")
    public CommonResult addSeafood(@RequestParam("name") String name, @RequestParam("inPrice") Double inPrice,
                                   @RequestParam("outPrice") Double outPrice, @RequestParam("count") Integer count,
                                   @RequestParam("discount") Double discount, @RequestParam("categoryId") Integer categoryId,
                                   @RequestParam("title") String title, @RequestParam("info") String info,
                                   @RequestParam("picName") String picName, @RequestParam("weight") Float weight,
                                   @RequestParam("seafoodSource") String seafoodSource) {
        return goodService.addSeafood(name, inPrice, outPrice, count, discount, categoryId, title, info, picName, weight, seafoodSource);
    }

    @PostMapping("/updateSeafood")
    public CommonResult updateSeafood(@RequestParam("name") String name, @RequestParam("inPrice") Double inPrice,
                                      @RequestParam("outPrice") Double outPrice, @RequestParam("count") Integer count,
                                      @RequestParam("discount") Double discount, @RequestParam("categoryId") Integer categoryId,
                                      @RequestParam("title") String title, @RequestParam("info") String info,
                                      @RequestParam(value = "picName", required = false) String picName, @RequestParam("id") Integer id,
                                      @RequestParam("weight") Float weight,
                                      @RequestParam("seafoodSource") String seafoodSource) {
        return goodService.updateSeafood(name, inPrice, outPrice, count, discount, categoryId, title, info, picName, id, weight, seafoodSource);
    }

    @PostMapping("/deleteSeafoodPic")
    public CommonResult deleteSeafoodPic(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        return goodService.deleteSeafoodPic(id, name);
    }

    @PostMapping("/deleteSeafood")
    public CommonResult deleteSeafood(@RequestParam("id") Integer id) {
        return goodService.deleteSeafood(id);
    }

    @PostMapping("/uploadExcel")
    public CommonResult uploadExcel(@RequestPart(value = "file") MultipartFile file) {
        return goodService.batchAddSeafood(file);
    }

    @PostMapping("/getParentCategoryVo")
    public CommonResult getParentCategoryVo() {
        return goodService.getParentCategoryVo();
    }

    @PostMapping("/getFireGood")
    public CommonResult getFireGood() {
        return goodService.getFireGood();
    }

    @PostMapping("/getAllGoods")
    public CommonResult getAllGoods(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "lowPrice", required = false) Double lowPrice,
                                    @RequestParam(value = "highPrice", required = false) Double highPrice,
                                    @RequestParam(value = "paixu") String paixu,
                                    @RequestParam("pageNo") Integer pageNo,
                                    @RequestParam("pageSize") Integer pageSize) {
        return goodService.getAllGoods(name, lowPrice, highPrice, paixu, pageNo, pageSize);
    }

    @PostMapping("/getSearchGoods")
    public CommonResult getSearchGoods(@RequestParam(value = "keywords", required = false) String keywords,
                                       @RequestParam("paixu") String paixu,
                                       @RequestParam(value = "categoryId", required = false) Integer categoryId) {
        System.out.println(keywords);
        return goodService.getSearchGoods(keywords, paixu, categoryId);
    }

    @RequestMapping("/getCategoryById")
    public CommonResult getCategoryById(@RequestParam("id") Integer id) {
        return goodService.getCategoryById(id);
    }

    @RequestMapping("/updateCategory")
    public CommonResult updateCategory(Category category) {
        return goodService.updateCategory(category);
    }

    @PostMapping("/getSeafoodCount")
    public CommonResult getSeafoodCount(@RequestParam("id") Integer id) {
        return goodService.getSeafoodCount(id);
    }

    @PostMapping("/getSeafoodCountByList")
    public CommonResult getSeafoodCountByList(@RequestParam("seafoodList[]") String[] seafoodList) {
        return goodService.getSeafoodCountByList(seafoodList);
    }
}
