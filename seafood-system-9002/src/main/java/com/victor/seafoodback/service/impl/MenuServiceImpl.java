package com.victor.seafoodback.service.impl;

import com.victor.seafoodback.dao.MenuDao;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Menu;
import com.victor.seafoodback.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public CommonResult getAllMenu() {
        //TODO 没有关联角色与权限表，这是所有权限
        List<Menu> allMenu = menuDao.getAllMenu();
        return new CommonResult(200, "获取菜单成功", getChildrenList(allMenu));
    }

    @Override
    public CommonResult addMenu(Menu menu) {
        if (menuDao.addMenu(menu) == 1) {
            return new CommonResult(200, "添加权限成功");
        }
        return new CommonResult(444, "添加失败");
    }

    @Override
    public CommonResult getMenuByRoleId(Integer roleId) {
        List<Menu> menu = menuDao.getMenuByRoleId(roleId);
        return new CommonResult(200,"根据角色获取菜单成功",getChildrenList(menu));
    }

    @Override
    public CommonResult getMenuById(Integer permissionId) {
        return new CommonResult(200,"根据ID获取权限成功",menuDao.getMenuById(permissionId));
    }

    @Override
    public CommonResult updateMenuById(Menu menu) {
        Integer status = menuDao.updateMenuById(menu);
        if (status != 1){
            return new CommonResult(444,"修改权限信息失败",null);
        }
        return new CommonResult(200,"修改权限信息成功",null);
    }

    public List<Menu> getChildrenList(List<Menu> allMenu) {
        ArrayList<Menu> menus = new ArrayList<>();
        for (Menu menu : allMenu) {
            if (menu.getLevel() == 1) {
                ArrayList<Menu> children = new ArrayList<>();
                for (Menu sub : allMenu) {
                    if (menu.getId() == sub.getParent()) {
                        children.add(sub);
                    }
                }
                menu.setChildren(children);
                menus.add(menu);
            }
        }
        return menus;
    }
}
