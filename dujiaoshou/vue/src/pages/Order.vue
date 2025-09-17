<!-- dujiaoshou 注释：Vue 组件文件，用于前台或后台界面渲染 -->
<template>
  <div class="card">
    <h2>订单详情</h2>
    <div v-if="loading">加载中...</div>
    <div v-else-if="order">
      <p>订单号：{{ order.sn }}</p>
      <p>状态：{{ order.status }}</p>
      <p>金额：￥{{ Number(order.amount).toFixed(2) }} {{ order.currency }}</p>

      <div v-if="order.status === 'PENDING'">
        <button @click="markPaid">模拟：标记为已支付</button>
      </div>

      <div v-if="order.status === 'PAID'">
        <button @click="deliver">发货（发卡）</button>
      </div>

      <div v-if="order.deliveries?.length">
        <h3>发货内容</h3>
        <ul>
          <li v-for="d in order.deliveries" :key="d.id"><code>{{ d.content }}</code></li>
        </ul>
      </div>
    </div>
    <div v-else>未找到该订单</div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import axios from 'axios'
import { useRoute } from 'vue-router'

const route = useRoute()
const loading = ref(true)
const order = ref(null)

async function fetchOrder() {
  loading.value = true
  try {
    const { data } = await axios.get('/api/orders/' + route.params.sn)
    order.value = data
  } finally {
    loading.value = false
  }
}

async function markPaid() {
  await axios.post(`/api/orders/${route.params.sn}/paid`)
  await fetchOrder()
}

async function deliver() {
  await axios.post(`/api/orders/${route.params.sn}/deliver`)
  await fetchOrder()
}

onMounted(fetchOrder)
watch(() => route.params.sn, fetchOrder)
</script>
