<template>
  <div class="file-container">
    <div class="page-header">
      <h2>文件存储</h2>
      <el-button type="primary" @click="dialogVisible = true">
        <el-icon><Upload /></el-icon>上传文件
      </el-button>
    </div>
    
    <!-- 上传文件对话框 -->
    <el-dialog
      v-model="dialogVisible"
      title="上传文件"
      width="50%"
      @close="handleDialogClose"
    >
      <el-upload
        ref="uploadRef"
        class="upload-demo"
        :auto-upload="false"
        :on-change="handleFileChange"
        :file-list="fileList"
        accept="*"
        multiple
      >
        <el-button type="primary">
          <el-icon><Plus /></el-icon>选择文件
        </el-button>
        <template #tip>
          <div class="el-upload__tip">
            支持上传任意类型文件，单个文件不超过100MB
          </div>
        </template>
      </el-upload>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleUpload">上传</el-button>
        </span>
      </template>
    </el-dialog>
    
    <el-card class="file-card">
      <el-table :data="fileDataList" stripe style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="文件名" min-width="200">
          <template #default="scope">
            <div class="file-name">
              <el-icon :size="20"><Document /></el-icon>
              <span>{{ scope.row.name }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="size" label="大小" width="120">
          <template #default="scope">
            {{ formatFileSize(scope.row.size) }}
          </template>
        </el-table-column>
        <el-table-column prop="type" label="类型" width="100" />
        <el-table-column prop="uploadTime" label="上传时间" width="180" />
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="scope">
            <el-button type="primary" size="small" @click="handleDownload(scope.row)">
              <el-icon><Download /></el-icon>下载
            </el-button>
            <el-button type="success" size="small" @click="handlePreview(scope.row)">
              <el-icon><View /></el-icon>预览
            </el-button>
            <el-button type="danger" size="small" @click="handleDeleteFile(scope.row.id)">
              <el-icon><Delete /></el-icon>删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'FileList',
  data() {
    return {
      dialogVisible: false,
      fileList: [],
      fileDataList: [
        {
          id: 1,
          name: '项目计划.pdf',
          size: 2048000,
          type: 'pdf',
          uploadTime: '2025-12-23 09:00:00'
        },
        {
          id: 2,
          name: '会议记录.docx',
          size: 1024000,
          type: 'docx',
          uploadTime: '2025-12-23 10:30:00'
        },
        {
          id: 3,
          name: '工作报告.xlsx',
          size: 512000,
          type: 'xlsx',
          uploadTime: '2025-12-22 15:45:00'
        },
        {
          id: 4,
          name: '项目图片.jpg',
          size: 3072000,
          type: 'jpg',
          uploadTime: '2025-12-21 14:20:00'
        }
      ]
    }
  },
  methods: {
    handleFileChange(file, fileList) {
      this.fileList = fileList
    },
    handleUpload() {
      // 模拟文件上传
      this.$message({
        type: 'success',
        message: '文件上传成功'
      })
      this.dialogVisible = false
      this.fileList = []
    },
    handleDialogClose() {
      this.fileList = []
      this.$refs.uploadRef.clearFiles()
    },
    formatFileSize(bytes) {
      if (bytes === 0) return '0 B'
      const k = 1024
      const sizes = ['B', 'KB', 'MB', 'GB', 'TB']
      const i = Math.floor(Math.log(bytes) / Math.log(k))
      return parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + ' ' + sizes[i]
    },
    handleDownload(row) {
      // 模拟文件下载
      this.$message({
        type: 'info',
        message: `正在下载文件：${row.name}`
      })
    },
    handlePreview(row) {
      // 模拟文件预览
      this.$message({
        type: 'info',
        message: `正在预览文件：${row.name}`
      })
    },
    handleDeleteFile(id) {
      // 删除文件逻辑
      this.$confirm('确定要删除这个文件吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.fileDataList = this.fileDataList.filter(item => item.id !== id)
        this.$message({
          type: 'success',
          message: '文件删除成功'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>

<style scoped>
/* 文件存储容器 */
.file-container {
  width: 100%;
  min-height: 100%;
}

/* 页面头部 */
.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
  padding: 16px 0;
  border-bottom: 1px solid var(--border-light);
}

.page-header h2 {
  margin: 0;
  font-size: 24px;
  font-weight: 600;
  color: var(--text-primary);
  background: linear-gradient(135deg, #4e54c8 0%, #8f94fb 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

/* 上传按钮 */
.page-header .el-button {
  height: 40px;
  padding: 0 20px;
  font-size: 15px;
  border-radius: 8px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border: none;
  box-shadow: 0 4px 12px rgba(102, 126, 234, 0.3);
  transition: all 0.3s ease;
}

.page-header .el-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 16px rgba(102, 126, 234, 0.4);
}

/* 上传对话框 */
.file-container .el-dialog {
  border-radius: 12px;
  overflow: hidden;
}

.file-container .el-dialog__header {
  background-color: var(--bg-secondary);
  border-bottom: 1px solid var(--border-light);
  padding: 20px 24px;
}

.file-container .el-dialog__title {
  font-size: 18px;
  font-weight: 600;
  color: var(--text-primary);
}

.file-container .el-dialog__body {
  padding: 24px;
}

.file-container .el-dialog__footer {
  padding: 16px 24px;
  border-top: 1px solid var(--border-light);
  background-color: var(--bg-secondary);
}

/* 上传区域 */
.upload-demo {
  margin-bottom: 24px;
  padding: 24px;
  background-color: var(--bg-secondary);
  border: 2px dashed var(--border-base);
  border-radius: 12px;
  transition: all 0.3s ease;
}

.upload-demo:hover {
  border-color: var(--primary-color);
  background-color: var(--bg-tertiary);
}

/* 上传提示 */
.file-container .el-upload__tip {
  color: var(--text-secondary);
  font-size: 14px;
  margin-top: 12px;
}

/* 文件卡片 */
.file-card {
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
  border: 1px solid var(--border-light);
  overflow: hidden;
  height: auto;
}

/* 文件表格 */
.file-card .el-table {
  border-radius: 12px;
  overflow: hidden;
}

.file-card .el-table__header-wrapper th {
  background-color: var(--bg-tertiary);
  font-weight: 600;
  color: var(--text-primary);
  height: 48px;
  line-height: 48px;
}

.file-card .el-table__body-wrapper tr {
  height: 56px;
  transition: background-color 0.2s ease;
}

.file-card .el-table__body-wrapper tr:hover td {
  background-color: var(--bg-tertiary);
}

/* 表格列样式 */
.file-card .el-table__body-wrapper td {
  padding: 12px;
  border-bottom: 1px solid var(--border-light);
}

/* 文件名 */
.file-name {
  display: flex;
  align-items: center;
  gap: 10px;
}

/* 文件名图标 */
.file-name .el-icon {
  color: var(--success-color);
  font-size: 20px;
  transition: all 0.3s ease;
}

/* 操作按钮 */
.file-card .el-button--small {
  border-radius: 6px;
  padding: 4px 12px;
  margin-right: 8px;
  font-weight: 500;
  transition: all 0.2s ease;
}

/* 下载按钮 */
.file-card .el-button--primary {
  background-color: var(--primary-color);
  border-color: var(--primary-color);
}

.file-card .el-button--primary:hover {
  background-color: var(--primary-hover);
  border-color: var(--primary-hover);
  transform: translateY(-1px);
}

/* 预览按钮 */
.file-card .el-button--success {
  background-color: var(--success-color);
  border-color: var(--success-color);
}

.file-card .el-button--success:hover {
  background-color: var(--success-hover);
  border-color: var(--success-hover);
  transform: translateY(-1px);
}

/* 删除按钮 */
.file-card .el-button--danger {
  background-color: var(--danger-color);
  border-color: var(--danger-color);
}

.file-card .el-button--danger:hover {
  background-color: var(--danger-hover);
  border-color: var(--danger-hover);
  transform: translateY(-1px);
}

/* 表格滚动条 */
.file-card .el-table__body-wrapper::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

.file-card .el-table__body-wrapper::-webkit-scrollbar-track {
  background: var(--bg-secondary);
}

.file-card .el-table__body-wrapper::-webkit-scrollbar-thumb {
  background: var(--border-base);
  border-radius: 3px;
}

.file-card .el-table__body-wrapper::-webkit-scrollbar-thumb:hover {
  background: var(--text-placeholder);
}

/* 响应式设计 */
@media screen and (max-width: 768px) {
  .file-container .el-dialog {
    width: 90% !important;
    margin: 20px auto;
  }
  
  .file-card .el-table__body-wrapper td {
    padding: 8px;
  }
  
  .file-card .el-button--small {
    padding: 2px 8px;
    margin-right: 4px;
  }
}
</style>