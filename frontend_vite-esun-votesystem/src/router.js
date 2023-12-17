import { createRouter, createWebHistory } from 'vue-router'
import UserVote from './views/UserVote.vue'
import Admin from './views/Admin.vue'
import Home from './views/Home.vue'
const routes = [
    {
        path: '/',
        component: Home
    },
    {
        path: '/user',
        component: UserVote
    },
    {
        path: '/admin',
        component: Admin
    },
]
const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router