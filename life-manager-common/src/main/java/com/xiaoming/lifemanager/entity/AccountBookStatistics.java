package com.xiaoming.lifemanager.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountBookStatistics {
    private Integer userId;              // 关联用户ID
    private String month;               // 统计月份（格式：yyyy-MM）
    private BigDecimal totalIncome;     // 月收入总和
    private BigDecimal totalExpense;    // 月支出总和
    private BigDecimal balance;         // 结余（收入-支出）
    private List<CategoryStatistics> categoryDetails; // 分类统计详情

    // 分类统计详情类
    @Data
    public static class CategoryStatistics {
        private Integer categoryId;     // 分类ID
        private String categoryName;    // 分类名称
        private Integer type;           // 类型（0=支出，1=收入）
        private BigDecimal amount;      // 金额
    }
}