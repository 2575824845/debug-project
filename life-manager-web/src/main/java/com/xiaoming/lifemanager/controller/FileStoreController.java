package com.xiaoming.lifemanager.controller;

import com.xiaoming.lifemanager.common.Result;
import com.xiaoming.lifemanager.entity.FileStore;
import com.xiaoming.lifemanager.service.FileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileStoreController {
    @Autowired
    private FileStoreService fileStoreService;

    // 从配置文件获取上传路径
    @Value("${file.upload-dir}")
    private String uploadDir;

    // 文件上传（前端上传图片/PDF用）
    @PostMapping("/upload")
    public Result<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam Integer userId,
            @RequestParam String category
    ) {
        try {
            // 校验文件大小（不超过5MB）
            if (file.getSize() > 5 * 1024 * 1024) {
                return Result.error("文件大小不能超过5MB");
            }

            // 生成唯一文件名（避免重复）
            String originalName = file.getOriginalFilename();
            String suffix = originalName.substring(originalName.lastIndexOf("."));
            String fileName = System.currentTimeMillis() + suffix;

            // 保存文件到本地
            File saveFile = new File(uploadDir + fileName);
            file.transferTo(saveFile);

            // 新增文件记录到数据库
            FileStore fileStore = new FileStore();
            fileStore.setUserId(userId);
            fileStore.setFileName(originalName);
            fileStore.setFilePath(uploadDir + fileName);
            fileStore.setFileSize(file.getSize());
            fileStore.setFileType(file.getContentType());
            fileStore.setCategory(category);
            fileStoreService.addFile(fileStore);

            // 返回文件ID（前端预览/下载用）
            return Result.success(fileStore.getId().toString());
        } catch (Exception e) {
            return Result.error("上传失败：" + e.getMessage());
        }
    }

    // 获取用户文件（支持按分类筛选）
    @GetMapping("/list")
    public Result<List<FileStore>> getUserFiles(
            @RequestParam Integer userId,
            @RequestParam(required = false) String category
    ) {
        List<FileStore> list = fileStoreService.getUserFiles(userId, category);
        return Result.success(list);
    }

    // 搜索文件
    @GetMapping("/search")
    public Result<List<FileStore>> searchFiles(
            @RequestParam Integer userId,
            @RequestParam String keyword
    ) {
        List<FileStore> list = fileStoreService.searchFiles(userId, keyword);
        return Result.success(list);
    }

    // 文件下载
    @GetMapping("/download")
    public void downloadFile(@RequestParam Integer id, HttpServletResponse response) {
        try {
            FileStore file = fileStoreService.getFileById(id);
            if (file == null) {
                response.getWriter().write("文件不存在");
                return;
            }

            // 设置响应头（触发下载）
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(file.getFileName(), "UTF-8"));

            // 读取本地文件并写入响应
            FileInputStream fis = new FileInputStream(file.getFilePath());
            OutputStream os = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }

            // 关闭流
            fis.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 删除文件（同时删除本地文件）
    @GetMapping("/delete")
    public Result<Void> deleteFile(@RequestParam Integer id) {
        try {
            // 删除数据库记录，并获取本地文件路径
            String filePath = fileStoreService.deleteFile(id);
            if (filePath == null) {
                return Result.error("删除失败");
            }

            // 删除本地文件
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }

            return Result.success();
        } catch (Exception e) {
            return Result.error("删除失败：" + e.getMessage());
        }
    }
}