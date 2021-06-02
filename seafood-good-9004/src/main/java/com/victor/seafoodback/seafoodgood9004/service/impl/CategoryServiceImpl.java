package com.victor.seafoodback.seafoodgood9004.service.impl;

import com.victor.seafoodback.entity.Category;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Menu;
import com.victor.seafoodback.seafoodgood9004.dao.CategoryDao;
import com.victor.seafoodback.seafoodgood9004.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public CommonResult getAllCategories() {
        List<Category> allCategories = categoryDao.getAllCategories();
        return new CommonResult(200, "获取商品分类成功", getChildrenList(allCategories));
    }

    @Override
    public CommonResult addCategory(Category category) {
        Integer integer = categoryDao.addCategory(category);
        if (integer == 1) {
            return new CommonResult(200, "添加商品分类成功");
        }
        return new CommonResult(444, "天机商品分类失败");
    }

    @Override
    public CommonResult deleteCategory(Integer categoryId) {
        List<Category> categories = categoryDao.getCategoryListByParent(categoryId);
        if (categories.size() > 0) {
            return new CommonResult(444, "删除分类失败，含有子分类");
        }
        categoryDao.deleteCategory(categoryId);
        return new CommonResult(200, "删除分类成功");
    }


    public List<Category> getChildrenList(List<Category> allCategories) {
        ArrayList<Category> categories = new ArrayList<>();
        for (Category category : allCategories) {
            if (category.getLevel() == 1) {
                ArrayList<Category> children = new ArrayList<>();
                for (Category sub : allCategories) {
                    if (category.getId() == sub.getParent()) {
                        children.add(sub);
                    }
                }
                category.setChildren(children);
                categories.add(category);
            }
        }
        return categories;
    }


    @Override
    public CommonResult getParentCategoryVo() {
        return new CommonResult(200, "获取一级分类成功", categoryDao.getParentCategoryVo());
    }

    @Override
    public CommonResult getCategoryById(Integer id) {
        return new CommonResult(200, "根据ID获取商品分类成功", categoryDao.getCategoryById(id));
    }

    @Override
    public CommonResult updateCategory(Category category) {
        if (categoryDao.updateCategory(category) == 1){
            return new CommonResult(200,"更新海鲜分类成功");
        }
        return new CommonResult(444,"更新海鲜分类失败");
    }

}
