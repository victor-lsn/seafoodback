package com.victor.seafoodback.controller;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Menu;
import com.victor.seafoodback.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping("/menu")
    public CommonResult getMenu() {
        return menuService.getAllMenu();
    }

    @PostMapping("/addMenu")
    public CommonResult addMenu(@RequestParam(value = "name") String name, @RequestParam(value = "path", required = false) String path,
                                @RequestParam(value = "parent",required = false) Integer parent, @RequestParam(value = "icon") String icon) {
        Menu menu = new Menu();
        if (path == null){
            menu.setLevel(1);
        }else {
            menu.setLevel(2);
        }
        menu.setName(name);
        menu.setPath(path);
        menu.setParent(parent);
        menu.setIcon(icon);
        return menuService.addMenu(menu);
    }
}
