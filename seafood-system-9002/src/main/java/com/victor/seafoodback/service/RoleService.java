package com.victor.seafoodback.service;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Role;

import java.util.List;

public interface RoleService {

    CommonResult getAllRole();

    CommonResult addRole(Role role);

    CommonResult deleteRole(Integer roelId);

    CommonResult getRoleById(Integer roleId);

    CommonResult addRolePermission(Integer[] currentIdList,Integer roleId);
}
