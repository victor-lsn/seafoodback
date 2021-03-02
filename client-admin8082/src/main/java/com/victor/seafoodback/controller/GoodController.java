package com.victor.seafoodback.controller;

import com.victor.seafoodback.entity.Category;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.service.GoodService;
import com.victor.seafoodback.util.MyTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                    MyTimeUtil.dealDateFormat(lowDate), MyTimeUtil.dealDateFormat(lowDate), null, pageNo, pageSize);
        }

        String[] split = category.split(",");
        return goodService.getAllSeafoods(name, lowPrice, highPrice,
                MyTimeUtil.dealDateFormat(lowDate), MyTimeUtil.dealDateFormat(lowDate), Integer.parseInt(split[1]), pageNo, pageSize);
    }

    @PostMapping("/getSeafoodById")
    public CommonResult getSeafoodById(@RequestParam("id")Integer id){
        return goodService.getSeafoodById(id);
    }
}
