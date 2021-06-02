package com.victor.seafoodback.controller;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Menu;
import com.victor.seafoodback.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class MenuController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/menu")
    public CommonResult getMenu(@RequestParam("roleId")Integer roleId) {
        return adminService.getMenu(roleId);
    }

    @PostMapping("/allMenu")
    public CommonResult getAllMenu() {
        return adminService.getAllMenu();
//        return menuService.getMenuByRoleId(roleId);
    }

    @PostMapping("/addMenu")
    public CommonResult addMenu(@RequestParam(value = "name") String name, @RequestParam(value = "path", required = false) String path,
                                @RequestParam(value = "parent",required = false) Integer parent, @RequestParam(value = "icon") String icon) {
        /*Menu menu = new Menu();
        if (path == null){
            menu.setLevel(1);
        }else {
            menu.setLevel(2);
        }
        menu.setName(name);
        menu.setPath(path);
        menu.setParent(parent);
        menu.setIcon(icon);*/
        return adminService.addMenu(name, path, parent, icon);
    }

    @PostMapping("/getOrderCountByMonth")
    public CommonResult getOrderCountByMonth(){
        return adminService.getOrderCountByMonth();
    }

    @PostMapping("/getOrderMoneyByMonth")
    public CommonResult getOrderMoneyByMonth(){
        return adminService.getOrderMoneyByMonth();
    }

    @PostMapping("/getSaleCountByCategory")
    public CommonResult getSaleCountByCategory(){
        return adminService.getSaleCountByCategory();
    }

    @PostMapping("/getOrderCountMap")
    public CommonResult getOrderCountMap(){
        return adminService.getOrderCountMap();
    }

    @RequestMapping("/getMenuById")
    public CommonResult getMenuById(@RequestParam("id") Integer permissionId) {
        return adminService.getMenuById(permissionId);
    }

    @RequestMapping("/updateMenuById")
    public CommonResult updateMenuById(Menu menu) {
        System.out.println(menu);
        return adminService.updateMenuById(menu);
    }
}
