package com.victor.seafoodback.service;

import com.victor.seafoodback.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
