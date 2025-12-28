<template>
  <div class="note-edit-container">
    <div class="page-header">
      <h2>{{ isEditMode ? '编辑备忘录' : '新建备忘录' }}</h2>
      <div class="header-buttons">
        <el-button @click="handleBack">
          <el-icon><ArrowLeft /></el-icon>返回
        </el-button>
        <el-button type="primary" @click="handleSave">
          <el-icon><Check /></el-icon>保存
        </el-button>
      </div>
    </div>
    
    <el-card class="note-edit-card">
      <el-form :model="noteForm" :rules="noteRules" ref="noteFormRef" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="noteForm.title" placeholder="请输入备忘录标题" />
        </el-form-item>
        
        <el-form-item label="重要性" prop="importance">
          <el-select v-model="noteForm.importance" placeholder="请选择重要性">
            <el-option label="重要" value="primary" />
            <el-option label="一般" value="warning" />
            <el-option label="不重要" value="success" />
          </el-select>
        </el-form-item>
        
        <el-form-item label="内容" prop="content">
          <div class="editor-container">
            <Toolbar
              style="border-bottom: 1px solid #ccc"
              :editor="editor"
              :default-config="toolbarConfig"
            />
            <Editor
              style="height: 500px; overflow-y: auto"
              v-model="noteForm.content"
              :default-config="editorConfig"
              @onCreated="onCreated"
            />
          </div>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import '@wangeditor/editor/dist/css/style.css' // 引入编辑器样式
import { Editor as EditorCore } from '@wangeditor/editor'

export default {
  name: 'NoteEdit',
  components: {
    Editor,
    Toolbar
  },
  data() {
    return {
      noteForm: {
        id: null,
        title: '',
        content: '',
        importance: 'warning'
      },
      noteRules: {
        title: [
          { required: true, message: '请输入备忘录标题', trigger: 'blur' },
          { min: 2, max: 50, message: '标题长度在 2 到 50 个字符', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '请输入备忘录内容', trigger: 'blur' }
        ]
      },
      editor: null,
      toolbarConfig: {},
      editorConfig: {
        placeholder: '请输入备忘录内容...',
        MENU_CONF: {}
      },
      isEditMode: false
    }
  },
  mounted() {
    // 获取URL参数中的id
    const id = this.$route.query.id
    if (id) {
      this.isEditMode = true
      this.loadNoteData(id)
    }
  },
  beforeUnmount() {
    const editor = this.editor
    if (editor == null) return
    editor.destroy() // 组件销毁时，及时销毁编辑器
  },
  methods: {
    onCreated(editor) {
      this.editor = editor // 记录 editor 实例，重要！
    },
    loadNoteData(id) {
      // 模拟加载备忘录数据
      setTimeout(() => {
        this.noteForm = {
          id: parseInt(id),
          title: '项目会议准备',
          content: '<p>1. 准备项目进度报告</p><p>2. 准备会议PPT</p><p>3. 确认参会人员</p>',
          importance: 'primary'
        }
      }, 500)
    },
    handleSave() {
      this.$refs.noteFormRef.validate((valid) => {
        if (valid) {
          // 保存备忘录逻辑
          this.$message({
            type: 'success',
            message: this.isEditMode ? '更新成功' : '创建成功'
          })
          // 跳转到备忘录列表页
          this.$router.push('/home/note')
        }
      })
    },
    handleBack() {
      // 返回备忘录列表页
      this.$router.push('/home/note')
    }
  }
}
</script>

<style scoped>
/* 备忘录编辑容器 */
.note-edit-container {
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

/* 头部按钮容器 */
.header-buttons {
  display: flex;
  gap: 12px;
}

/* 头部按钮 */
.header-buttons .el-button {
  height: 40px;
  padding: 0 20px;
  font-size: 15px;
  border-radius: 8px;
  font-weight: 500;
  transition: all 0.3s ease;
}

/* 返回按钮 */
.header-buttons .el-button:nth-child(1) {
  border-color: var(--border-base);
  color: var(--text-primary);
}

.header-buttons .el-button:nth-child(1):hover {
  border-color: var(--primary-color);
  color: var(--primary-color);
  transform: translateY(-1px);
}

/* 保存按钮 */
.header-buttons .el-button:nth-child(2) {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border: none;
  box-shadow: 0 4px 12px rgba(102, 126, 234, 0.3);
}

.header-buttons .el-button:nth-child(2):hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 16px rgba(102, 126, 234, 0.4);
}

/* 编辑卡片 */
.note-edit-card {
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
  border: 1px solid var(--border-light);
  overflow: hidden;
  height: auto;
  min-height: 600px;
}

/* 表单样式 */
.note-edit-card .el-form {
  padding: 16px 0;
}

/* 表单项 */
.note-edit-card .el-form-item {
  margin-bottom: 24px;
}

/* 表单标签 */
.note-edit-card .el-form-item__label {
  font-weight: 600;
  color: var(--text-primary);
  font-size: 15px;
}

/* 输入框 */
.note-edit-card .el-input__wrapper {
  border-radius: 8px;
  transition: all 0.3s ease;
}

.note-edit-card .el-input__wrapper:hover {
  box-shadow: 0 0 0 1px var(--primary-light), 0 0 0 4px var(--primary-light);
}

.note-edit-card .el-input__wrapper.is-focus {
  box-shadow: 0 0 0 1px var(--primary-color), 0 0 0 4px var(--primary-light);
}

/* 选择器 */
.note-edit-card .el-select .el-input__wrapper {
  border-radius: 8px;
}

.note-edit-card .el-select .el-input__wrapper:hover {
  box-shadow: 0 0 0 1px var(--primary-light), 0 0 0 4px var(--primary-light);
}

.note-edit-card .el-select .el-input__wrapper.is-focus {
  box-shadow: 0 0 0 1px var(--primary-color), 0 0 0 4px var(--primary-light);
}

/* 编辑器容器 */
.editor-container {
  border: 1px solid var(--border-light);
  border-radius: 8px;
  overflow: hidden;
  transition: all 0.3s ease;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.05);
}

.editor-container:hover {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

/* 编辑器工具栏 */
.editor-container .w-e-toolbar {
  border-bottom: 1px solid var(--border-light);
  background-color: var(--bg-secondary);
}

/* 编辑器内容区 */
.editor-container .w-e-text-container {
  background-color: #ffffff;
}

/* 响应式设计 */
@media screen and (max-width: 768px) {
  .page-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 16px;
  }
  
  .header-buttons {
    align-self: stretch;
    justify-content: space-between;
  }
  
  .header-buttons .el-button {
    flex: 1;
  }
}
</style>