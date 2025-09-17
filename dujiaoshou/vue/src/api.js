// dujiaoshou 注释：前端脚本/路由/API 封装
import axios from 'axios'

export const api = axios.create({
  baseURL: (import.meta.env.VITE_API_BASE || '/api')
})

const jwt = localStorage.getItem('jwt')
if (jwt) api.defaults.headers.common['Authorization'] = 'Bearer ' + jwt
