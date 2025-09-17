<!-- dujiaoshou 注释：Vue 组件文件，用于前台或后台界面渲染 -->
<template>
  <div class="container">
    <header>
      <h1>独角兽 · Vue 前台（演示）</h1>
      <nav>
        <router-link to="/">首页</router-link>
      </nav>
    </header>
    <router-view />
    <footer>© {{year}} Demo · 后端 Spring Boot · 前端 Vue3+Vite</footer>
  </div>
</template>

<script setup>
const year = new Date().getFullYear()
</script>

<style>
body { font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans SC", "PingFang SC", "Microsoft YaHei", sans-serif; margin:0; background:#0b0c10; color:#eaeaea;}
.container { max-width: 960px; margin: 0 auto; padding: 16px;}
a { color: #64b5f6; text-decoration: none; margin-right: 12px;}
header { display:flex; align-items:center; justify-content:space-between; margin-bottom: 16px;}
h1 { font-size: 20px; margin: 0;}
.card { background:#111217; border:1px solid #20222a; border-radius: 12px; padding: 16px; margin: 12px 0; box-shadow: 0 5px 20px rgba(0,0,0,.2);}
button { border-radius: 10px; padding: 10px 14px; border: 1px solid #2a2d3a; background: #1a1c24; color: #fff; cursor: pointer;}
button:hover { filter: brightness(1.1);}
input, select { background:#0f1117; border:1px solid #2a2d3a; color:#eaeaea; padding: 8px; border-radius: 10px;}
.grid { display:grid; grid-template-columns: repeat(auto-fill, minmax(240px, 1fr)); gap: 12px;}
.price { font-weight: 700;}
footer { margin-top: 36px; opacity: .6; font-size: 12px;}
</style>
