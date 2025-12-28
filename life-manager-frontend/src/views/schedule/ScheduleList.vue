<template>
  <div class="schedule-container">
    <div class="page-header">
      <h2>日程管理</h2>
      <el-button type="primary" @click="handleAddSchedule">
        <el-icon><Plus /></el-icon>添加日程
      </el-button>
    </div>
    
    <el-card class="schedule-card">
      <el-table :data="scheduleList" stripe style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="title" label="标题" min-width="200" />
        <el-table-column prop="startTime" label="开始时间" width="180" />
        <el-table-column prop="endTime" label="结束时间" width="180" />
        <el-table-column prop="status" label="状态" width="100">
          <template #default="scope">
            <el-tag :type="scope.row.status === 'completed' ? 'success' : 'warning'">
              {{ scope.row.status === 'completed' ? '已完成' : '进行中' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="reminder" label="提醒" width="100">
          <template #default="scope">
            <el-switch
              v-model="scope.row.reminder"
              @change="handleReminderChange(scope.row)"
              active-text="开启"
              inactive-text="关闭"
            />
          </template>
        </el-table-column>
        <el-table-column prop="reminderTime" label="提醒时间" width="180">
          <template #default="scope">
            <el-date-picker
              v-if="scope.row.reminder"
              v-model="scope.row.reminderTime"
              type="datetime"
              placeholder="选择提醒时间"
              format="YYYY-MM-DD HH:mm"
              value-format="YYYY-MM-DD HH:mm"
              :disabled-date="disabledDate"
              @change="handleReminderTimeChange(scope.row)"
            />
            <span v-else class="no-reminder">无</span>
          </template>
        </el-table-column>
        <el-table-column prop="description" label="描述" min-width="200" show-overflow-tooltip />
        <el-table-column label="操作" width="150" fixed="right">
          <template #default="scope">
            <el-button type="primary" size="small" @click="handleEditSchedule(scope.row)">
              <el-icon><Edit /></el-icon>编辑
            </el-button>
            <el-button type="danger" size="small" @click="handleDeleteSchedule(scope.row.id)">
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
  name: 'ScheduleList',
  data() {
    return {
      scheduleList: [
        {
          id: 1,
          title: '项目会议',
          startTime: '2025-12-23 09:00',
          endTime: '2025-12-23 10:30',
          status: 'in_progress',
          description: '讨论项目进展和下一步计划',
          reminder: true,
          reminderTime: '2025-12-23 08:45'
        },
        {
          id: 2,
          title: '健身',
          startTime: '2025-12-23 18:00',
          endTime: '2025-12-23 19:30',
          status: 'in_progress',
          description: '有氧运动和力量训练',
          reminder: false,
          reminderTime: ''
        },
        {
          id: 3,
          title: '学习Vue 3',
          startTime: '2025-12-23 20:00',
          endTime: '2025-12-23 22:00',
          status: 'in_progress',
          description: '学习组合式API和TypeScript',
          reminder: true,
          reminderTime: '2025-12-23 19:30'
        }
      ]
    }
  },
  mounted() {
    // 启动提醒检查定时器，每分钟检查一次
    this.reminderTimer = setInterval(() => {
      this.checkReminders()
    }, 60000)
    
    // 初始检查一次
    this.checkReminders()
  },
  beforeUnmount() {
    // 清除定时器
    if (this.reminderTimer) {
      clearInterval(this.reminderTimer)
      this.reminderTimer = null
    }
  },
  methods: {
    handleAddSchedule() {
      // 添加日程逻辑
      this.$message('添加日程功能待实现')
    },
    handleEditSchedule(row) {
      // 编辑日程逻辑
      this.$message(`编辑日程: ${row.title}`)
    },
    handleDeleteSchedule(id) {
      // 删除日程逻辑
      this.$confirm('确定要删除这条日程吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.scheduleList = this.scheduleList.filter(item => item.id !== id)
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
    handleReminderChange(row) {
      // 处理提醒开关切换
      if (row.reminder) {
        // 开启提醒时，如果没有设置提醒时间，默认设置为开始时间前15分钟
        if (!row.reminderTime) {
          const startTime = new Date(row.startTime)
          const reminderTime = new Date(startTime.getTime() - 15 * 60 * 1000)
          row.reminderTime = reminderTime.toISOString().slice(0, 16)
        }
        this.$message({
          type: 'success',
          message: '提醒已开启'
        })
      } else {
        // 关闭提醒
        this.$message({
          type: 'info',
          message: '提醒已关闭'
        })
      }
    },
    handleReminderTimeChange(row) {
      // 处理提醒时间修改
      // 验证提醒时间是否在开始时间之前
      const reminderTime = new Date(row.reminderTime)
      const startTime = new Date(row.startTime)
      if (reminderTime >= startTime) {
        this.$message({
          type: 'error',
          message: '提醒时间必须在开始时间之前'
        })
        // 恢复到之前的提醒时间或默认时间
        const defaultReminderTime = new Date(startTime.getTime() - 15 * 60 * 1000)
        row.reminderTime = defaultReminderTime.toISOString().slice(0, 16)
      } else {
        this.$message({
          type: 'success',
          message: '提醒时间已更新'
        })
      }
    },
    disabledDate(time) {
      // 禁用过去的日期
      return time.getTime() < Date.now() - 8.64e7
    },
    checkReminders() {
      // 检查是否有需要提醒的日程
      const now = new Date()
      const currentTime = now.toISOString().slice(0, 16) // 格式：YYYY-MM-DD HH:mm
      
      this.scheduleList.forEach(schedule => {
        if (schedule.reminder && schedule.reminderTime === currentTime) {
          // 显示提醒通知
          this.showReminderNotification(schedule)
        }
      })
    },
    showReminderNotification(schedule) {
      // 显示提醒通知
      // 在实际项目中，可以使用浏览器的Notification API或Element Plus的Message组件
      this.$notify({
        title: '日程提醒',
        message: `您有一个日程即将开始：${schedule.title}\n时间：${schedule.startTime}`,
        type: 'warning',
        duration: 0, // 不会自动关闭
        showClose: true
      })
    }
  }
}
</script>

<style scoped>
/* 日程管理容器 */
.schedule-container {
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

/* 添加日程按钮 */
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

/* 日程卡片 */
.schedule-card {
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
  border: 1px solid var(--border-light);
  overflow: hidden;
}

/* 日程表格 */
.schedule-card .el-table {
  border-radius: 12px;
  overflow: hidden;
}

.schedule-card .el-table__header-wrapper th {
  background-color: var(--bg-tertiary);
  font-weight: 600;
  color: var(--text-primary);
  height: 48px;
  line-height: 48px;
}

.schedule-card .el-table__body-wrapper tr {
  height: 56px;
  transition: background-color 0.2s ease;
}

.schedule-card .el-table__body-wrapper tr:hover td {
  background-color: var(--bg-tertiary);
}

/* 表格列样式 */
.schedule-card .el-table__body-wrapper td {
  padding: 12px;
  border-bottom: 1px solid var(--border-light);
}

/* 标题列 */
.schedule-card .el-table-column--title {
  font-weight: 500;
  color: var(--text-primary);
}

/* 状态标签 */
.schedule-card .el-tag {
  font-weight: 500;
  border-radius: 6px;
  padding: 2px 8px;
}

/* 提醒开关 */
.schedule-card .el-switch {
  --el-switch-on-color: var(--primary-color);
  --el-switch-off-color: var(--text-placeholder);
}

/* 提醒时间选择器 */
.schedule-card .el-date-editor {
  width: 100%;
}

.schedule-card .el-date-editor .el-input__wrapper {
  border-radius: 6px;
}

/* 无提醒文本 */
.no-reminder {
  color: var(--text-placeholder);
  font-style: italic;
  font-size: 14px;
}

/* 操作按钮 */
.schedule-card .el-button--small {
  border-radius: 6px;
  padding: 4px 12px;
  margin-right: 8px;
  font-weight: 500;
  transition: all 0.2s ease;
}

.schedule-card .el-button--primary {
  background-color: var(--primary-color);
  border-color: var(--primary-color);
}

.schedule-card .el-button--primary:hover {
  background-color: var(--primary-hover);
  border-color: var(--primary-hover);
  transform: translateY(-1px);
}

.schedule-card .el-button--danger {
  background-color: var(--danger-color);
  border-color: var(--danger-color);
}

.schedule-card .el-button--danger:hover {
  background-color: var(--danger-hover);
  border-color: var(--danger-hover);
  transform: translateY(-1px);
}

/* 表格滚动条 */
.schedule-card .el-table__body-wrapper::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

.schedule-card .el-table__body-wrapper::-webkit-scrollbar-track {
  background: var(--bg-secondary);
}

.schedule-card .el-table__body-wrapper::-webkit-scrollbar-thumb {
  background: var(--border-base);
  border-radius: 3px;
}

.schedule-card .el-table__body-wrapper::-webkit-scrollbar-thumb:hover {
  background: var(--text-placeholder);
}
</style>