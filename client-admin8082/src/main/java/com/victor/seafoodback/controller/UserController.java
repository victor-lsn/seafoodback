package com.victor.seafoodback.controller;

import com.victor.seafoodback.entity.Address;
import com.victor.seafoodback.entity.Comment;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    @PostMapping("/getAllAddress")
    public CommonResult getAllAddress(@RequestParam("userId") Integer userId){
        return userService.getAllAddress(userId);
    }

    @PostMapping("/addAddress")
    public CommonResult addAddress(@RequestParam("addr") String addr,@RequestParam("userId")Integer userId,
                                   @RequestParam("realName") String realName){
        return userService.addAddress(addr, userId, realName);
    }

    @PostMapping("/deleteAddress")
    public CommonResult deleteAddress(@RequestParam("id")Integer id){
        return userService.deleteAddress(id);
    }

    @PostMapping("/addShopCar")
    public CommonResult addShopCar(@RequestParam("userId")Integer userId,@RequestParam("seafoodId")Integer srafoodId){
        return userService.addShopCar(userId,srafoodId);
    }

    @PostMapping("/deleteShopCar")
    public CommonResult deleteShopCar(@RequestParam("userId")Integer userId,@RequestParam("seafoodId")Integer srafoodId){
        return userService.deleteShopCar(userId, srafoodId);
    }

    @PostMapping("/getShopCar")
    public CommonResult getShopCar(@RequestParam("userId")Integer userId){
        return userService.getShopCar(userId);
    }

    @PostMapping("/getComment")
    public CommonResult getAllCommentBySeafood(@RequestParam("seafoodId") Integer seafoodId){
        return userService.getAllCommentBySeafood(seafoodId);
    }

    @PostMapping("/addComment")
    public CommonResult addComment(@RequestParam("fromUserId") Integer fromUserId, @RequestParam(value = "toUerId",required = false) Integer toUserId,
                                   @RequestParam("seafoodId") Integer seafoodId,
                                   @RequestParam(value = "parent",required = false) Integer parent,
                                   @RequestParam("level") Integer level,
                                   @RequestParam("content") String content,
                                   @RequestParam("fromUserName") String fromUserName,@RequestParam(value = "toUserName",required = false) String toUserName) {
        return userService.addComment(fromUserId, toUserId, seafoodId, parent, level, content, fromUserName, toUserName);
    }

    @PostMapping("/getAllWaiter")
    public CommonResult getAllWaiter(){
        return userService.getAllWaiter();
    }
}
