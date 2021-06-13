package com.victor.seafoodback.service;

import com.victor.seafoodback.entity.CommonResult;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface StatisticsService {

    CommonResult getOrderCountByMonth();

    CommonResult getOrderMoneyByMonth();

    CommonResult getSaleCountByCategory();

    CommonResult getOrderCountMap();

    CommonResult getOrderCountMap2(Integer year);

    CommonResult getOrderCountByMonth2(String year);

    CommonResult getOrderMoneyByMonth2(String year);
}
