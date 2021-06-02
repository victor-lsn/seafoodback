package com.victor.seafoodback.service;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuService {

    CommonResult getAllMenu();

    CommonResult addMenu(Menu menu);

    CommonResult getMenuByRoleId(Integer roleId);

    CommonResult getMenuById(Integer permissionId);

    CommonResult updateMenuById(Menu menu);
}
