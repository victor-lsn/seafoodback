package com.victor.seafoodback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    private Integer id;
    private String path;
    private Integer level;
    private Integer parent;
    private String icon;
    private String name;
}
