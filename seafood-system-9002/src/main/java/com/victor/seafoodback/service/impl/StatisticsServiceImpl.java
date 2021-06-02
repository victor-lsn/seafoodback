package com.victor.seafoodback.service.impl;

import com.victor.seafoodback.dao.MenuDao;
import com.victor.seafoodback.dao.StatisticsDao;
import com.victor.seafoodback.entity.Category;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Menu;
import com.victor.seafoodback.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsDao statisticsDao;



    @Override
    public CommonResult getOrderCountByMonth() {
        Map<Integer, Integer> orderCountByMonth = statisticsDao.getOrderCountByMonth();
        return new CommonResult(200,"获取每月订单成功",orderCountByMonth);
    }

    @Override
    public CommonResult getOrderMoneyByMonth() {
        Map<Integer, Double> orderMoneyByMonth = statisticsDao.getOrderMoneyByMonth();
        return new CommonResult(200,"获取每月销售额成功",orderMoneyByMonth);
    }

    @Override
    public CommonResult getSaleCountByCategory() {
        List<Category> firstLevelCategory = statisticsDao.getFirstLevelCategory();
        System.out.println(firstLevelCategory);
        HashMap<String, Integer> map = new HashMap<>();
        for (Category category : firstLevelCategory) {
            Integer count = statisticsDao.getSaleCountByCategory(category.getId());
            map.put(category.getName(),count);
        }

        System.out.println(map);
        return new CommonResult(200,"获取每种分类成功",map);
    }

    @Override
    public CommonResult getOrderCountMap() {
        Date date = new Date();
        String nowYear = new SimpleDateFormat("yyyy").format(date);
        String nextYear = String.valueOf(Integer.parseInt(nowYear) + 1);
        String nowDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println(nowDay);
        System.out.println(nowYear);
        System.out.println(nextYear);
        return new CommonResult(200,"获取今日与今年订单数成功",
                statisticsDao.getOrderCountMap(nowDay,nowYear,nextYear));
    }


}
