<!-- dujiaoshou 注释：Vue 组件文件，用于前台或后台界面渲染 -->
<template>
  <div class="card">
    <h2>订单管理</h2>
    <table v-if="items.length" border="0" cellpadding="8">
      <thead><tr><th>SN</th><th>状态</th><th>金额</th><th>渠道</th><th>创建时间</th><th>操作</th></tr></thead>
      <tbody>
        <tr v-for="o in items" :key="o.sn">
          <td>{{o.sn}}</td>
          <td>{{o.status}}</td>
          <td>{{o.amount}}</td>
          <td>{{o.paymentChannel}}</td>
          <td>{{o.createdAt}}</td>
          <td>
            <button @click="deliver(o.sn)">手动发货</button>
            <button @click="cancel(o.sn)">取消</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { api } from '../../api'

const items = ref([])

async function load(){ items.value = (await api.get('/admin/orders')).data }
async function deliver(sn){ await api.post(`/admin/orders/${sn}/deliver`); await load() }
async function cancel(sn){ await api.post(`/admin/orders/${sn}/cancel`); await load() }

onMounted(load)
</script>
