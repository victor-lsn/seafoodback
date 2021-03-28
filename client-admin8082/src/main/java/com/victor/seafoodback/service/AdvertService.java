package com.victor.seafoodback.service;


import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.News;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Component
@FeignClient("seafood-media-9005")
public interface AdvertService {

    @PostMapping("/getAllAdvert")
    public CommonResult getAllAdverts();

    @PostMapping("/getAllNews")
    public CommonResult getAllNews(@RequestParam(value = "lowDate", required = false) Date lowDate, @RequestParam(value = "highDate", required = false) Date highDate,
                                   @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    @PostMapping("/addNews")
    public CommonResult addNews(@RequestParam("content") String content);

    @PostMapping("/deleteNews")
    public CommonResult deleteNews(@RequestParam("id") Integer id);

    @PostMapping("/updateNews")
    public CommonResult updateNews(@RequestParam("content") String content,@RequestParam("id") Integer id);

    @PostMapping("/updateAdvert")
    public CommonResult updateAdvert(@RequestParam("id")Integer id,@RequestParam("name")String name);
}
