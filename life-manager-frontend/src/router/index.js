import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'

// 导入各功能模块页面
import ScheduleList from '../views/schedule/ScheduleList.vue'
import AccountBook from '../views/account/AccountBook.vue'
import NoteList from '../views/note/NoteList.vue'
import NoteEdit from '../views/note/NoteEdit.vue'
import FileList from '../views/file/FileList.vue'
import Profile from '../views/profile/Profile.vue'

const routes = [
    { path: '/', redirect: '/login' },  // 默认跳登录页
    { path: '/login', component: Login },  // 登录页
    {
        path: '/home',
        component: Home,
        children: [  // 首页里包含5个功能模块
            { path: '', redirect: 'schedule' },  // 默认显示日程管理
            { path: 'schedule', component: ScheduleList, name: '日程管理' },
            { path: 'account', component: AccountBook, name: '记账管理' },
            { path: 'note', component: NoteList, name: '备忘录' },
            { path: 'note/edit', component: NoteEdit, name: '备忘录编辑' },
            { path: 'file', component: FileList, name: '文件存储' },
            { path: 'profile', component: Profile, name: '个人中心' }
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router