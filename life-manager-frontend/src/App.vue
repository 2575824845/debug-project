<template>
  <div id="app">
    <router-view v-slot="{ Component }">
      <transition name="fade-slide" mode="out-in">
        <component :is="Component" />
      </transition>
    </router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
/* CSS变量定义主题色 */
:root {
  /* 主色调 */
  --primary-color: #409eff;
  --primary-hover: #66b1ff;
  --primary-light: #ecf5ff;
  
  /* 辅助色 */
  --success-color: #67c23a;
  --success-hover: #85ce61;
  --warning-color: #e6a23c;
  --warning-hover: #ebb563;
  --danger-color: #f56c6c;
  --danger-hover: #f78989;
  --info-color: #909399;
  --info-hover: #a6a9ad;
  
  /* 中性色 */
  --text-primary: #303133;
  --text-regular: #606266;
  --text-secondary: #909399;
  --text-placeholder: #c0c4cc;
  
  /* 背景色 */
  --bg-primary: #ffffff;
  --bg-secondary: #f5f7fa;
  --bg-tertiary: #ecf5ff;
  
  /* 边框色 */
  --border-base: #dcdfe6;
  --border-light: #e4e7ed;
  --border-lighter: #ebeef5;
  --border-extra-light: #f2f6fc;
  
  /* 阴影 */
  --shadow-base: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  --shadow-light: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  --shadow-dark: 0 4px 20px rgba(0, 0, 0, 0.15);
  
  /* 过渡效果 */
  --transition-base: all 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
  --transition-fast: all 0.2s ease;
}

/* 全局样式重置 */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html, body, #app {
  width: 100%;
  height: 100%;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
  font-size: 14px;
  line-height: 1.5;
  color: var(--text-regular);
  background-color: var(--bg-secondary);
}

/* 自定义滚动条样式 */
::-webkit-scrollbar {
  width: 8px;
  height: 8px;
}

::-webkit-scrollbar-track {
  background: var(--bg-tertiary);
  border-radius: 4px;
}

::-webkit-scrollbar-thumb {
  background: var(--border-base);
  border-radius: 4px;
  transition: var(--transition-base);
}

::-webkit-scrollbar-thumb:hover {
  background: var(--text-placeholder);
}

/* 路由过渡动画 */
.fade-slide-enter-active {
  transition: all 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
}

.fade-slide-leave-active {
  transition: all 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
}

.fade-slide-enter-from {
  opacity: 0;
  transform: translateY(20px);
}

.fade-slide-leave-to {
  opacity: 0;
  transform: translateY(-20px);
}

/* 链接样式 */
a {
  color: var(--primary-color);
  text-decoration: none;
  transition: var(--transition-base);
}

a:hover {
  color: var(--primary-hover);
}

/* 全局按钮样式 */
.el-button {
  transition: var(--transition-base);
}

/* 全局卡片样式 */
.el-card {
  border-radius: 8px;
  transition: var(--transition-base);
}

.el-card:hover {
  box-shadow: var(--shadow-light);
}

/* 全局表格样式 */
.el-table {
  border-radius: 8px;
  overflow: hidden;
}

/* 全局表单样式 */
.el-form-item__label {
  color: var(--text-regular);
}

/* 全局输入框样式 */
.el-input__wrapper {
  border-radius: 6px;
  transition: var(--transition-base);
}

.el-input__wrapper:hover {
  box-shadow: 0 0 0 1px var(--primary-light), 0 0 0 4px var(--primary-light);
}

.el-input__wrapper.is-focus {
  box-shadow: 0 0 0 1px var(--primary-color), 0 0 0 4px var(--primary-light);
}
</style>