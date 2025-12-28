<template>
  <div class="profile-container">
    <div class="page-header">
      <h2>个人中心</h2>
    </div>
    
    <div class="profile-content">
      <!-- 用户信息卡片 -->
      <el-card class="profile-card" shadow="hover">
        <template #header>
          <div class="card-header">
            <h3>用户信息</h3>
            <div class="header-buttons">
              <el-button
                type="primary"
                size="small"
                @click="isEditMode = !isEditMode"
              >
                <el-icon>{{ isEditMode ? 'Check' : 'Edit' }}</el-icon>
                {{ isEditMode ? '保存' : '编辑' }}
              </el-button>
              <el-button
                type="success"
                size="small"
                @click="handleExportData"
              >
                <el-icon><Download /></el-icon>导出数据
              </el-button>
            </div>
          </div>
        </template>
        
        <el-form :model="userInfo" :rules="userRules" ref="userInfoRef" label-width="120px">
          <div class="user-avatar">
            <el-avatar :size="100" :src="userInfo.avatar">
              {{ userInfo.username.charAt(0).toUpperCase() }}
            </el-avatar>
            <el-button type="text" @click="handleChangeAvatar" :disabled="!isEditMode">
              <el-icon><Upload /></el-icon>更换头像
            </el-button>
          </div>
          
          <el-form-item label="用户名" prop="username">
            <el-input v-model="userInfo.username" :disabled="!isEditMode" />
          </el-form-item>
          
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="userInfo.email" type="email" :disabled="!isEditMode" />
          </el-form-item>
          
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="userInfo.phone" :disabled="!isEditMode" />
          </el-form-item>
          
          <el-form-item label="性别" prop="gender">
            <el-radio-group v-model="userInfo.gender" :disabled="!isEditMode">
              <el-radio label="male">男</el-radio>
              <el-radio label="female">女</el-radio>
              <el-radio label="other">其他</el-radio>
            </el-radio-group>
          </el-form-item>
          
          <el-form-item label="生日" prop="birthday">
            <el-date-picker
              v-model="userInfo.birthday"
              type="date"
              placeholder="选择生日"
              :disabled="!isEditMode"
              style="width: 100%"
            />
          </el-form-item>
          
          <el-form-item label="个人简介" prop="bio">
            <el-input
              v-model="userInfo.bio"
              type="textarea"
              :rows="4"
              placeholder="请输入个人简介"
              :disabled="!isEditMode"
            />
          </el-form-item>
        </el-form>
      </el-card>
      
      <!-- 修改密码卡片 -->
      <el-card class="profile-card" shadow="hover" style="margin-top: 20px;">
        <template #header>
          <div class="card-header">
            <h3>修改密码</h3>
          </div>
        </template>
        
        <el-form :model="passwordForm" :rules="passwordRules" ref="passwordFormRef" label-width="120px">
          <el-form-item label="当前密码" prop="oldPassword">
            <el-input v-model="passwordForm.oldPassword" type="password" placeholder="请输入当前密码" />
          </el-form-item>
          
          <el-form-item label="新密码" prop="newPassword">
            <el-input v-model="passwordForm.newPassword" type="password" placeholder="请输入新密码" />
          </el-form-item>
          
          <el-form-item label="确认密码" prop="confirmPassword">
            <el-input v-model="passwordForm.confirmPassword" type="password" placeholder="请确认新密码" />
          </el-form-item>
          
          <el-form-item>
            <el-button type="primary" @click="handleChangePassword">
              <el-icon><Check /></el-icon>确认修改
            </el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Profile',
  data() {
    return {
      isEditMode: false,
      userInfo: {
        username: 'admin',
        email: 'admin@example.com',
        phone: '13800138000',
        gender: 'male',
        birthday: '1990-01-01',
        bio: '这是一个个人简介...',
        avatar: ''
      },
      userRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 20, message: '用户名长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号', trigger: 'blur' }
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        birthday: [
          { required: true, message: '请选择生日', trigger: 'change' }
        ]
      },
      passwordForm: {
        oldPassword: '',
        newPassword: '',
        confirmPassword: ''
      },
      passwordRules: {
        oldPassword: [
          { required: true, message: '请输入当前密码', trigger: 'blur' }
        ],
        newPassword: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
          { min: 6, max: 20, message: '密码长度在 6 到 20 个字符', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '请确认新密码', trigger: 'blur' },
          { validator: (rule, value, callback) => this.validateConfirmPassword(rule, value, callback), trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    validateConfirmPassword(rule, value, callback) {
      if (value !== this.passwordForm.newPassword) {
        callback(new Error('两次输入的密码不一致'))
      } else {
        callback()
      }
    },
    handleChangeAvatar() {
      // 模拟更换头像
      this.$message({
        type: 'info',
        message: '更换头像功能待实现'
      })
    },
    handleChangePassword() {
      this.$refs.passwordFormRef.validate((valid) => {
        if (valid) {
          // 模拟修改密码
          this.$message({
            type: 'success',
            message: '密码修改成功'
          })
          this.passwordForm = {
            oldPassword: '',
            newPassword: '',
            confirmPassword: ''
          }
          this.$refs.passwordFormRef.resetFields()
        }
      })
    },
    handleExportData() {
      // 导出数据备份功能
      // 模拟用户数据，实际项目中应该从后端获取
      const userData = {
        userInfo: this.userInfo,
        exportTime: new Date().toISOString(),
        dataVersion: '1.0.0'
      }
      
      // 转换为JSON字符串
      const jsonString = JSON.stringify(userData, null, 2)
      
      // 创建Blob对象
      const blob = new Blob([jsonString], { type: 'application/json' })
      
      // 创建下载链接
      const url = URL.createObjectURL(blob)
      const link = document.createElement('a')
      link.href = url
      link.download = `life-manager-backup-${new Date().toISOString().slice(0, 10)}.json`
      
      // 触发下载
      document.body.appendChild(link)
      link.click()
      
      // 清理
      document.body.removeChild(link)
      URL.revokeObjectURL(url)
      
      this.$message({
        type: 'success',
        message: '数据导出成功'
      })
    }
  },
  watch: {
    isEditMode(newVal) {
      if (newVal) {
        // 进入编辑模式，保存原始数据
        this.originalUserInfo = JSON.parse(JSON.stringify(this.userInfo))
      } else {
        // 退出编辑模式，验证并保存
        this.$refs.userInfoRef.validate((valid) => {
          if (valid) {
            this.$message({
              type: 'success',
              message: '用户信息保存成功'
            })
          } else {
            // 验证失败，恢复原始数据
            this.userInfo = JSON.parse(JSON.stringify(this.originalUserInfo))
            this.$message({
              type: 'error',
              message: '用户信息验证失败，请检查输入'
            })
          }
        })
      }
    }
  }
}
</script>

