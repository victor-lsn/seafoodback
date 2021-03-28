package com.victor.seafoodback.seafoodgood9004.dao;

import com.victor.seafoodback.entity.Seafood;
import com.victor.seafoodback.entity.SeafoodPic;
import com.victor.seafoodback.vo.SeafoodVoExcel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SeafoodDao {

    List<Seafood> getAllSeafoods(@Param("name")String name, @Param("lowPrice")Double lowPrice, @Param("highPrice")Double highPrice,
                                 @Param("lowDate")Date lowDate,@Param("highDate")Date highDate,@Param("category")Integer category);

    Seafood getSeafoodById(Integer id);

    Integer addGoodPic(SeafoodPic seafoodPic);

    Integer updateGoodPic(SeafoodPic seafoodPic);

    Integer addSeafood(Seafood seafood);

    Integer updateSeafood(Seafood seafood);

    Integer getSeafoodPic(Map map);

    Integer deleteSeafoodPic(Map map);

    Integer deleteSeafood(Integer id);

    Integer batchAddSeafood(List<SeafoodVoExcel> list);

    Integer batchSeafoodPic(List<SeafoodPic> list);

    List<Seafood> getFireGood();

    List<Seafood> getAllGoods(@Param("name")String name, @Param("lowPrice")Double lowPrice, @Param("highPrice")Double highPrice,
                              @Param("paixu")String paixu);
}
