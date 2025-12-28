package com.xiaoming.lifemanager.controller;

import com.xiaoming.lifemanager.common.Result;
import com.xiaoming.lifemanager.entity.Schedule;
import com.xiaoming.lifemanager.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/schedule")
@CrossOrigin
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    // 新增日程
    @PostMapping("/add")
    public Result<Void> addSchedule(@RequestBody Schedule schedule) {
        boolean success = scheduleService.addSchedule(schedule);
        return success ? Result.success() : Result.error("新增失败");
    }

    // 查询用户日程
    @GetMapping("/list")
    public Result<List<Schedule>> getUserSchedules(@RequestParam Integer userId) {
        List<Schedule> list = scheduleService.getUserSchedules(userId);
        return Result.success(list);
    }

    // 编辑日程
    @PostMapping("/update")
    public Result<Void> updateSchedule(@RequestBody Schedule schedule) {
        boolean success = scheduleService.updateSchedule(schedule);
        return success ? Result.success() : Result.error("更新失败");
    }

    // 删除日程
    @GetMapping("/delete")
    public Result<Void> deleteSchedule(@RequestParam Integer id) {
        boolean success = scheduleService.deleteSchedule(id);
        return success ? Result.success() : Result.error("删除失败");
    }
}