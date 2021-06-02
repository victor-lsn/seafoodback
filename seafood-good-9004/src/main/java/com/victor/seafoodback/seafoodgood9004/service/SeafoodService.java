package com.victor.seafoodback.seafoodgood9004.service;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Seafood;
import com.victor.seafoodback.vo.SeafoodVoExcel;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SeafoodService {

    CommonResult getAllSeafoods(String name, Double lowPrice, Double highPrice,
                                Date lowDate, Date highDate, Integer category,Integer pageNo,Integer pageSize);

    CommonResult getSeafoodById(Integer id);

    CommonResult addSeafood(Seafood seafood,String picName);

    CommonResult updateSeafood(Seafood seafood,String picName);

    CommonResult deleteSeafoodPic(Integer seafoodId,String picName);

    CommonResult deleteSeafood(Integer id);

    CommonResult batchAddSeafood(List<SeafoodVoExcel> list);

    CommonResult getFireGood();

    CommonResult getAllGoods(String name,Double lowPrice,Double highPrice,String paixu,Integer pageNo,Integer pageSize);

    CommonResult getSearchGoods(String keywords,String paixu,Integer categoryId);

    CommonResult getSeafoodCount(Integer id);

    CommonResult getSeafoodCountByList(String[] listIdCount);
}
