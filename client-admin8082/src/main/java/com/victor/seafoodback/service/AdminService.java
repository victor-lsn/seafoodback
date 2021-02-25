package com.victor.seafoodback.service;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Menu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("seafood-syatem-9002")
public interface AdminService {

    @PostMapping("/menu")
    CommonResult getMenu();

    @PostMapping("/addMenu")
    public CommonResult addMenu(@RequestParam(value = "name") String name, @RequestParam(value = "path", required = false) String path,
                                @RequestParam(value = "parent",required = false) Integer parent, @RequestParam(value = "icon") String icon);
}
