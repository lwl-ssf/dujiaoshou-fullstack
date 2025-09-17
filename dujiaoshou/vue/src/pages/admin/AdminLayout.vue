<!-- dujiaoshou 注释：Vue 组件文件，用于前台或后台界面渲染 -->
<template>
  <div>
    <div class="card" style="display:flex; gap:12px;">
      <router-link to="/admin/products"><button>商品管理</button></router-link>
      <router-link to="/admin/orders"><button>订单管理</button></router-link>
      <router-link to="/admin/inventory"><button>库存卡密</button></router-link>
    </div>
    <router-view />
  </div>
</template>
