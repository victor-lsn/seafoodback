package com.victor.seafoodback.service;

import com.victor.seafoodback.entity.CommonResult;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface StatisticsService {

    CommonResult getOrderCountByMonth();

    CommonResult getOrderMoneyByMonth();

    CommonResult getSaleCountByCategory();

    CommonResult getOrderCountMap();
}
