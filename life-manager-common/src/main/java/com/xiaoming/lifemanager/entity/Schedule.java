package com.xiaoming.lifemanager.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Schedule {
    private Integer id;         // 主键ID
    private Integer userId;     // 关联用户ID
    private String title;       // 日程标题
    private String content;     // 日程内容
    private Date startTime;     // 开始时间
    private Date endTime;       // 结束时间
    private String tag;         // 标签（工作/生活/学习）
    private Integer remind;     // 是否提醒（0=否，1=是）
}