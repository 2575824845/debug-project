package com.xiaoming.lifemanager.service;

import com.xiaoming.lifemanager.entity.Category;
import java.util.List;

public interface CategoryService {
    int addCategory(Category category);
    int updateCategory(Category category);
    int deleteCategory(Integer id);
    Category getCategoryById(Integer id);
    List<Category> getCategoriesByUserId(Integer userId);
    List<Category> getAllCategories();
}