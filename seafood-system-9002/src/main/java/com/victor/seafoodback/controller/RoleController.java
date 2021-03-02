package com.victor.seafoodback.controller;

import cn.hutool.core.convert.Convert;
import com.victor.seafoodback.dao.RoleDao;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Role;
import com.victor.seafoodback.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/getAllRole")
    public CommonResult getAllRoles() {
        return roleService.getAllRole();
    }

    @PostMapping("/addRole")
    public CommonResult addRole(@RequestParam("name") String name, @RequestParam("describe") String describe) {
        return roleService.addRole(new Role(null, name, describe, null));
    }

    @PostMapping("/deleteRole")
    public CommonResult deleteRole(@RequestParam("roleId") Integer roleId) {
        return roleService.deleteRole(roleId);
    }

    @PostMapping("/getRoleById")
    public CommonResult getRoleById(@RequestParam("roleId") Integer roleId) {
        return roleService.getRoleById(roleId);
    }

    @PostMapping("/addRolePermission")
    public CommonResult addRolePermission(@RequestParam("currentIdList") String currentIdList, @RequestParam("roleId") Integer roleId) {
        String[] split = currentIdList.split(",");
        return roleService.addRolePermission(Convert.toIntArray(split), roleId);
    }
}
