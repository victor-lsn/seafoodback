package com.victor.seafoodback.seafoodgood9004.dao;

import com.victor.seafoodback.entity.Category;
import com.victor.seafoodback.vo.CategoryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryDao {

    List<Category> getAllCategories();

    Integer addCategory(Category category);

    List<Category> getCategoryListByParent(Integer categoryId);

    Integer deleteCategory(Integer categoryId);

    List<CategoryVo> getParentCategoryVo();

    Category getCategoryById(@Param("id") Integer id);

    Integer updateCategory(Category category);
}
