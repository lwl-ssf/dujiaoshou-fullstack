<!-- dujiaoshou 注释：Vue 组件文件，用于前台或后台界面渲染 -->
<template>
  <div class="card">
    <h2>库存卡密</h2>
    <div style="display:flex; gap:8px; align-items:center;">
      <input type="number" v-model.number="productId" placeholder="商品ID" />
      <input type="file" @change="onFile" />
      <button @click="upload">上传 CSV/TXT（每行一条卡密）</button>
      <button @click="stat">刷新统计</button>
    </div>
    <div v-if="stats" style="margin-top:12px;">
      <p>可用：{{stats.available}}，已预占：{{stats.reserved}}，已售：{{stats.sold}}</p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { api } from '../../api'

const productId = ref(1)
const file = ref(null)
const stats = ref(null)

function onFile(e){ file.value = e.target.files[0] }

async function upload(){
  if (!file.value) return alert('请选择文件')
  const form = new FormData()
  form.append('file', file.value)
  const { data } = await api.post(`/admin/inventory/${productId.value}/upload`, form)
  alert('上传成功：' + data.inserted)
  await stat()
}
async function stat(){
  stats.value = (await api.get(`/admin/inventory/${productId.value}`)).data
}
</script>
