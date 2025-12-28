package com.xiaoming.lifemanager.entity;

import lombok.Data;

@Data
public class Category {
    private Integer id;         // 主键ID
    private Integer userId;     // 关联用户ID
    private String name;        // 分类名称
    private Integer type;       // 类型（0=记账分类，1=备忘录分类）
}