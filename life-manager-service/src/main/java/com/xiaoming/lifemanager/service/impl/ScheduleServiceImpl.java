package com.xiaoming.lifemanager.service.impl;

import com.xiaoming.lifemanager.entity.Schedule;
import com.xiaoming.lifemanager.mapper.ScheduleMapper;
import com.xiaoming.lifemanager.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public boolean addSchedule(Schedule schedule) {
        return scheduleMapper.addSchedule(schedule) > 0;
    }

    @Override
    public List<Schedule> getUserSchedules(Integer userId) {
        return scheduleMapper.selectAllByUserId(userId);
    }

    @Override
    public boolean updateSchedule(Schedule schedule) {
        return scheduleMapper.updateSchedule(schedule) > 0;
    }

    @Override
    public boolean deleteSchedule(Integer id) {
        return scheduleMapper.deleteSchedule(id) > 0;
    }
}