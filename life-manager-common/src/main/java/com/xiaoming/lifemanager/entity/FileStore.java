package com.xiaoming.lifemanager.entity;

import lombok.Data;
import java.util.Date;

@Data
public class FileStore {
    private Integer id;         // 主键ID
    private Integer userId;     // 关联用户ID
    private String fileName;    // 文件名
    private String filePath;    // 存储路径
    private Long fileSize;      // 文件大小（字节）
    private String fileType;    // 文件类型（image/pdf/doc）
    private String category;    // 文件分类（课件/照片/文档）
    private Date uploadTime;    // 上传时间
}