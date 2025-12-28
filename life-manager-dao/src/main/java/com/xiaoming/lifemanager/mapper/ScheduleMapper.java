package com.xiaoming.lifemanager.mapper;

import com.xiaoming.lifemanager.entity.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ScheduleMapper {
    // 新增日程
    int addSchedule(Schedule schedule);
    // 查询用户所有日程
    List<Schedule> selectAllByUserId(@Param("userId") Integer userId);
    // 编辑日程
    int updateSchedule(Schedule schedule);
    // 删除日程
    int deleteSchedule(@Param("id") Integer id);
}