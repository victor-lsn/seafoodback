package com.victor.seafoodback.seafoodmedia9005.service;

import com.victor.seafoodback.entity.CommonResult;

public interface AdvertService {

    CommonResult getAllAdverts();

    CommonResult updateAdvert(Integer id,String name);
}
