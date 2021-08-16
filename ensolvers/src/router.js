import Vue from 'vue'
import VueRouter from 'vue-router'
import Todos from './components/Todos.vue'
import Folders from './components/Folders.vue'
import Edit from './components/Edit.vue'


Vue.use(VueRouter)
export const router = new VueRouter({
    mode: 'history',
    routes: [
        {path: "/", redirect: "/folders"},
        {path: "/folders", component: Folders},
        {path: "/todos", component: Todos},
        {path: "/edit", component: Edit}
    ]
})