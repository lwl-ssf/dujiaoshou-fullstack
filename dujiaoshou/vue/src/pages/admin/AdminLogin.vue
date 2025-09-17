<!-- dujiaoshou 注释：Vue 组件文件，用于前台或后台界面渲染 -->
<template>
  <div class="card">
    <h2>管理员登录</h2>
    <input v-model="username" placeholder="用户名（默认 admin）" />
    <input v-model="password" placeholder="密码（默认 admin123）" type="password" />
    <div style="margin-top:8px;"><button @click="login">登录</button></div>
    <p style="opacity:.7;margin-top:8px;">首次使用：先在后端启动后请求一次 <code>POST /api/auth/seed</code> 初始化角色权限。</p>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { api } from '../../api'

const username = ref('admin')
const password = ref('admin123')

async function login(){
  const { data } = await axios.post('/api/auth/login', { username: username.value, password: password.value })
  localStorage.setItem('jwt', data.token)
  api.defaults.headers.common['Authorization'] = 'Bearer ' + data.token
  window.location.href = '/admin/products'
}
</script>
