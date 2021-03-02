package com.victor.seafoodback.seafoodgood9004.service;


import com.victor.seafoodback.entity.Category;
import com.victor.seafoodback.entity.CommonResult;

import java.util.List;

public interface CategoryService {

    CommonResult getAllCategories();

    CommonResult addCategory(Category category);

    CommonResult deleteCategory(Integer categoryId);
}
