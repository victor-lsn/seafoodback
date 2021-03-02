package com.victor.seafoodback.seafoodgood9004.service;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Seafood;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface SeafoodService {

    CommonResult getAllSeafoods(String name, Double lowPrice, Double highPrice,
                                Date lowDate, Date highDate, Integer category,Integer pageNo,Integer pageSize);

    CommonResult getSeafoodById(Integer id);
}
