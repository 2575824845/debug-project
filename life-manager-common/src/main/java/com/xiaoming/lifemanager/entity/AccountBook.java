package com.xiaoming.lifemanager.entity;

import lombok.Data;
import java.util.Date;

@Data
public class AccountBook {
    private Integer id;         // 主键ID
    private Integer userId;     // 关联用户ID
    private Double amount;      // 金额
    private Integer type;       // 类型（0=支出，1=收入）
    private Integer categoryId; // 关联分类ID
    private Date date;          // 交易日期
    private String note;        // 备注
}