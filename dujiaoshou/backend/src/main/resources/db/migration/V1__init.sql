-- V1__init.sql
CREATE TABLE products(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL UNIQUE,
  description VARCHAR(255),
  price DECIMAL(12,2) NOT NULL,
  card_delivery BOOLEAN NOT NULL,
  stock INT
);

CREATE TABLE inventory_cards(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  product_id BIGINT NOT NULL,
  code VARCHAR(512) NOT NULL UNIQUE,
  status VARCHAR(16) NOT NULL,
  CONSTRAINT fk_card_product FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE orders(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  sn VARCHAR(64) NOT NULL UNIQUE,
  status VARCHAR(16) NOT NULL,
  amount DECIMAL(12,2) NOT NULL,
  currency VARCHAR(3) NOT NULL,
  payment_channel VARCHAR(64),
  created_at TIMESTAMP NULL,
  paid_at TIMESTAMP NULL,
  delivered_at TIMESTAMP NULL
);

CREATE TABLE order_items(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  quantity INT,
  unit_price DECIMAL(12,2) NOT NULL,
  CONSTRAINT fk_item_order FOREIGN KEY (order_id) REFERENCES orders(id),
  CONSTRAINT fk_item_product FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE order_deliveries(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  content VARCHAR(1024),
  CONSTRAINT fk_delivery_order FOREIGN KEY (order_id) REFERENCES orders(id)
);

CREATE TABLE users(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(64) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  role VARCHAR(16) NOT NULL
);
