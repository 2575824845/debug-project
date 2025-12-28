package com.xiaoming.lifemanager.service;

import com.xiaoming.lifemanager.entity.Schedule;
import java.util.List;

public interface ScheduleService {
    // 新增日程
    boolean addSchedule(Schedule schedule);

    // 查询用户所有日程
    List<Schedule> getUserSchedules(Integer userId);

    // 编辑日程
    boolean updateSchedule(Schedule schedule);

    // 删除日程
    boolean deleteSchedule(Integer id);
}