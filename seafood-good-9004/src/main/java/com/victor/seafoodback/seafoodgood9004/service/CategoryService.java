package com.victor.seafoodback.seafoodgood9004.service;


import com.victor.seafoodback.entity.Category;
import com.victor.seafoodback.entity.CommonResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryService {

    CommonResult getAllCategories();

    CommonResult addCategory(Category category);

    CommonResult deleteCategory(Integer categoryId);

    CommonResult getParentCategoryVo();

    CommonResult getCategoryById(Integer id);

    CommonResult updateCategory(Category category);
}
