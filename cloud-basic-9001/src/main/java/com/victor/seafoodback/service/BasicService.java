package com.victor.seafoodback.service;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.User;

public interface BasicService {

    CommonResult register(User user);

    CommonResult login(User user);
}
