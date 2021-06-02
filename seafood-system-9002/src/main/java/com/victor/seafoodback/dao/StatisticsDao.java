package com.victor.seafoodback.dao;

import com.victor.seafoodback.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface StatisticsDao {

    Map<Integer, Integer> getOrderCountByMonth();

    Map<Integer, Double> getOrderMoneyByMonth();

    Integer getSaleCountByCategory(@Param("categoryParentId") Integer categoryParentId);

    List<Category> getFirstLevelCategory();

    Map<String,Integer> getOrderCountMap(@Param("nowDay") String nowDay,@Param("nowYear") String nowYear,
                                         @Param("nextYear") String nextYear);
}
