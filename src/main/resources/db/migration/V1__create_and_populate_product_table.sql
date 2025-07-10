CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description VARCHAR(1000),
                         price DECIMAL(10, 2) NOT NULL,
                         sku VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO product (name, description, price, sku) VALUES ('Notebook Pro', 'Notebook Gamer c/ 32GB RAM e SSD 1TB', 8500.50, 'NTB-PRO-X1-32');
INSERT INTO product (name, description, price, sku) VALUES ('Mouse Gamer', 'Mouse com 16000 DPI e RGB', 350.75, 'MSE-GMR-RGB-16K');
INSERT INTO product (name, description, price, sku) VALUES ('Teclado Mecânico ', 'Teclado compacto com switches Blue', 550.00, 'KBD-MEC-TKL-BRW');