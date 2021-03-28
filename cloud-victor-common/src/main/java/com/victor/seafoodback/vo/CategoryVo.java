package com.victor.seafoodback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo {
    private Integer id;
    private String name;
    private Integer level;
    private String pic;
}
