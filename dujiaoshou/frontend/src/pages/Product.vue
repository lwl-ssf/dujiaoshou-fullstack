<!-- dujiaoshou 注释：Vue 组件文件，用于前台或后台界面渲染 -->
<template>
  <div class="card" v-if="product">
    <h2>{{ product.name }}</h2>
    <p>{{ product.description }}</p>
    <p>单价：<b>￥{{ Number(product.price).toFixed(2) }}</b></p>
    <label>数量：
      <input type="number" v-model.number="qty" min="1" />
    </label>
    <div style="margin-top:8px;">
      <select v-model="channel">
        <option value="ALIPAY">支付宝</option>
        <option value="WECHAT">微信</option>
        <option value="STRIPE">Stripe</option>
      </select>
    </div>
    <div style="margin-top:12px;">
      <button @click="createOrder">创建订单</button>
    </div>
    <p v-if="orderSn" style="margin-top:12px;">订单已创建：
      <router-link :to="'/order/' + orderSn">{{ orderSn }}</router-link>
    </p>
  </div>
  <div v-else class="card">加载中...</div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { useRoute } from 'vue-router'

const route = useRoute()
const product = ref(null)
const qty = ref(1)
const channel = ref('ALIPAY')
const orderSn = ref('')

onMounted(async () => {
  const { data } = await axios.get('/api/products/' + route.params.id)
  product.value = data
})

async function createOrder() {
  const { data } = await axios.post('/api/orders/create', null, {
    params: { productId: product.value.id, qty: qty.value, currency: 'CNY', channel: channel.value }
  })
  orderSn.value = data.sn
}
</script>
