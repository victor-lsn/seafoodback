package com.victor.seafoodback.dao;

import com.victor.seafoodback.entity.SeafoodPic;
import com.victor.seafoodback.entity.ShopCar;
import com.victor.seafoodback.service.ShopCarService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShopCarDao {

    Integer addSeafoodToCar(@Param("userId") Integer userId,@Param("seafoodId") Integer seafoodId);

    Integer deleteSeafoodFromCar(@Param("userId") Integer userId,@Param("seafoodId") Integer seafoodId);

    List<ShopCar> getShopCar(Integer userId);

    ShopCar selectShopCarBy2Id(@Param("userId") Integer userId,@Param("seafoodId") Integer seafoodId);
}
