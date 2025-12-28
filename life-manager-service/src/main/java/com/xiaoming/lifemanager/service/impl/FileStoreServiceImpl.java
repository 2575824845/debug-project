package com.xiaoming.lifemanager.service.impl;

import com.xiaoming.lifemanager.entity.FileStore;
import com.xiaoming.lifemanager.mapper.FileStoreMapper;
import com.xiaoming.lifemanager.service.FileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FileStoreServiceImpl implements FileStoreService {
    @Autowired
    private FileStoreMapper fileStoreMapper;

    @Override
    public boolean addFile(FileStore fileStore) {
        return fileStoreMapper.addFile(fileStore) > 0;
    }

    @Override
    public List<FileStore> getUserFiles(Integer userId, String category) {
        return fileStoreMapper.selectByUserId(userId, category);
    }

    @Override
    public List<FileStore> searchFiles(Integer userId, String keyword) {
        return fileStoreMapper.searchByFileName(userId, keyword);
    }

    @Override
    public String deleteFile(Integer id) {
        FileStore file = fileStoreMapper.selectById(id);
        if (file != null && fileStoreMapper.deleteFile(id) > 0) {
            return file.getFilePath(); // 返回本地路径，供Controller删除文件
        }
        return null;
    }

    @Override
    public FileStore getFileById(Integer id) {
        return fileStoreMapper.selectById(id);
    }
}