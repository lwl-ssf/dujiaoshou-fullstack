<!-- dujiaoshou 注释：Vue 组件文件，用于前台或后台界面渲染 -->
<template>
  <div class="card">
    <h2>商品管理</h2>
    <table v-if="items.length" border="0" cellpadding="8">
      <thead><tr><th>ID</th><th>名称</th><th>价格</th><th>发卡</th><th>操作</th></tr></thead>
      <tbody>
        <tr v-for="p in items" :key="p.id">
          <td>{{p.id}}</td>
          <td><input v-model="p.name" /></td>
          <td><input v-model.number="p.price" type="number" step="0.01" /></td>
          <td><input type="checkbox" v-model="p.cardDelivery" /></td>
          <td>
            <button @click="save(p)">保存</button>
            <button @click="remove(p)">删除</button>
          </td>
        </tr>
      </tbody>
    </table>
    <div style="margin-top:12px;">
      <button @click="add()">新增商品</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { api } from '../../api'

const items = ref([])

async function load(){ items.value = (await api.get('/admin/products')).data }
async function save(p){
  if (p.id) await api.put('/admin/products/'+p.id, p)
  else Object.assign(p, (await api.post('/admin/products', p)).data)
}
async function remove(p){
  await api.delete('/admin/products/'+p.id)
  await load()
}
function add(){
  items.value.push({ name:'新商品', price: 9.99, cardDelivery: true, stock: 0 })
}
onMounted(load)
</script>
