package com.victor.seafoodback.controller;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.User;
import com.victor.seafoodback.service.BasicService;
import com.victor.seafoodback.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BasicController {
    @Autowired
    private BasicService basicService;

    private String code;

    @PostMapping("/register")
    public CommonResult register(@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("password") String password, @RequestParam("code") String code) {
        if (!code.equalsIgnoreCase(this.code)) {
            return new CommonResult(400, "验证码错误,请稍后重试");
        }
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        return basicService.register(user);
    }

    @PostMapping("/login")
    public CommonResult login(@RequestParam("phone") String phone, @RequestParam("password") String password, @RequestParam("code") String code) {
        if (!code.equalsIgnoreCase(this.code)) {
            return new CommonResult(400, "验证码错误");
        }
        User user = new User();
        user.setPassword(password);
        user.setPhone(phone);
        return basicService.login(user);
    }

    @PostMapping("/getCode")
    public CommonResult getCode() {
        this.code = CodeUtil.getCode();
        return new CommonResult(200, this.code);
    }
}