<style scoped>
/* 个人中心容器 */
.profile-container {
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

/* 内容区域 */
.profile-content {
  max-width: 800px;
  margin: 0 auto;
}

/* 个人信息卡片 */
.profile-card {
  margin-bottom: 24px;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
  border: 1px solid var(--border-light);
  overflow: hidden;
  transition: all 0.3s ease;
}

.profile-card:hover {
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
}

/* 卡片头部 */
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 20px;
  border-bottom: 1px solid var(--border-light);
}

.card-header h3 {
  margin: 0;
  font-size: 18px;
  font-weight: 600;
  color: var(--text-primary);
}

/* 头部按钮 */
.header-buttons {
  display: flex;
  gap: 12px;
}

.header-buttons .el-button {
  height: 36px;
  padding: 0 16px;
  font-size: 14px;
  border-radius: 6px;
  font-weight: 500;
  transition: all 0.3s ease;
}

/* 编辑/保存按钮 */
.header-buttons .el-button:nth-child(1) {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border: none;
}

.header-buttons .el-button:nth-child(1):hover {
  transform: translateY(-1px);
  box-shadow: 0 4px 12px rgba(102, 126, 234, 0.3);
}

/* 导出数据按钮 */
.header-buttons .el-button:nth-child(2) {
  background-color: var(--success-color);
  border-color: var(--success-color);
}

