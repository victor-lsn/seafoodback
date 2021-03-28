package com.victor.seafoodback.seafoodmedia9005.dao;

import com.victor.seafoodback.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface NewsDao {

    List<News> getAllNews(@Param("lowDate")Date lowDate,@Param("highDate")Date highDate);

    Integer addNews(News news);

    Integer deleteNews(Integer id);

    Integer updateNews(News news);
}
