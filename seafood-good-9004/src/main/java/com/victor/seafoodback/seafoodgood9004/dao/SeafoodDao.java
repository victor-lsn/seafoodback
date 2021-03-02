package com.victor.seafoodback.seafoodgood9004.dao;

import com.victor.seafoodback.entity.Seafood;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface SeafoodDao {

    List<Seafood> getAllSeafoods(@Param("name")String name, @Param("lowPrice")Double lowPrice, @Param("highPrice")Double highPrice,
                                 @Param("lowDate")Date lowDate,@Param("highDate")Date highDate,@Param("category")Integer category);

    Seafood getSeafoodById(Integer id);
}
