package com.victor.seafoodback.seafoodmedia9005.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.News;
import com.victor.seafoodback.seafoodmedia9005.dao.NewsDao;
import com.victor.seafoodback.seafoodmedia9005.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public CommonResult getAllNews(Date lowDate, Date highDate,Integer pageNo,Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return new CommonResult(200,"获取新闻列表成功",new PageInfo<>(newsDao.getAllNews(lowDate, highDate)));
    }

    @Override
    public CommonResult addNews(News news) {
        newsDao.addNews(news);
        return new CommonResult(200,"添加新闻成功");
    }

    @Override
    public CommonResult deleteNews(Integer id) {
        newsDao.deleteNews(id);
        return new CommonResult(200,"删除新闻成功");
    }

    @Override
    public CommonResult updateNews(News news) {
        newsDao.updateNews(news);
        return new CommonResult(200,"更新新闻成功");
    }
}
