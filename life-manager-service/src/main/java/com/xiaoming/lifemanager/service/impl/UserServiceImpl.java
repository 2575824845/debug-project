package com.xiaoming.lifemanager.service.impl;

import com.xiaoming.lifemanager.entity.User;
import com.xiaoming.lifemanager.mapper.UserMapper;
import com.xiaoming.lifemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }

    @Override
    public boolean updatePassword(Integer id, String newPassword) {
        return userMapper.updatePassword(id, newPassword) > 0;
    }

    @Override
    public User getUserInfo(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public boolean updateAvatar(Integer id, String avatarUrl) {
        return userMapper.updateAvatar(id, avatarUrl) > 0;
    }

    @Override
    public boolean exportDataBackup(Integer userId) {
        // 这里可以实现导出数据备份的逻辑，比如生成JSON或Excel文件
        // 目前暂时返回true表示成功
        return true;
    }
}