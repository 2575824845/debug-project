package com.xiaoming.lifemanager.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Note {
    private Integer id;         // 主键ID
    private Integer userId;     // 关联用户ID
    private String title;       // 标题
    private String content;     // 富文本内容
    private Integer tagId;      // 关联分类ID
    private Integer isTop;      // 是否置顶（0=否，1=是）
    private Date createTime;    // 创建时间
    private Date updateTime;    // 更新时间
}