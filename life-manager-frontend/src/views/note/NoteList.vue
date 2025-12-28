<template>
  <div class="note-container">
    <div class="page-header">
      <h2>备忘录</h2>
      <el-button type="primary" @click="handleAddNote">
        <el-icon><Plus /></el-icon>新建备忘录
      </el-button>
    </div>
    
    <div class="note-list">
      <el-card
        v-for="note in noteList"
        :key="note.id"
        class="note-card"
        shadow="hover"
        @click="handleEditNote(note.id)"
      >
        <template #header>
          <div class="note-header">
            <div class="title-container">
              <el-icon
                v-if="note.isPinned"
                class="pinned-icon"
                color="#e6a23c"
              >
                <StarFilled />
              </el-icon>
              <h3>{{ note.title }}</h3>
            </div>
            <div class="header-buttons">
              <el-button
                type="text"
                @click.stop="handlePinNote(note.id)"
                class="pin-btn"
              >
                <el-icon>
                  {{ note.isPinned ? 'StarFilled' : 'Star' }}
                </el-icon>
                {{ note.isPinned ? '取消置顶' : '置顶' }}
              </el-button>
              <el-button
                type="text"
                @click.stop="handleDeleteNote(note.id)"
                class="delete-btn"
              >
                <el-icon><Delete /></el-icon>删除
              </el-button>
            </div>
          </div>
        </template>
        <div class="note-content">
          <div class="note-preview" v-html="note.contentPreview"></div>
        </div>
        <div class="note-footer">
          <span class="note-date">{{ note.updatedAt }}</span>
          <el-tag :type="note.importance" size="small">
            {{ note.importance === 'primary' ? '重要' : note.importance === 'warning' ? '一般' : '不重要' }}
          </el-tag>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'NoteList',
  data() {
    return {
      noteList: [
        {
          id: 1,
          title: '项目会议准备',
          content: '<p>1. 准备项目进度报告</p><p>2. 准备会议PPT</p><p>3. 确认参会人员</p>',
          contentPreview: '<p>1. 准备项目进度报告</p><p>2. 准备会议PPT</p><p>3. 确认参会人员</p>',
          importance: 'primary',
          isPinned: true,
          createdAt: '2025-12-22 14:30:00',
          updatedAt: '2025-12-22 15:45:00'
        },
        {
          id: 2,
          title: '购物清单',
          content: '<p>1. 牛奶</p><p>2. 鸡蛋</p><p>3. 蔬菜</p><p>4. 水果</p>',
          contentPreview: '<p>1. 牛奶</p><p>2. 鸡蛋</p><p>3. 蔬菜</p><p>4. 水果</p>',
          importance: 'success',
          isPinned: false,
          createdAt: '2025-12-23 09:15:00',
          updatedAt: '2025-12-23 09:15:00'
        },
        {
          id: 3,
          title: '学习计划',
          content: '<p>1. Vue 3 组合式API</p><p>2. TypeScript 基础</p><p>3. Element Plus 组件库</p>',
          contentPreview: '<p>1. Vue 3 组合式API</p><p>2. TypeScript 基础</p><p>3. Element Plus 组件库</p>',
          importance: 'warning',
          isPinned: false,
          createdAt: '2025-12-21 20:00:00',
          updatedAt: '2025-12-21 21:30:00'
        }
      ]
    }
  },
  mounted() {
    // 对笔记列表进行排序，置顶的笔记显示在前面
    this.sortNotesByPinned()
  },
  methods: {
    handleAddNote() {
      // 添加备忘录逻辑
      this.$router.push('/home/note/edit')
    },
    handleEditNote(id) {
      // 编辑备忘录逻辑
      this.$router.push(`/home/note/edit?id=${id}`)
    },
    handleDeleteNote(id) {
      // 删除备忘录逻辑
      this.$confirm('确定要删除这条备忘录吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.noteList = this.noteList.filter(item => item.id !== id)
        this.$message({
          type: 'success',
          message: '删除成功'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    handlePinNote(id) {
      // 处理置顶/取消置顶逻辑
      const note = this.noteList.find(item => item.id === id)
      if (note) {
        note.isPinned = !note.isPinned
        this.sortNotesByPinned()
        this.$message({
          type: 'success',
          message: note.isPinned ? '置顶成功' : '取消置顶成功'
        })
      }
    },
    sortNotesByPinned() {
      // 按置顶状态排序，置顶的笔记显示在前面
      this.noteList.sort((a, b) => {
        if (a.isPinned && !b.isPinned) return -1
        if (!a.isPinned && b.isPinned) return 1
        // 对于同样状态的笔记，按更新时间倒序排列
        return new Date(b.updatedAt) - new Date(a.updatedAt)
      })
    }
  }
}
</script>

<style scoped>
/* 备忘录容器 */
.note-container {
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

/* 新建备忘录按钮 */
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

/* 备忘录列表 */
.note-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 24px;
}

/* 备忘录卡片 */
.note-card {
  height: 280px;
  cursor: pointer;
  transition: all 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.06);
  border: 1px solid var(--border-light);
  overflow: hidden;
  background-color: #ffffff;
}

.note-card:hover {
  transform: translateY(-8px) scale(1.02);
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12);
}

/* 备忘录头部 */
.note-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  padding: 12px 0;
  border-bottom: 1px solid var(--border-light);
}

/* 标题容器 */
.title-container {
  display: flex;
  align-items: center;
  max-width: 70%;
}

/* 标题 */
.title-container h3 {
  margin: 0;
  font-size: 18px;
  font-weight: 600;
  color: var(--text-primary);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  transition: all 0.3s ease;
}

/* 置顶图标 */
.pinned-icon {
  margin-right: 8px;
  font-size: 18px;
  color: var(--warning-color);
  animation: pulse 2s infinite;
}

@keyframes pulse {
  0% { transform: scale(1); }
  50% { transform: scale(1.1); }
  100% { transform: scale(1); }
}

/* 头部按钮 */
.header-buttons {
  display: flex;
  align-items: center;
  gap: 12px;
}

/* 置顶按钮 */
.pin-btn {
  color: var(--warning-color);
  font-weight: 500;
  transition: all 0.2s ease;
}

.pin-btn:hover {
  color: var(--warning-hover);
  transform: translateY(-1px);
}

/* 删除按钮 */
.delete-btn {
  color: var(--danger-color);
  font-weight: 500;
  transition: all 0.2s ease;
}

.delete-btn:hover {
  color: var(--danger-hover);
  transform: translateY(-1px);
}

/* 备忘录内容 */
.note-content {
  height: 140px;
  overflow: hidden;
  margin: 16px 0;
  padding: 0 8px;
}

/* 内容预览 */
.note-preview {
  font-size: 14px;
  color: var(--text-secondary);
  line-height: 1.6;
  display: -webkit-box;
  -webkit-line-clamp: 5;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

/* 内容预览中的段落 */
.note-preview p {
  margin: 0 0 8px 0;
}

/* 备忘录底部 */
.note-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 16px;
  padding-top: 16px;
  border-top: 1px solid var(--border-light);
}

/* 日期 */
.note-date {
  font-size: 13px;
  color: var(--text-placeholder);
  font-weight: 500;
}

/* 重要性标签 */
.note-footer .el-tag {
  border-radius: 6px;
  font-weight: 500;
  padding: 2px 8px;
}

/* 响应式设计 */
@media screen and (max-width: 768px) {
  .note-list {
    grid-template-columns: 1fr;
    gap: 20px;
  }
  
  .note-card {
    height: 260px;
  }
  
  .title-container h3 {
    font-size: 16px;
  }
}
</style>