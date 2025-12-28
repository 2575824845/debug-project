package com.xiaoming.lifemanager.controller;

import com.xiaoming.lifemanager.common.Result;
import com.xiaoming.lifemanager.entity.User;
import com.xiaoming.lifemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin  // 解决跨域问题
public class UserController {
    @Autowired
    private UserService userService;

    // 登录接口
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user) {
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        return loginUser != null ? Result.success(loginUser) : Result.error("用户名或密码错误");
    }

    // 修改密码接口
    @PostMapping("/updatePassword")
    public Result<Void> updatePassword(@RequestParam Integer id, @RequestParam String newPassword) {
        boolean success = userService.updatePassword(id, newPassword);
        return success ? Result.success() : Result.error("修改密码失败");
    }

    // 获取用户信息接口
    @GetMapping("/info")
    public Result<User> getUserInfo(@RequestParam Integer id) {
        User user = userService.getUserInfo(id);
        return user != null ? Result.success(user) : Result.error("获取用户信息失败");
    }

    // 更新头像接口
    @PostMapping("/updateAvatar")
    public Result<Void> updateAvatar(@RequestParam Integer id, @RequestParam String avatarUrl) {
        boolean success = userService.updateAvatar(id, avatarUrl);
        return success ? Result.success() : Result.error("更新头像失败");
    }

    // 导出数据备份接口
    @GetMapping("/exportBackup")
    public Result<Void> exportDataBackup(@RequestParam Integer userId) {
        boolean success = userService.exportDataBackup(userId);
        return success ? Result.success() : Result.error("导出备份失败");
    }
}