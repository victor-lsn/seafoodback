package com.victor.seafoodback.seafoodgood9004.controller;

import com.victor.seafoodback.entity.Category;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.seafoodgood9004.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/getAllCategory")
    public CommonResult getAllCategory() {
        return categoryService.getAllCategories();
    }


    @PostMapping("/addCategory")
    public CommonResult addCategory(@RequestParam("name") String name, @RequestParam("desc") String desc,
                                    @RequestParam(value = "parent", required = false) Integer parent) {
        Category category = null;
        if (parent == null) {
            category = new Category(null, name, desc, parent, 1, null);
        } else {
            category = new Category(null, name, desc, parent, 2, null);
        }
        return categoryService.addCategory(category);
    }

    @PostMapping("/deleteCategory")
    public CommonResult deleteCategory(@RequestParam("categoryId") Integer categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
