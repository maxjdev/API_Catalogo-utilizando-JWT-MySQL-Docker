CREATE TABLE IF NOT EXISTS tb_brand (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    status VARCHAR(10) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    brand_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(7, 2) NOT NULL,
    stock_quantity INT NOT NULL,
    status VARCHAR(10) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES tb_brand(id)
);

CREATE TABLE IF NOT EXISTS tb_user (
  id VARCHAR(255) PRIMARY KEY,
   status VARCHAR(10) NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   login VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   role VARCHAR(10) NOT NULL
);