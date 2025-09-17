-- V2__reservation.sql
ALTER TABLE inventory_cards ADD COLUMN order_id BIGINT NULL;
CREATE INDEX idx_card_order ON inventory_cards(order_id);
