package com.victor.seafoodback.service;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Menu;

import java.util.List;

public interface MenuService {

    CommonResult getAllMenu();

    CommonResult addMenu(Menu menu);
}
