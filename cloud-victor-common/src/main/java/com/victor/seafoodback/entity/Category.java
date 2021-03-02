package com.victor.seafoodback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;
    private String name;
    private String categoryDesc;
    private Integer parent;
    private Integer level;
    private List<Category> children;
}
