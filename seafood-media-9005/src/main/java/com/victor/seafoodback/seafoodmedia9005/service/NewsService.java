package com.victor.seafoodback.seafoodmedia9005.service;

import com.victor.seafoodback.entity.Comment;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.News;

import java.util.Date;
import java.util.List;

public interface NewsService {

    CommonResult getAllNews(Date lowDate, Date highDate,Integer pageNo,Integer pageSize);

    CommonResult addNews(News news);

    CommonResult deleteNews(Integer id);

    CommonResult updateNews(News news);
}
