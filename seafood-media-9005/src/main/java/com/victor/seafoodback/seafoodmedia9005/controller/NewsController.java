package com.victor.seafoodback.seafoodmedia9005.controller;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.News;
import com.victor.seafoodback.seafoodmedia9005.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class NewsController {

    @Autowired
    private NewsServiceImpl newsService;

    @PostMapping("/getAllNews")
    public CommonResult getAllNews(@RequestParam(value = "lowDate", required = false) Date lowDate, @RequestParam(value = "highDate", required = false) Date highDate,
                                   @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        return newsService.getAllNews(lowDate, highDate, pageNo, pageSize);
    }

    @PostMapping("/addNews")
    public CommonResult addNews(@RequestParam("content") String content) {
        return newsService.addNews(new News(null, content, new Date()));
    }

    @PostMapping("/deleteNews")
    public CommonResult deleteNews(@RequestParam("id") Integer id) {
        return newsService.deleteNews(id);
    }

    @PostMapping("/updateNews")
    public CommonResult updateNews(@RequestParam("content") String content, @RequestParam("id") Integer id) {
        return newsService.updateNews(new News(id, content, null));
    }
}
