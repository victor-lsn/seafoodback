package com.victor.seafoodback.service;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("cloud-basic-9001")
public interface UserRegiter {

    @PostMapping("/register")
    CommonResult register(@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("password")String password,@RequestParam("code")String code);

    @PostMapping("/login")
    CommonResult login(@RequestParam("phone") String phone,@RequestParam("password")String password,@RequestParam("code")String code);
}
