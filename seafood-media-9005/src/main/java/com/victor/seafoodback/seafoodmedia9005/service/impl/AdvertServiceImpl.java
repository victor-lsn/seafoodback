package com.victor.seafoodback.seafoodmedia9005.service.impl;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.seafoodmedia9005.dao.AdvertDao;
import com.victor.seafoodback.seafoodmedia9005.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    private AdvertDao advertDao;

    @Override
    public CommonResult getAllAdverts() {
        return new CommonResult(200, "获取成功", advertDao.getAllAdverts());
    }

    @Override
    public CommonResult updateAdvert(Integer id, String name) {
        advertDao.updateAdvert(id, name);
        return new CommonResult(200,"更新图片成功");
    }
}
