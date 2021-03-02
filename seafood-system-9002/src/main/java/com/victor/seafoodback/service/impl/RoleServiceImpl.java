package com.victor.seafoodback.service.impl;

import com.victor.seafoodback.dao.RoleDao;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Menu;
import com.victor.seafoodback.entity.Role;
import com.victor.seafoodback.entity.User;
import com.victor.seafoodback.service.RoleService;
import com.victor.seafoodback.vo.RolePermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MenuServiceImpl menuService;

    @Override
    public CommonResult getAllRole() {
        List<Role> allRole = roleDao.getAllRole();
        for (Role role : allRole) {
            List<Menu> childrenList = menuService.getChildrenList(role.getChildren());
            role.setChildren(childrenList);
        }

        return new CommonResult(200, "获取角色列表成功", allRole);
    }

    @Override
    public CommonResult addRole(Role role) {
        if (roleDao.addRole(role) == 1) {
            return new CommonResult(200, "添加成功");
        }
        return new CommonResult(444, "添加失败");
    }

    @Override
    public CommonResult deleteRole(Integer roelId) {
        List<User> users = roleDao.selectUserByRoleId(roelId);
        if (users.size() > 0) {
            return new CommonResult(444, "删除失败，有用户关联此角色");
        }
        roleDao.deleteRoleById(roelId);
        return new CommonResult(200, "删除角色成功");
    }

    @Override
    public CommonResult getRoleById(Integer roleId) {
        Role role = roleDao.getRoleById(roleId);
        if (role != null && role.getChildren().size() > 0) {
            List<Integer> collect = role.getChildren().stream().filter(menu -> menu.getLevel() == 2).map(Menu::getId).collect(Collectors.toList());
            return new CommonResult(200, "获取角色权限ID列表成功", collect);
        }
        return new CommonResult(200, "获取角色权限ID列表成功", new ArrayList<>());
    }

    @Override
    @Transactional
    public CommonResult addRolePermission(Integer[] currentIdList, Integer roleId) {
        Role role = roleDao.getRoleById(roleId);
        if (role.getChildren().size() == 0 && currentIdList.length == 0) {
            return null;
        }
        if (role.getChildren().size() > 0) {
            //删除原有的
            roleDao.deleteRolePermissionByRoleId(roleId);
        }
        if (currentIdList.length > 0 && currentIdList[0]!=null) {
            //判断是否有二级目录但是没有一级目录
            ArrayList<Integer> integers = new ArrayList<>();
            for (int i = 0; i < currentIdList.length; i++) {
                Menu permission = roleDao.getMenuById(currentIdList[i]);
                integers.add(currentIdList[i]);
                if (permission.getLevel() == 2){
                    integers.add(permission.getParent());
                }
            }
            HashSet<Integer> currentKeys = new HashSet<>(integers);

            //添加现有的
            ArrayList<RolePermissionVo> rolePermissionVos = new ArrayList<>();
            for (Integer currentKey : currentKeys) {
                rolePermissionVos.add(new RolePermissionVo(roleId, currentKey));
            }

            roleDao.addRolePermission(rolePermissionVos);
        }
        return new CommonResult(200, "更新权限成功");
    }
}
