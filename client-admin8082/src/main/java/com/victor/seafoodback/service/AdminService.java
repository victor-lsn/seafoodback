package com.victor.seafoodback.service;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Menu;
import com.victor.seafoodback.entity.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Component
@FeignClient("seafood-system-9002")
public interface AdminService {

    @PostMapping("/menu")
    CommonResult getMenu(@RequestParam("roleId")Integer roleId);

    @PostMapping("/allMenu")
    public CommonResult getAllMenu();

    @PostMapping("/addMenu")
    public CommonResult addMenu(@RequestParam(value = "name") String name, @RequestParam(value = "path", required = false) String path,
                                @RequestParam(value = "parent",required = false) Integer parent, @RequestParam(value = "icon") String icon);

    @PostMapping("/getAllRole")
    public CommonResult getAllRoles();

    @PostMapping("/addRole")
    public CommonResult addRole(@RequestParam("name") String name, @RequestParam("describe") String describe);

    @PostMapping("/deleteRole")
    public CommonResult deleteRole(@RequestParam("roleId") Integer roleId);

    @PostMapping("/getRoleById")
    public CommonResult getRoleById(@RequestParam("roleId") Integer roleId);

    @PostMapping("/addRolePermission")
    public CommonResult addRolePermission(@RequestParam("currentIdList") String currentIdList,@RequestParam("roleId")Integer roleId);

    @PostMapping("/getOrderCountByMonth")
    public CommonResult getOrderCountByMonth();

    @PostMapping("/getOrderMoneyByMonth")
    public CommonResult getOrderMoneyByMonth();

    @PostMapping("/getSaleCountByCategory")
    public CommonResult getSaleCountByCategory();

    @PostMapping("/getOrderCountMap")
    public CommonResult getOrderCountMap();

    @RequestMapping("/getMenuById")
    public CommonResult getMenuById(@RequestParam("id") Integer permissionId);

    @RequestMapping("/updateMenuById")
    public CommonResult updateMenuById(Menu menu);

    @PostMapping("/getOrderCountByMonth2")
    public CommonResult getOrderCountByMonth2(@RequestParam("year") String year);

    @PostMapping("/getOrderMoneyByMonth2")
    public CommonResult getOrderMoneyByMonth2(@RequestParam("year") String year);

    @PostMapping("/getOrderCountMap2")
    public CommonResult getOrderCountMap2(@RequestParam("year") Integer year);
}
