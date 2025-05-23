-- Drop tables if they exist (in reverse order of dependencies)
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS accessory;
DROP TABLE IF EXISTS videogame;
DROP TABLE IF EXISTS console;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS users;

-- Create product table (parent table)
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DOUBLE NOT NULL,
    stock INT NOT NULL,
    rating DOUBLE DEFAULT 0
);

-- Create videogame table
CREATE TABLE videogame (
    id BIGINT PRIMARY KEY,
    genre VARCHAR(100),
    developer VARCHAR(100),
    publisher VARCHAR(100),
    release_year INT,
    platform VARCHAR(100),
    FOREIGN KEY (id) REFERENCES product(id) ON DELETE CASCADE
);

-- Create console table
CREATE TABLE console (
    id BIGINT PRIMARY KEY,
    manufacturer VARCHAR(100),
    model VARCHAR(100),
    release_year INT,
    generation VARCHAR(50),
    FOREIGN KEY (id) REFERENCES product(id) ON DELETE CASCADE
);

-- Create accessory table
CREATE TABLE accessory (
    id BIGINT PRIMARY KEY,
    type VARCHAR(100),
    compatibility VARCHAR(100),
    brand VARCHAR(100),
    FOREIGN KEY (id) REFERENCES product(id) ON DELETE CASCADE
);

-- Create review table
CREATE TABLE review (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    product_id BIGINT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);

-- Create orders table
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    customer_email VARCHAR(255) NOT NULL,
    shipping_address TEXT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL
);

-- Create order_item table
CREATE TABLE order_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id)
);

-- Create users table
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);
