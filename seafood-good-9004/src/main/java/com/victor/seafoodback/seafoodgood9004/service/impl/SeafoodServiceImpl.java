package com.victor.seafoodback.seafoodgood9004.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Seafood;
import com.victor.seafoodback.entity.SeafoodPic;
import com.victor.seafoodback.seafoodgood9004.config.SeafoodExcelListener;
import com.victor.seafoodback.seafoodgood9004.dao.SeafoodDao;
import com.victor.seafoodback.seafoodgood9004.service.SeafoodService;
import com.victor.seafoodback.vo.SeafoodVoExcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class SeafoodServiceImpl implements SeafoodService {
    @Autowired
    private SeafoodDao seafoodDao;

    @Autowired
    private SeafoodExcelListener seafoodExcelListener;

    @Override
    public CommonResult getAllSeafoods(String name, Double lowPrice, Double highPrice,
                                       Date lowDate, Date highDate, Integer category, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        //System.out.println(category);
        List<Seafood> allSeafoods = seafoodDao.getAllSeafoods(name, lowPrice, highPrice, lowDate, highDate, category);
        return new CommonResult(200, "获取商品成功", new PageInfo<Seafood>(allSeafoods));
    }

    @Override
    public CommonResult getSeafoodById(Integer id) {
        return new CommonResult(200, "获取商品详情成功", seafoodDao.getSeafoodById(id));
    }

    @Override
    @Transactional
    public CommonResult addSeafood(Seafood seafood, String picName) {
        //TODO 先判断是否添加过同样的商品
        //添加商品
        seafoodDao.addSeafood(seafood);
        //System.out.println(seafood.getId());
        //添加与商品关联的图片
        seafoodDao.addGoodPic(new SeafoodPic(null, picName, seafood.getId(), null));
        return new CommonResult(200, "添加成功");
    }

    @Override
    public CommonResult updateSeafood(Seafood seafood, String picName) {
        seafoodDao.updateSeafood(seafood);
        if (picName!=null){
            HashMap<String, Object> map = new HashMap<>();
            map.put("seafoodId",seafood.getId());
            map.put("picName",picName);
            if (seafoodDao.getSeafoodPic(map) == 0) {
                seafoodDao.addGoodPic(new SeafoodPic(null, picName, seafood.getId(), null));
            }
        }
        return new CommonResult(200, "修改成功");
    }

    @Override
    public CommonResult deleteSeafoodPic(Integer seafoodId,String picName) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("seafoodId",seafoodId);
        map.put("picName",picName);
        seafoodDao.deleteSeafoodPic(map);
        return new CommonResult(200,"删除照片成功");
    }

    @Override
    public CommonResult deleteSeafood(Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("seafoodId",id);
        map.put("picName",null);
        seafoodDao.deleteSeafoodPic(map);
        seafoodDao.deleteSeafood(id);
        return new CommonResult(200,"删除海鲜产品成功");
    }

    @Override
    public CommonResult batchAddSeafood(List<SeafoodVoExcel> list) {
        seafoodDao.batchAddSeafood(list);
        ArrayList<SeafoodPic> seafoodPics = new ArrayList<>();
        for (SeafoodVoExcel seafoodVoExcel : list) {
            String pics = seafoodVoExcel.getPics();
            String[] splits = pics.split("/");
            //根据seafoodVoExcel
            for (String split : splits) {
                seafoodPics.add(new SeafoodPic(null,split,seafoodVoExcel.getId(),null));
            }
        }
        seafoodDao.batchSeafoodPic(seafoodPics);
        return new CommonResult(200,"批量插入成功");
    }

    @Override
    public CommonResult getFireGood() {
        log.info("top10的数量："+seafoodDao.getFireGood().size());
        return new CommonResult(200,"获取销量最高top10成功",seafoodDao.getFireGood());
    }

    @Override
    public CommonResult getAllGoods(String name, Double lowPrice, Double highPrice, String paixu, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Seafood> allGoods = seafoodDao.getAllGoods(name, lowPrice, highPrice, paixu);
        return new CommonResult(200, "获取商品成功", new PageInfo<>(allGoods));
    }


}
