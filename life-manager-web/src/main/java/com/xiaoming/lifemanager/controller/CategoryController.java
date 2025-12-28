package com.xiaoming.lifemanager.controller;

import com.xiaoming.lifemanager.common.Result;
import com.xiaoming.lifemanager.entity.Category;
import com.xiaoming.lifemanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Result<Void> addCategory(@RequestBody Category category) {
        int result = categoryService.addCategory(category);
        return result > 0 ? Result.success() : Result.error("新增失败");
    }

    @PostMapping("/update")
    public Result<Void> updateCategory(@RequestBody Category category) {
        int result = categoryService.updateCategory(category);
        return result > 0 ? Result.success() : Result.error("更新失败");
    }

    @GetMapping("/delete")
    public Result<Void> deleteCategory(@RequestParam Integer id) {
        int result = categoryService.deleteCategory(id);
        return result > 0 ? Result.success() : Result.error("删除失败");
    }

    @GetMapping("/info")
    public Result<Category> getCategoryById(@RequestParam Integer id) {
        Category category = categoryService.getCategoryById(id);
        return category != null ? Result.success(category) : Result.error("获取失败");
    }

    @GetMapping("/listByUser")
    public Result<List<Category>> getCategoriesByUserId(@RequestParam Integer userId) {
        List<Category> categories = categoryService.getCategoriesByUserId(userId);
        return Result.success(categories);
    }

    @GetMapping("/list")
    public Result<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return Result.success(categories);
    }
}