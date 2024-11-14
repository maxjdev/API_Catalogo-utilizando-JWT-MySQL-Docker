INSERT INTO tb_brand (name, status, created_at, updated_at)
VALUES
    ('Taurus', 'ACTIVE', NOW(), NOW()),
    ('Imbel', 'ACTIVE', NOW(), NOW()),
    ('Glock', 'ACTIVE', NOW(), NOW()),
    ('Colt', 'ACTIVE', NOW(), NOW()),
    ('Sig Sauer', 'ACTIVE', NOW(), NOW()),
    ('Beretta', 'ACTIVE', NOW(), NOW()),
    ('Smith & Wesson', 'ACTIVE', NOW(), NOW()),
    ('Heckler & Koch', 'ACTIVE', NOW(), NOW());

INSERT INTO tb_product (name, brand_id, description, price, stock_quantity, status, created_at, updated_at)
VALUES
    ('Pistola G2C', 1, 'Pistola compacta e leve, ideal para porte velado.', 2500.00, 15, 'ACTIVE', NOW(), NOW()),
    ('Pistola TS9', 1, 'Pistola para uso tático e esportivo, com alta precisão.', 3200.00, 10, 'ACTIVE', NOW(), NOW()),
    ('Carabina IA2', 2, 'Carabina de alta resistência, utilizada por forças armadas.', 4500.00, 8, 'ACTIVE', NOW(), NOW()),
    ('Pistola .40', 2, 'Pistola popular para uso policial, confiável e durável.', 2800.00, 20, 'ACTIVE', NOW(), NOW()),
    ('Glock 17', 3, 'Modelo clássico da Glock, confiável e amplamente utilizado.', 3000.00, 25, 'ACTIVE', NOW(), NOW()),
    ('Glock 19', 3, 'Modelo compacto da Glock, ideal para porte velado.', 3100.00, 18, 'ACTIVE', NOW(), NOW()),
    ('Colt M1911', 4, 'Pistola lendária com mais de um século de uso militar.', 3500.00, 12, 'ACTIVE', NOW(), NOW()),
    ('Colt Python', 4, 'Revólver de alta precisão e construção robusta.', 3700.00, 5, 'ACTIVE', NOW(), NOW()),
    ('Sig Sauer P320', 5, 'Pistola modular e confiável, usada por forças militares.', 3300.00, 10, 'INACTIVE', NOW(), NOW()),
    ('Sig Sauer P226', 5, 'Pistola clássica de serviço, com alta precisão.', 3400.00, 6, 'DELETED', NOW(), NOW()),
    ('Beretta 92FS', 6, 'Pistola de alto desempenho com design clássico.', 3100.00, 12, 'INACTIVE', NOW(), NOW()),
    ('Beretta PX4 Storm', 6, 'Pistola compacta, ideal para defesa pessoal.', 2900.00, 5, 'DELETED', NOW(), NOW()),
    ('S&W M&P9', 7, 'Pistola de polímero com ergonomia avançada.', 3200.00, 9, 'INACTIVE', NOW(), NOW()),
    ('S&W 686', 7, 'Revólver robusto, projetado para alto desempenho.', 3600.00, 3, 'DELETED', NOW(), NOW()),
    ('HK USP', 8, 'Pistola versátil, projetada para uso militar e policial.', 3500.00, 8, 'INACTIVE', NOW(), NOW()),
    ('HK VP9', 8, 'Pistola leve com sistema de disparo inovador.', 3400.00, 4, 'DELETED', NOW(), NOW());