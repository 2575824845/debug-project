package com.xiaoming.lifemanager.mapper;

import com.xiaoming.lifemanager.entity.FileStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface FileStoreMapper {
    // 新增文件记录
    int addFile(FileStore fileStore);
    // 查询用户所有文件（支持按分类筛选）
    List<FileStore> selectByUserId(@Param("userId") Integer userId, @Param("category") String category);
    // 按文件名搜索
    List<FileStore> searchByFileName(@Param("userId") Integer userId, @Param("keyword") String keyword);
    // 删除文件记录
    int deleteFile(@Param("id") Integer id);
    // 根据ID查询文件（用于下载/预览）
    FileStore selectById(@Param("id") Integer id);
}