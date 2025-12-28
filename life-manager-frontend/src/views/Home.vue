<template>
  <el-container class="home-container">
    <el-aside width="200px" class="sidebar">
      <el-menu
        :default-active="activeMenu"
        class="el-menu-vertical-demo"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        router
      >
        <el-menu-item index="/home/schedule">
          <el-icon><Calendar /></el-icon>
          <span>日程管理</span>
        </el-menu-item>
        <el-menu-item index="/home/account">
          <el-icon><Coin /></el-icon>
          <span>记账管理</span>
        </el-menu-item>
        <el-menu-item index="/home/note">
          <el-icon><Document /></el-icon>
          <span>备忘录</span>
        </el-menu-item>
        <el-menu-item index="/home/file">
          <el-icon><FolderOpened /></el-icon>
          <span>文件存储</span>
        </el-menu-item>
        <el-menu-item index="/home/profile">
          <el-icon><User /></el-icon>
          <span>个人中心</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="header">
        <div class="header-content">
          <h1>个人生活管理平台</h1>
          <div class="user-info">
            <el-dropdown>
              <span class="user-dropdown">
                <el-icon><User /></el-icon>
                <span>用户名</span>
                <el-icon class="el-icon--right"><ArrowDown /></el-icon>
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click="handleLogout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </div>
      </el-header>
      <el-main class="main-content">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: 'Home',
  data() {
    return {
      activeMenu: '/home/schedule'
    }
  },
  mounted() {
    this.activeMenu = this.$route.path
  },
  watch: {
    '$route.path'(newPath) {
      this.activeMenu = newPath
    }
  },
  methods: {
    handleLogout() {
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
/* 主页面容器 */
.home-container {
  height: 100vh;
  background-color: var(--bg-secondary);
  display: flex;
  flex-direction: row;
  overflow: hidden;
}

/* 侧边栏样式 */
.sidebar {
  width: 240px;
  background: linear-gradient(180deg, #4e54c8 0%, #8f94fb 100%);
  box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
  overflow-y: auto;
  transition: all 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
  z-index: 10;
}

/* 侧边栏菜单 */
.sidebar .el-menu {
  background-color: transparent;
  border-right: none;
  height: 100%;
  padding-top: 20px;
}

.sidebar .el-menu-item {
  height: 50px;
  line-height: 50px;
  margin: 8px 12px;
  border-radius: 8px;
  color: rgba(255, 255, 255, 0.9);
  font-size: 15px;
  transition: all 0.3s ease;
  border: 1px solid transparent;
}

.sidebar .el-menu-item:hover {
  background-color: rgba(255, 255, 255, 0.1);
  border-color: rgba(255, 255, 255, 0.2);
}

.sidebar .el-menu-item.is-active {
  background-color: rgba(255, 255, 255, 0.2);
  border-color: rgba(255, 255, 255, 0.4);
  color: #ffffff;
  font-weight: 500;
}

.sidebar .el-menu-item .el-icon {
  margin-right: 12px;
  font-size: 18px;
}

/* 主内容区域 */
.home-container .el-container {
  display: flex;
  flex-direction: column;
  flex: 1;
  overflow: hidden;
}

/* 头部样式 */
.header {
  height: 70px;
  background-color: #ffffff;
  border-bottom: 1px solid var(--border-light);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
  padding: 0;
  display: flex;
  align-items: center;
  z-index: 9;
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 100%;
  padding: 0 30px;
}

.header-content h1 {
  margin: 0;
  font-size: 24px;
  font-weight: 600;
  color: var(--text-primary);
  background: linear-gradient(135deg, #4e54c8 0%, #8f94fb 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

/* 用户信息区域 */
.user-info {
  display: flex;
  align-items: center;
}

.user-dropdown {
  display: flex;
  align-items: center;
  cursor: pointer;
  padding: 8px 16px;
  border-radius: 20px;
  transition: all 0.3s ease;
  background-color: var(--bg-secondary);
  color: var(--text-primary);
  border: 2px solid transparent;
}

.user-dropdown:hover {
  background-color: var(--bg-tertiary);
  border-color: var(--primary-light);
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.user-dropdown .el-icon {
  margin-right: 8px;
  font-size: 16px;
  color: var(--primary-color);
}

/* 下拉箭头 */
.user-dropdown .el-icon--right {
  margin-left: 4px;
  font-size: 14px;
}

/* 主内容区域 */
.main-content {
  flex: 1;
  padding: 24px;
  overflow-y: auto;
  background-color: var(--bg-secondary);
}

/* 内容卡片样式 */
.main-content .el-card {
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
  border: 1px solid var(--border-light);
  margin-bottom: 24px;
  transition: all 0.3s ease;
}

.main-content .el-card:hover {
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
  transform: translateY(-2px);
}

/* 按钮样式优化 */
.main-content .el-button {
  border-radius: 8px;
  font-weight: 500;
  padding: 8px 16px;
  transition: all 0.3s ease;
}

.main-content .el-button:hover {
  transform: translateY(-1px);
}

/* 表格样式优化 */
.main-content .el-table {
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
}

.main-content .el-table__header-wrapper th {
  background-color: var(--bg-tertiary);
  font-weight: 600;
  color: var(--text-primary);
}

/* 表单样式优化 */
.main-content .el-form-item__label {
  font-weight: 500;
  color: var(--text-regular);
}

.main-content .el-input__wrapper {
  border-radius: 8px;
}

/* 响应式设计 */
@media screen and (max-width: 768px) {
  .sidebar {
    width: 0;
    overflow: hidden;
  }
  
  .sidebar.show {
    width: 240px;
  }
  
  .header-content {
    padding: 0 16px;
  }
  
  .header-content h1 {
    font-size: 20px;
  }
  
  .main-content {
    padding: 16px;
  }
}
</style>