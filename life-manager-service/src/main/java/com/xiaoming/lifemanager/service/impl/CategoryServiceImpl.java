package com.xiaoming.lifemanager.service.impl;

import com.xiaoming.lifemanager.entity.Category;
import com.xiaoming.lifemanager.mapper.CategoryMapper;
import com.xiaoming.lifemanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int addCategory(Category category) {
        return categoryMapper.insert(category);
    }

    @Override
    public int updateCategory(Category category) {
        return categoryMapper.update(category);
    }

    @Override
    public int deleteCategory(Integer id) {
        return categoryMapper.delete(id);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public List<Category> getCategoriesByUserId(Integer userId) {
        return categoryMapper.selectByUserId(userId);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.selectAll();
    }
}