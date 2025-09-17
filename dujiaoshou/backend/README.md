# 独角兽 · Spring Boot 重构骨架

> 受 dujiaoka 启发（Laravel 实现），本项目为 Spring Boot 3 的最小可运行骨架：商品、下单、支付（占位）、自动发卡。**仅供学习参考**。

## 运行

1. 安装 JDK 17、Maven、MySQL。
2. 创建数据库：`CREATE DATABASE djk DEFAULT CHARACTER SET utf8mb4;`
3. 修改 `src/main/resources/application.yml` 中数据库账号密码。
4. 启动：`mvn spring-boot:run`
5. 访问：`http://localhost:8080/`

## API 示例
- `GET /api/products`
- `POST /api/orders/create?productId=1&qty=1&currency=CNY&channel=ALIPAY`
- `POST /api/orders/{sn}/paid`
- `POST /api/orders/{sn}/deliver`

## 说明
- 数据库结构通过 Flyway `V1__init.sql` 初始化，`data.sql` 写入演示数据。
- 支付网关位于 `service/payment/providers`，为占位实现，方便后续对接支付宝/微信/Stripe/PayPal 等。
- 与 dujiaoka 的核心差异：本项目为 Java Spring 技术栈、仅包含最小业务闭环和清晰的分层，便于二次开发。
