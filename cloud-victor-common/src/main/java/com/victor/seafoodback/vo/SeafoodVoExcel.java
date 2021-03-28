package com.victor.seafoodback.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Date;

@Data
@AllArgsConstructor
public class SeafoodVoExcel {
    private Integer id;
    @ExcelProperty("海鲜名")
    private String name;
    @ExcelProperty("进价")
    private Double inPrice;
    @ExcelProperty("预售价")
    private Double outPrice;
    @ExcelProperty("折扣")
    private Double discount;
    @ExcelProperty("库存")
    private Integer count;
    @ExcelProperty("子分类ID")
    private Integer categoryId;
    private Date saleDate;
    @ExcelProperty("标题")
    private String title;
    @ExcelProperty("详情")
    private String info;
    @ExcelProperty("图片")
    private String pics;

    public SeafoodVoExcel() {
        this.saleDate = new Date();
    }
}
