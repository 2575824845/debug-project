package com.xiaoming.lifemanager.mapper;

import com.xiaoming.lifemanager.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CategoryMapper {
    int insert(Category category);
    int update(Category category);
    int delete(Integer id);
    Category selectById(Integer id);
    List<Category> selectByUserId(Integer userId);
    List<Category> selectAll();
}