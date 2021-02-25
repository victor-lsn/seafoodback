package com.victor.seafoodback.service.impl;

import com.victor.seafoodback.dao.BasicDao;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.User;
import com.victor.seafoodback.service.BasicService;
import com.victor.seafoodback.util.JwtUtils;
import com.victor.seafoodback.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BasicServiceImpl implements BasicService {
    @Autowired
    private BasicDao basicDao;

    @Override
    public CommonResult register(User user) {
        if (basicDao.getUserByPhone(user.getPhone()) > 0) {
            return new CommonResult(400, "此手机号已经注册过");
        }
        String s = Md5Util.StringInMd5(user.getPassword());
        user.setPassword(s);
        basicDao.register(user);
        return new CommonResult(200, "注册成功");
    }

    @Override
    public CommonResult login(User user) {
        user.setPassword(Md5Util.StringInMd5(user.getPassword()));
        User login = basicDao.login(user);
        if (login == null) {
            return new CommonResult(400, "用户名或密码错误");
        }
        //返回token
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", login);
        map.put("token", JwtUtils.create(login.getId().toString(), login.getName()));
        return new CommonResult(200, "登录成功", map);
    }


}
