package com.victor.seafoodback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seafood {
    private Integer id;
    private String name;
    private Double inPrice;
    private Double outPrice;
    private Double discount;
    private Integer count;
    private Integer categoryId;
    private Category category;
    private Date saleDate;
    private String title;
    private String info;
    private List<SeafoodPic> pics;
    private Integer saleCount;
}
