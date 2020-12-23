BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price int);
INSERT INTO products (title, price) VALUES
('american bobtail cat', 10000),
('bengal cat', 20000),
('sphynx cat', 15000),
('norwegian forest cat', 50000),
('pixie-bob cat', 30000),
('maine coon cat', 30000),
('snowshoe cat', 30000),
('persian cat', 30000),
('donskoy cat', 30000),
('savannah cat', 500000);

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO users (name) VALUES
('Alice'),
('Ricky');

DROP TABLE IF EXISTS products_users CASCADE;
CREATE TABLE products_users (product_id bigint, user_id bigint, FOREIGN KEY (product_id) REFERENCES products (id), FOREIGN KEY (user_id) REFERENCES users (id));
INSERT INTO products_users (product_id, user_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(1, 2),
(2, 2);



COMMIT;