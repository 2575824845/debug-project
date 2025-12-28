<template>
  <div class="login-container">
    <div class="login-background"></div>
    <div class="login-main">
      <el-card class="login-card" shadow="hover">
        <template #header>
          <div class="login-header">
            <div class="logo">
              <el-icon class="logo-icon"><House /></el-icon>
            </div>
            <h2>个人生活管理平台</h2>
            <p class="login-subtitle">让生活更有规律</p>
          </div>
        </template>
        <el-form :model="loginForm" :rules="loginRules" ref="loginFormRef" label-position="top">
          <el-form-item prop="username">
            <el-input 
              v-model="loginForm.username" 
              placeholder="请输入用户名" 
              prefix-icon="User" 
              size="large"
            />
          </el-form-item>
          <el-form-item prop="password">
            <el-input 
              v-model="loginForm.password" 
              type="password" 
              placeholder="请输入密码" 
              prefix-icon="Lock" 
              show-password 
              size="large"
            />
          </el-form-item>
          <div class="login-actions">
            <el-checkbox v-model="rememberMe" size="large">记住我</el-checkbox>
            <el-button type="text" size="large" @click="handleForgotPassword">忘记密码？</el-button>
          </div>
          <el-form-item>
            <el-button 
              type="primary" 
              @click="handleLogin" 
              :loading="loading" 
              class="login-btn"
              size="large"
            >
              <el-icon><Right /></el-icon>登录
            </el-button>
          </el-form-item>
        </el-form>
      </el-card>
      <div class="login-footer">
        <p>© 2025 个人生活管理平台. 保留所有权利.</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, message: '密码长度不能少于6个字符', trigger: 'blur' }
        ]
      },
      loading: false,
      rememberMe: false
    }
  },
  methods: {
    handleLogin() {
      this.$refs.loginFormRef.validate((valid) => {
        if (valid) {
          this.loading = true
          // 模拟登录请求
          setTimeout(() => {
            this.$router.push('/home/schedule')
            this.loading = false
          }, 1000)
        }
      })
    },
    handleForgotPassword() {
      this.$message({
        type: 'info',
        message: '忘记密码功能待实现'
      })
    }
  }
}
</script>

<style scoped>
/* 登录页面容器 */
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  position: relative;
  overflow: hidden;
}

/* 背景装饰效果 */
.login-container::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-image: 
    radial-gradient(circle at 20% 50%, rgba(255, 255, 255, 0.15) 0%, transparent 50%),
    radial-gradient(circle at 80% 20%, rgba(255, 255, 255, 0.1) 0%, transparent 50%),
    radial-gradient(circle at 40% 80%, rgba(255, 255, 255, 0.12) 0%, transparent 50%);
  animation: backgroundAnimation 20s ease infinite;
}

@keyframes backgroundAnimation {
  0%, 100% { transform: scale(1) rotate(0deg); }
  50% { transform: scale(1.1) rotate(180deg); }
}

/* 登录主体内容 */
.login-main {
  position: relative;
  z-index: 1;
  width: 100%;
  max-width: 420px;
  padding: 0 20px;
  animation: fadeInUp 0.6s ease-out;
}

@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

/* 登录卡片 */
.login-card {
  background-color: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  border-radius: 16px;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: all 0.3s ease;
}

.login-card:hover {
  box-shadow: 0 30px 60px rgba(0, 0, 0, 0.15);
  transform: translateY(-5px);
}

/* 登录头部 */
.login-header {
  text-align: center;
  padding: 40px 20px 20px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
}

.logo {
  display: inline-block;
  width: 80px;
  height: 80px;
  background-color: rgba(255, 255, 255, 0.2);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
}

.logo-icon {
  font-size: 40px;
  color: white;
}

.login-header h2 {
  margin: 0 0 10px;
  font-size: 28px;
  font-weight: 600;
}

.login-subtitle {
  margin: 0;
  font-size: 14px;
  opacity: 0.9;
}

/* 登录表单 */
.login-card .el-card__body {
  padding: 30px 25px;
}

/* 登录操作区域 */
.login-actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 25px;
  font-size: 14px;
}

/* 登录按钮 */
.login-btn {
  width: 100%;
  height: 48px;
  font-size: 16px;
  border-radius: 8px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border: none;
  transition: all 0.3s ease;
}

.login-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 16px rgba(102, 126, 234, 0.4);
}

/* 登录页脚 */
.login-footer {
  text-align: center;
  margin-top: 20px;
  color: rgba(255, 255, 255, 0.8);
  font-size: 12px;
}

/* 输入框样式优化 */
.login-card .el-input__wrapper {
  border-radius: 8px;
  height: 48px;
}

.login-card .el-input__inner {
  font-size: 15px;
}

/* 复选框样式优化 */
.login-card .el-checkbox {
  color: var(--text-regular);
}

/* 忘记密码链接 */
.login-card .el-button--text {
  color: var(--primary-color);
  font-size: 14px;
}

.login-card .el-button--text:hover {
  color: var(--primary-hover);
}
</style>