.header-buttons .el-button:nth-child(2):hover {
  background-color: var(--success-hover);
  border-color: var(--success-hover);
  transform: translateY(-1px);
}

/* 表单样式 */
.profile-card .el-form {
  padding: 24px;
}

/* 表单项 */
.profile-card .el-form-item {
  margin-bottom: 24px;
}

/* 表单标签 */
.profile-card .el-form-item__label {
  font-weight: 600;
  color: var(--text-primary);
  font-size: 15px;
}

/* 用户头像 */
.user-avatar {
  display: flex;
  align-items: center;
  margin-bottom: 32px;
  padding: 20px;
  background-color: var(--bg-secondary);
  border-radius: 12px;
  border: 1px solid var(--border-light);
  transition: all 0.3s ease;
}

.user-avatar:hover {
  background-color: var(--bg-tertiary);
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
}

/* 头像 */
.user-avatar .el-avatar {
  margin-right: 24px;
  border: 3px solid var(--border-light);
  transition: all 0.3s ease;
}

.user-avatar:hover .el-avatar {
  border-color: var(--primary-color);
  transform: scale(1.05);
}

/* 更换头像按钮 */
.user-avatar .el-button {
  color: var(--primary-color);
  font-weight: 500;
  font-size: 14px;
  transition: all 0.3s ease;
}

.user-avatar .el-button:hover {
  color: var(--primary-hover);
  transform: translateY(-1px);
}

/* 输入框 */
.profile-card .el-input__wrapper {
  border-radius: 8px;
  transition: all 0.3s ease;
}

.profile-card .el-input__wrapper:hover {
  box-shadow: 0 0 0 1px var(--primary-light), 0 0 0 4px var(--primary-light);
}

.profile-card .el-input__wrapper.is-focus {
  box-shadow: 0 0 0 1px var(--primary-color), 0 0 0 4px var(--primary-light);
}

/* 文本域 */
.profile-card .el-textarea__inner {
  border-radius: 8px;
  transition: all 0.3s ease;
}

.profile-card .el-textarea__inner:hover {
  border-color: var(--primary-color);
}

.profile-card .el-textarea__inner:focus {
  border-color: var(--primary-color);
  box-shadow: 0 0 0 1px var(--primary-color), 0 0 0 4px var(--primary-light);
}

/* 单选框 */
.profile-card .el-radio {
  margin-right: 24px;
  color: var(--text-primary);
  font-weight: 500;
}

/* 日期选择器 */
.profile-card .el-date-editor .el-input__wrapper {
  border-radius: 8px;
}

/* 修改密码按钮 */
.profile-card .el-button--primary {
  height: 40px;
  padding: 0 24px;
  font-size: 15px;
  border-radius: 8px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border: none;
  box-shadow: 0 4px 12px rgba(102, 126, 234, 0.3);
  font-weight: 500;
  transition: all 0.3s ease;
}

.profile-card .el-button--primary:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 16px rgba(102, 126, 234, 0.4);
}

/* 响应式设计 */
@media screen and (max-width: 768px) {
  .profile-content {
    padding: 0 16px;
  }
  
  .profile-card .el-form {
    padding: 16px;
  }
  
  .user-avatar {
    flex-direction: column;
    text-align: center;
  }
  
  .user-avatar .el-avatar {
    margin-right: 0;
    margin-bottom: 16px;
  }
  
  .card-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 12px;
  }
  
  .header-buttons {
    align-self: stretch;
    justify-content: space-between;
  }
}
</style>