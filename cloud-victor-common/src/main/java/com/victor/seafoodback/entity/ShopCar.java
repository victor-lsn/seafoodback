package com.victor.seafoodback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCar {
    private Integer id;
    private Integer userId;
    private Integer seafoodId;
    private Seafood seafood;
    private Integer count;
}
