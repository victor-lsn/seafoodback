package com.victor.seafoodback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeafoodPic {
    private Integer id;
    private String name;
    private Integer seafoodId;
    private String picPath;
}
