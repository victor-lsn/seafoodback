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
        List<Menu> allMenu = menuDao.getAllMenu();
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
        return new CommonResult(200, "获取菜单成功", menus);
    }

    @Override
    public CommonResult addMenu(Menu menu) {
        if (menuDao.addMenu(menu) == 1) {
            return new CommonResult(200, "添加权限成功");
        }
        return new CommonResult(444, "添加失败");
    }
}
