package com.victor.seafoodback.controller;

import cn.hutool.core.convert.Convert;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Role;
import com.victor.seafoodback.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/admin")
public class RoleController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/getAllRole")
    public CommonResult getAllRoles() {
        return adminService.getAllRoles();
    }

    @PostMapping("/addRole")
    public CommonResult addRole(@RequestParam("name") String name, @RequestParam("describe") String describe) {
        return adminService.addRole(name, describe);
    }

    @PostMapping("/deleteRole")
    public CommonResult deleteRole(@RequestParam("roleId") Integer roleId) {
        return adminService.deleteRole(roleId);
    }

    @PostMapping("/getRoleById")
    public CommonResult getRoleById(@RequestParam("roleId") Integer roleId) {
        return adminService.getRoleById(roleId);
    }

    @PostMapping("/addRolePermission")
    public CommonResult addRolePermission(@RequestParam("currentIdList") String currentIdList, @RequestParam("roleId") Integer roleId) {
        return adminService.addRolePermission(currentIdList, roleId);
    }
}
