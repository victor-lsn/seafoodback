package com.victor.seafoodback.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.victor.seafoodback.dao.UserDao;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.User;
import com.victor.seafoodback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public CommonResult getAllUser() {
        return new CommonResult(200, "获取所有用户列表成功", userDao.getAllUser());
    }

    @Override
    public CommonResult getUserForPage(Integer pageNo, Integer pageSize, String username, String phone, String role) {
        PageHelper.startPage(pageNo, pageSize);
        List<User> allUser = userDao.getAllUserBySearch(username, phone, role);
        PageInfo<User> userPageInfo = new PageInfo<>(allUser);
        return new CommonResult(200, "用户列表分页", userPageInfo);
    }

    @Override
    public CommonResult updateStatus(Integer userId, Integer flag) {
        if (userDao.updateStatus(userId, flag) == 1) {
            if (flag == 1) {
                return new CommonResult(200, "恢复此用户的使用成功");
            } else {
                return new CommonResult(200, "关闭此用户的使用成功");
            }
        }
        return new CommonResult(444, "修改失败");
    }

    @Override
    public CommonResult updateRole(Integer userId, Integer roleId) {
        if (userDao.updateRole(userId, roleId) == 1) {
            return new CommonResult(200, "修改角色成功");
        }
        return new CommonResult(444, "修改失败");
    }

    @Override
    public CommonResult getUserInfo(Integer userId) {
        return new CommonResult(200,"",userDao.getUserInfo(userId));
    }


}
