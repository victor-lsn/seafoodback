package com.victor.seafoodback.service.impl;

import com.victor.seafoodback.dao.ShopCarDao;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.ShopCar;
import com.victor.seafoodback.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopCarServiceImpl implements ShopCarService {

    @Autowired
    private ShopCarDao carDao;

    @Override
    public CommonResult addSeafoodToCar(Integer userId, Integer seafoodId) {
        //判断是否添加过此商品到购物车如果已经添加则原来数量加1
        ShopCar shopCar = carDao.selectShopCarBy2Id(userId, seafoodId);
        if (shopCar != null) {
            return new CommonResult(444, "此商品已经存在于购物车中");
        }
        carDao.addSeafoodToCar(userId, seafoodId);
        return new CommonResult(200, "添加到购物车成功");
    }

    @Override
    public CommonResult deleteSeafoodFromCar(Integer userId, Integer seafoodId) {
        carDao.deleteSeafoodFromCar(userId, seafoodId);
        return new CommonResult(200, "移除商品成功");
    }

    @Override
    public CommonResult getShopCar(Integer userId) {
        return new CommonResult(200, "获取购物车成功", carDao.getShopCar(userId));
    }
}
