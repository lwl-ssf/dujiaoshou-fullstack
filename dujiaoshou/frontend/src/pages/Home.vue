<!-- dujiaoshou 注释：Vue 组件文件，用于前台或后台界面渲染 -->
<template>
  <div>
    <div class="card">
      <h2>商品列表</h2>
      <div v-if="loading">加载中...</div>
      <div v-else class="grid">
        <div v-for="p in products" :key="p.id" class="card">
          <h3>{{ p.name }}</h3>
          <p>{{ p.description }}</p>
          <p class="price">￥{{ Number(p.price).toFixed(2) }}</p>
          <router-link :to="'/product/' + p.id"><button>立即购买</button></router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const products = ref([])
const loading = ref(true)

onMounted(async () => {
  try {
    const { data } = await axios.get('/api/products')
    products.value = data
  } finally {
    loading.value = false
  }
})
</script>
