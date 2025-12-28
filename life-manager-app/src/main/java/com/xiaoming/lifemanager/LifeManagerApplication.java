package com.xiaoming.lifemanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.xiaoming.lifemanager.mapper")  // 扫描Mapper接口
public class LifeManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LifeManagerApplication.class, args);
    }
}