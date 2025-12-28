<template>
  <div class="account-container">
    <div class="page-header">
      <h2>记账管理</h2>
      <el-button type="primary" @click="handleAddAccount">
        <el-icon><Plus /></el-icon>添加记账
      </el-button>
    </div>
    
    <!-- 月度统计图表 -->
    <el-card class="stats-card" shadow="hover">
      <template #header>
        <div class="card-header">
          <h3>月度统计</h3>
          <el-select v-model="selectedMonth" @change="handleMonthChange">
            <el-option label="12月" value="12" />
            <el-option label="11月" value="11" />
            <el-option label="10月" value="10" />
          </el-select>
        </div>
      </template>
      <div class="stats-content">
        <div class="stats-summary">
          <div class="stats-item">
            <div class="stats-label">本月收入</div>
            <div class="stats-value income">¥{{ monthlyStats.income.toFixed(2) }}</div>
          </div>
          <div class="stats-item">
            <div class="stats-label">本月支出</div>
            <div class="stats-value expense">¥{{ monthlyStats.expense.toFixed(2) }}</div>
          </div>
          <div class="stats-item">
            <div class="stats-label">本月结余</div>
            <div class="stats-value balance">¥{{ (monthlyStats.income - monthlyStats.expense).toFixed(2) }}</div>
          </div>
        </div>
        <div id="expense-chart" ref="expenseChartRef" class="chart-container"></div>
      </div>
    </el-card>
    
    <!-- 记账列表 -->
    <el-card class="account-card">
      <el-table :data="filteredAccountList" stripe style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="type" label="类型" width="100">
          <template #default="scope">
            <el-tag :type="scope.row.type === 'income' ? 'success' : 'danger'">
              {{ scope.row.type === 'income' ? '收入' : '支出' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="category" label="分类" width="120" />
        <el-table-column prop="amount" label="金额" width="120">
          <template #default="scope">
            <span :class="scope.row.type === 'income' ? 'income-amount' : 'expense-amount'">
              {{ scope.row.type === 'income' ? '+' : '-' }}{{ scope.row.amount.toFixed(2) }}
            </span>
          </template>
        </el-table-column>
        <el-table-column prop="date" label="日期" width="180" />
        <el-table-column prop="description" label="描述" min-width="200" show-overflow-tooltip />
        <el-table-column label="操作" width="150" fixed="right">
          <template #default="scope">
            <el-button type="primary" size="small" @click="handleEditAccount(scope.row)">
              <el-icon><Edit /></el-icon>编辑
            </el-button>
            <el-button type="danger" size="small" @click="handleDeleteAccount(scope.row.id)">
              <el-icon><Delete /></el-icon>删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: 'AccountBook',
  data() {
    return {
      accountList: [
        {
          id: 1,
          type: 'expense',
          category: '餐饮',
          amount: 50.50,
          date: '2025-12-23',
          description: '午餐'
        },
        {
          id: 2,
          type: 'income',
          category: '工资',
          amount: 5000.00,
          date: '2025-12-23',
          description: '12月工资'
        },
        {
          id: 3,
          type: 'expense',
          category: '交通',
          amount: 20.00,
          date: '2025-12-22',
          description: '地铁'
        },
        {
          id: 4,
          type: 'expense',
          category: '购物',
          amount: 120.00,
          date: '2025-12-21',
          description: '生活用品'
        },
        {
          id: 5,
          type: 'expense',
          category: '餐饮',
          amount: 80.00,
          date: '2025-12-20',
          description: '晚餐'
        },
        {
          id: 6,
          type: 'expense',
          category: '交通',
          amount: 50.00,
          date: '2025-12-15',
          description: '打车'
        },
        {
          id: 7,
          type: 'income',
          category: '奖金',
          amount: 1000.00,
          date: '2025-12-10',
          description: '季度奖金'
        },
        {
          id: 8,
          type: 'expense',
          category: '娱乐',
          amount: 200.00,
          date: '2025-12-05',
          description: '电影票'
        },
        {
          id: 9,
          type: 'expense',
          category: '房租',
          amount: 2000.00,
          date: '2025-12-01',
          description: '12月房租'
        }
      ],
      selectedMonth: '12',
      monthlyStats: {
        income: 0,
        expense: 0
      },
      chartInstance: null
    }
  },
  computed: {
    filteredAccountList() {
      // 根据选择的月份过滤记账记录
      return this.accountList.filter(item => {
        const month = new Date(item.date).getMonth() + 1
        return month === parseInt(this.selectedMonth)
      })
    }
  },
  mounted() {
    // 计算月度统计数据
    this.calculateMonthlyStats()
    // 初始化图表
    this.initChart()
  },
  beforeUnmount() {
    // 销毁图表实例
    if (this.chartInstance) {
      this.chartInstance.dispose()
      this.chartInstance = null
    }
  },
  methods: {
    handleAddAccount() {
      // 添加记账逻辑
      this.$message('添加记账功能待实现')
    },
    handleEditAccount(row) {
      // 编辑记账逻辑
      this.$message(`编辑记账: ${row.category}`)
    },
    handleDeleteAccount(id) {
      // 删除记账逻辑
      this.$confirm('确定要删除这条记账记录吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.accountList = this.accountList.filter(item => item.id !== id)
        // 重新计算统计数据和更新图表
        this.calculateMonthlyStats()
        this.updateChart()
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
    handleMonthChange() {
      // 切换月份时重新计算统计数据和更新图表
      this.calculateMonthlyStats()
      this.updateChart()
    },
    calculateMonthlyStats() {
      // 计算月度收入和支出
      const filtered = this.filteredAccountList
      this.monthlyStats.income = filtered
        .filter(item => item.type === 'income')
        .reduce((sum, item) => sum + item.amount, 0)
      this.monthlyStats.expense = filtered
        .filter(item => item.type === 'expense')
        .reduce((sum, item) => sum + item.amount, 0)
    },
    initChart() {
      // 初始化支出分类饼图
      const chartDom = this.$refs.expenseChartRef
      this.chartInstance = echarts.init(chartDom)
      
      // 更新图表数据
      this.updateChart()
      
      // 监听窗口大小变化，调整图表大小
      window.addEventListener('resize', () => {
        this.chartInstance.resize()
      })
    },
    updateChart() {
      if (!this.chartInstance) return
      
      // 计算各支出分类的金额
      const expenseData = this.filteredAccountList
        .filter(item => item.type === 'expense')
        .reduce((acc, item) => {
          if (acc[item.category]) {
            acc[item.category] += item.amount
          } else {
            acc[item.category] = item.amount
          }
          return acc
        }, {})
      
      // 转换为ECharts需要的数据格式
      const seriesData = Object.entries(expenseData).map(([name, value]) => ({
        name,
        value
      }))
      
      // 设置图表配置
      const option = {
        title: {
          text: '支出分类占比',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{b}: {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          right: 10,
          top: 'center',
          data: Object.keys(expenseData)
        },
        series: [
          {
            name: '支出分类',
            type: 'pie',
            radius: ['40%', '70%'],
            center: ['40%', '50%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 18,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: seriesData
          }
        ]
      }
      
      // 设置图表配置
      this.chartInstance.setOption(option)
    }
  }
}
</script>

<style scoped>
/* 记账管理容器 */
.account-container {
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

/* 添加记账按钮 */
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

/* 统计卡片 */
.stats-card {
  margin-bottom: 24px;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
  border: 1px solid var(--border-light);
  overflow: hidden;
  transition: all 0.3s ease;
}

.stats-card:hover {
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

/* 月份选择器 */
.card-header .el-select {
  width: 120px;
}

.card-header .el-select .el-input__wrapper {
  border-radius: 6px;
}

/* 统计内容 */
.stats-content {
  display: flex;
  align-items: center;
  padding: 24px;
  gap: 32px;
}

/* 统计摘要 */
.stats-summary {
  width: 30%;
  display: flex;
  flex-direction: column;
  gap: 24px;
}

/* 统计项 */
.stats-item {
  text-align: center;
  padding: 20px;
  background-color: var(--bg-secondary);
  border-radius: 12px;
  transition: all 0.3s ease;
  border: 1px solid var(--border-light);
}

.stats-item:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
}

/* 统计标签 */
.stats-label {
  font-size: 14px;
  color: var(--text-secondary);
  margin-bottom: 8px;
  font-weight: 500;
}

/* 统计数值 */
.stats-value {
  font-size: 28px;
  font-weight: 700;
  transition: all 0.3s ease;
}

/* 收入颜色 */
.stats-value.income {
  color: var(--success-color);
}

/* 支出颜色 */
.stats-value.expense {
  color: var(--danger-color);
}

/* 结余颜色 */
.stats-value.balance {
  color: var(--primary-color);
}

/* 图表容器 */
.chart-container {
  width: 70%;
  height: 300px;
  border-radius: 12px;
  overflow: hidden;
  background-color: var(--bg-secondary);
  padding: 16px;
  border: 1px solid var(--border-light);
}

/* 记账卡片 */
.account-card {
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
  border: 1px solid var(--border-light);
  overflow: hidden;
  margin-top: 0;
  height: auto;
}

/* 记账表格 */
.account-card .el-table {
  border-radius: 12px;
  overflow: hidden;
}

.account-card .el-table__header-wrapper th {
  background-color: var(--bg-tertiary);
  font-weight: 600;
  color: var(--text-primary);
  height: 48px;
  line-height: 48px;
}

.account-card .el-table__body-wrapper tr {
  height: 56px;
  transition: background-color 0.2s ease;
}

.account-card .el-table__body-wrapper tr:hover td {
  background-color: var(--bg-tertiary);
}

/* 表格列样式 */
.account-card .el-table__body-wrapper td {
  padding: 12px;
  border-bottom: 1px solid var(--border-light);
}

/* 类型标签 */
.account-card .el-tag {
  font-weight: 500;
  border-radius: 6px;
  padding: 2px 8px;
}

/* 金额样式 */
.income-amount {
  color: var(--success-color);
  font-weight: 600;
}

.expense-amount {
  color: var(--danger-color);
  font-weight: 600;
}

/* 操作按钮 */
.account-card .el-button--small {
  border-radius: 6px;
  padding: 4px 12px;
  margin-right: 8px;
  font-weight: 500;
  transition: all 0.2s ease;
}

.account-card .el-button--primary {
  background-color: var(--primary-color);
  border-color: var(--primary-color);
}

.account-card .el-button--primary:hover {
  background-color: var(--primary-hover);
  border-color: var(--primary-hover);
  transform: translateY(-1px);
}

.account-card .el-button--danger {
  background-color: var(--danger-color);
  border-color: var(--danger-color);
}

.account-card .el-button--danger:hover {
  background-color: var(--danger-hover);
  border-color: var(--danger-hover);
  transform: translateY(-1px);
}

/* 表格滚动条 */
.account-card .el-table__body-wrapper::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

.account-card .el-table__body-wrapper::-webkit-scrollbar-track {
  background: var(--bg-secondary);
}

.account-card .el-table__body-wrapper::-webkit-scrollbar-thumb {
  background: var(--border-base);
  border-radius: 3px;
}

.account-card .el-table__body-wrapper::-webkit-scrollbar-thumb:hover {
  background: var(--text-placeholder);
}

/* 响应式设计 */
@media screen and (max-width: 768px) {
  .stats-content {
    flex-direction: column;
    gap: 20px;
  }
  
  .stats-summary {
    width: 100%;
    flex-direction: row;
  }
  
  .stats-item {
    flex: 1;
    padding: 16px;
  }
  
  .stats-value {
    font-size: 24px;
  }
  
  .chart-container {
    width: 100%;
    height: 250px;
  }
}
</style>