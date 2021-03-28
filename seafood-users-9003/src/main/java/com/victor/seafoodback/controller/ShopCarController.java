package com.victor.seafoodback.controller;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.service.impl.ShopCarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopCarController {

    @Autowired
    private ShopCarServiceImpl shopCarService;

    @PostMapping("/addShopCar")
    public CommonResult addShopCar(@RequestParam("userId")Integer userId,@RequestParam("seafoodId")Integer srafoodId){
        return shopCarService.addSeafoodToCar(userId,srafoodId);
    }

    @PostMapping("/deleteShopCar")
    public CommonResult deleteShopCar(@RequestParam("userId")Integer userId,@RequestParam("seafoodId")Integer srafoodId){
        return shopCarService.deleteSeafoodFromCar(userId, srafoodId);
    }

    @PostMapping("/getShopCar")
    public CommonResult getShopCar(@RequestParam("userId")Integer userId){
        return shopCarService.getShopCar(userId);
    }
}
