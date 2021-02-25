package com.victor.seafoodback.controller;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/getAllUser")
    public CommonResult getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/getUserPage")
    public CommonResult getUserForPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,@RequestParam(value = "username",required = false) String username,
                                       @RequestParam(value = "phone",required = false)String phone,@RequestParam(value = "role",required = false)String role) {
        return userService.getUserForPage(pageNo, pageSize,username,phone,role);
    }

    @PostMapping("/updateStatus")
    public CommonResult updateStatus(@RequestParam("userId") Integer userId, @RequestParam("flag") Integer flag) {
        return userService.updateStatus(userId, flag);
    }

    @PostMapping("/updateRole")
    public CommonResult updateRole(@RequestParam("userId") Integer userId, @RequestParam("roleId") Integer roleId) {
        return userService.updateRole(userId, roleId);
    }

    @PostMapping("/getUserInfo")
    public CommonResult getUserInfo(@RequestParam("userId") Integer userId){
        return userService.getUserInfo(userId);
    }
}
