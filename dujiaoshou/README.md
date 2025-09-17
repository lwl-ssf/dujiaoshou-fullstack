# 独角兽 · 全栈示例（Spring Boot + Vue 3）

本项目基于你需求，将 dujiaoka 的核心业务（自动发卡）抽象为 **Spring Boot 后端** 与 **Vue 3 + Vite 前端**，可本地快速跑通下单→支付（模拟）→发卡流程。

## 目录
- backend/  — Spring Boot 3（JPA / Flyway / Thymeleaf），端口 8080
- frontend/ — Vue 3 + Vite（Vue Router / Axios），端口 5173（已配 `/api` 代理到 8080）

## 快速开始
### 启动后端
1. 安装 JDK 17、Maven、MySQL
2. 创建数据库：`CREATE DATABASE dujiaoshou DEFAULT CHARACTER SET utf8mb4;`
3. 修改 `backend/src/main/resources/application.yml` 数据库账号密码
4. 执行：`cd backend && mvn spring-boot:run`

### 启动前端
1. 安装 Node.js (>=18)
2. 执行：`cd frontend && npm install && npm run dev`
3. 浏览器打开 `http://localhost:5173`

> 已启用 CORS/代理，前端可直接调用 `GET /api/products` 等后端接口。

## 功能演示
- 首页拉取商品列表
- 商品详情页下单（创建订单）
- 订单页模拟“标记已支付”和“发货（自动发卡）”，展示卡密

## 后续可扩展
- 接入真实支付回调（支付宝/微信/Stripe）
- 订单超时关闭、库存预占/回滚
- 管理后台（前端加一套 /admin，后端增加权限）
- 多语言与主题模板


## Docker 一键启动（Linux/Windows 通用）
前置：安装 Docker Desktop（Windows）或 Docker Engine（Linux）。

```bash
# Linux/macOS
./run.sh

# Windows PowerShell
./run.ps1
```

- MySQL 暴露在 `3306`，后端 `8080`，前端（Nginx）在 `5173`。
- 可通过环境变量覆盖：`MYSQL_*`、`APP_JWT_SECRET` 等。
- 首次启动后，访问后端：`POST /api/auth/seed` 初始化角色/权限，然后使用
  - 账号：`admin` / 密码：`admin123`
  - 登录前台管理：`/admin/login`，登录后访问 `/admin/products` 等。
