package com.victor.seafoodback.seafoodmedia9005.controller;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.seafoodmedia9005.service.AdvertService;
import com.victor.seafoodback.seafoodmedia9005.service.impl.AdvertServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvertController {

    @Autowired
    private AdvertServiceImpl advertService;

    @PostMapping("/getAllAdvert")
    public CommonResult getAllAdverts(){
        return advertService.getAllAdverts();
    }

    @PostMapping("/updateAdvert")
    public CommonResult updateAdvert(@RequestParam("id")Integer id,@RequestParam("name")String name){
        return  advertService.updateAdvert(id,name);
    }
}
