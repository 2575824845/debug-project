package com.xiaoming.lifemanager.mapper;

import com.xiaoming.lifemanager.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    // 登录查询
    User login(@Param("username") String username, @Param("password") String password);
    // 修改密码
    int updatePassword(@Param("id") Integer id, @Param("newPassword") String newPassword);
    // 查询用户信息
    User selectById(@Param("id") Integer id);
    // 更新头像
    int updateAvatar(@Param("id") Integer id, @Param("avatar") String avatar);
}