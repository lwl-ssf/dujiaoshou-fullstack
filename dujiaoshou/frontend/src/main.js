// dujiaoshou 注释：前端脚本/路由/API 封装
import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue'
import Home from './pages/Home.vue'
import Product from './pages/Product.vue'
import Order from './pages/Order.vue'
import AdminLogin from './pages/admin/AdminLogin.vue'
import AdminLayout from './pages/admin/AdminLayout.vue'
import AdminProducts from './pages/admin/AdminProducts.vue'
import AdminOrders from './pages/admin/AdminOrders.vue'
import AdminInventory from './pages/admin/AdminInventory.vue'

const routes = [
  { path: '/admin/login', component: AdminLogin },
  { path: '/admin', component: AdminLayout, children: [
    { path: 'products', component: AdminProducts },
    { path: 'orders', component: AdminOrders },
    { path: 'inventory', component: AdminInventory }
  ]},
  { path: '/', component: Home },
  { path: '/product/:id', component: Product, props: true },
  { path: '/order/:sn', component: Order, props: true },
]

const router = createRouter({ history: createWebHistory(), routes })
createApp(App).use(router).mount('#app')
