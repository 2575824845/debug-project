package com.xiaoming.lifemanager.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;         // 主键ID
    private String username;    // 登录账号
    private String password;    // 登录密码
    private String nickname;    // 昵称
    private String avatar;      // 头像地址
}