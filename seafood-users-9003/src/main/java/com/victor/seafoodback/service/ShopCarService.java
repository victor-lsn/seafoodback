package com.victor.seafoodback.service;

import com.victor.seafoodback.entity.CommonResult;
import org.apache.ibatis.annotations.Param;

public interface ShopCarService {

    CommonResult addSeafoodToCar(Integer userId,Integer seafoodId);

    CommonResult deleteSeafoodFromCar(Integer userId,Integer seafoodId);

    CommonResult getShopCar(Integer userId);
}
