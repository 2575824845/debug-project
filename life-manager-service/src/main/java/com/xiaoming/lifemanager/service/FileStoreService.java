package com.xiaoming.lifemanager.service;

import com.xiaoming.lifemanager.entity.FileStore;

import java.util.List;

public interface FileStoreService {
    // 新增文件记录
    boolean addFile(FileStore fileStore);

    // 获取用户文件（支持按分类筛选）
    List<FileStore> getUserFiles(Integer userId, String category);

    // 搜索文件
    List<FileStore> searchFiles(Integer userId, String keyword);

    // 删除文件（返回文件路径，用于删除本地文件）
    String deleteFile(Integer id);

    // 根据ID获取文件（用于下载/预览）
    FileStore getFileById(Integer id);
}