INSERT INTO products(name, description, price, card_delivery, stock) VALUES
('Netflix 1 Month', '账号共享一个月', 25.00, true, 100),
('ChatGPT Plus Key', '月卡', 35.00, true, 50);

-- inventory cards (sample codes)
INSERT INTO inventory_cards(product_id, code, status) VALUES
(1, 'NF-KEY-111', 'AVAILABLE'),
(1, 'NF-KEY-112', 'AVAILABLE'),
(2, 'CGPT-KEY-201', 'AVAILABLE');
