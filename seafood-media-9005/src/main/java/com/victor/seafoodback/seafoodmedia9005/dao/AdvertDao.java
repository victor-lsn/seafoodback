package com.victor.seafoodback.seafoodmedia9005.dao;

import com.victor.seafoodback.entity.Advert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdvertDao {

    List<Advert> getAllAdverts();

    Integer updateAdvert(@Param("id") Integer id, @Param("name") String name);
}
