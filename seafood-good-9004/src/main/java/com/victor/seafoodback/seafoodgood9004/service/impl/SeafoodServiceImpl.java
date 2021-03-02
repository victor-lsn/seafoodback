package com.victor.seafoodback.seafoodgood9004.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Seafood;
import com.victor.seafoodback.seafoodgood9004.dao.SeafoodDao;
import com.victor.seafoodback.seafoodgood9004.service.SeafoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SeafoodServiceImpl implements SeafoodService {
    @Autowired
    private SeafoodDao seafoodDao;

    @Override
    public CommonResult getAllSeafoods(String name, Double lowPrice, Double highPrice,
                                       Date lowDate, Date highDate, Integer category,Integer pageNo,Integer pageSize) {
        List<Seafood> allSeafoods = seafoodDao.getAllSeafoods(name, lowPrice, highPrice, lowDate, highDate, category);
        PageHelper.startPage(pageNo, pageSize);
        return new CommonResult(200, "获取商品成功", new PageInfo<Seafood>(allSeafoods));
    }

    @Override
    public CommonResult getSeafoodById(Integer id) {
        return new CommonResult(200,"获取商品详情成功",seafoodDao.getSeafoodById(id));
    }
}
