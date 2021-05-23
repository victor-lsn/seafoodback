package com.victor.seafoodback.controller;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.User;
import com.victor.seafoodback.service.UserRegiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class RegiterController {

    @Autowired
    private UserRegiter userRegiter;

    @PostMapping("/register")
    public CommonResult register(@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("password") String password, @RequestParam("code") String code) {
        return userRegiter.register(name, phone, password, code);
    }

    @PostMapping("/login")
    public CommonResult login(@RequestParam("phone") String phone, @RequestParam("password") String password, @RequestParam("code") String code) {
        return userRegiter.login(phone, password, code);
    }

    @PostMapping("/getCode")
    public CommonResult getCode() {
        return userRegiter.getCode();
    }
}
