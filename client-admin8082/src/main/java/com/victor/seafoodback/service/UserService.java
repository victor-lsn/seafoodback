package com.victor.seafoodback.service;

import com.victor.seafoodback.entity.Address;
import com.victor.seafoodback.entity.Comment;
import com.victor.seafoodback.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Component
@FeignClient("seafood-users-9003")
public interface UserService {
    @PostMapping("/getAllUser")
    CommonResult getAllUser();

    @PostMapping("/getUserPage")
    public CommonResult getUserForPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "username", required = false) String username,
                                       @RequestParam(value = "phone", required = false) String phone, @RequestParam(value = "role", required = false) String role);

    @PostMapping("/updateStatus")
    public CommonResult updateStatus(@RequestParam("userId") Integer userId, @RequestParam("flag") Integer flag);

    @PostMapping("/updateRole")
    public CommonResult updateRole(@RequestParam("userId") Integer userId, @RequestParam("roleId") Integer roleId);

    @PostMapping("/getUserInfo")
    public CommonResult getUserInfo(@RequestParam("userId") Integer userId);

    @PostMapping("/getAllAddress")
    public CommonResult getAllAddress(@RequestParam("param") Integer userId);

    @PostMapping("/addAddress")
    public CommonResult addAddress(@RequestParam("addr") String addr,@RequestParam("userId")Integer userId,
                                   @RequestParam("realName") String realName);

    @PostMapping("/deleteAddress")
    public CommonResult deleteAddress(@RequestParam("id")Integer id);

    @PostMapping("/addShopCar")
    public CommonResult addShopCar(@RequestParam("userId")Integer userId,@RequestParam("seafoodId")Integer srafoodId);

    @PostMapping("/deleteShopCar")
    public CommonResult deleteShopCar(@RequestParam("userId")Integer userId,@RequestParam("seafoodId")Integer srafoodId);

    @PostMapping("/getShopCar")
    public CommonResult getShopCar(@RequestParam("userId")Integer userId);

    @PostMapping("/getComment")
    public CommonResult getAllCommentBySeafood(@RequestParam("seafoodId") Integer seafoodId);

    @PostMapping("/addComment")
    public CommonResult addComment(@RequestParam("fromUserId") Integer fromUserId, @RequestParam(value = "toUerId",required = false) Integer toUserId,
                                   @RequestParam("seafoodId") Integer seafoodId,
                                   @RequestParam(value = "parent",required = false) Integer parent,
                                   @RequestParam("level") Integer level,
                                   @RequestParam("content") String content,
                                   @RequestParam("fromUserName") String fromUserName,@RequestParam(value = "toUserName",required = false) String toUserName);

    @PostMapping("/getAllWaiter")
    public CommonResult getAllWaiter();
}
