package com.xiaoming.lifemanager.service;

import com.xiaoming.lifemanager.entity.User;

public interface UserService {
    // 登录验证
    User login(String username, String password);

    // 修改密码
    boolean updatePassword(Integer id, String newPassword);

    // 获取用户信息
    User getUserInfo(Integer id);

    // 更新头像
    boolean updateAvatar(Integer id, String avatarUrl);

    // 导出数据备份
    boolean exportDataBackup(Integer userId);
}