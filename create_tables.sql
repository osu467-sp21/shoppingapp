SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS; 
SET FOREIGN_KEY_CHECKS=0;  

SHOW databases;
CREATE DATABASE Items;
USE Items;
-- USE shoppingapp;

DROP Table IF EXISTS Store; 
DROP Table IF EXISTS User;
DROP Table IF EXISTS Product;
DROP Table IF EXISTS Shopping_List;
DROP Table IF EXISTS Store_Product;
DROP Table IF EXISTS Price;
DROP Table IF EXISTS Store_Product_Price;
DROP Table IF EXISTS Product_Review;

CREATE TABLE IF NOT EXISTS Store
(
      store_id INT AUTO_INCREMENT NOT NULL,
      store_name VARCHAR(255) NOT NULL,
      addr_line_1 VARCHAR(255) NOT NULL,
      addr_line_2 VARCHAR(255),
      addr_line_3 VARCHAR(255),
      city VARCHAR(255) NOT NULL,
      state VARCHAR(255) NOT NULL,
      zip_code INT NOT NULL,
      country VARCHAR(255) NOT NULL,
      PRIMARY KEY (store_id)
);

CREATE TABLE IF NOT EXISTS User
(
      user_id VARCHAR(255) NOT NULL,
      username VARCHAR(255) NOT NULL,
      signup_date DATE NOT NULL,
      first_name VARCHAR(255),
      last_name VARCHAR(255),
      master_shopper_level INT NOT NULL,
      zip_code VARCHAR(255),
      active_shopping_list INT,
      PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS Shopping_List
(
      shopping_list_id INT AUTO_INCREMENT NOT NULL,
      user_id VARCHAR(255),
      PRIMARY KEY (shopping_list_id)
);

CREATE TABLE IF NOT EXISTS Product
(
      product_id INT AUTO_INCREMENT NOT NULL,
      barcode VARCHAR(255),
      item_name VARCHAR(255) NOT NULL,
      size INT NOT NULL,
      size_unit VARCHAR(255) NOT NULL,
      generic_product INT,
      PRIMARY KEY (product_id)
);

CREATE TABLE IF NOT EXISTS Store_Product
(
      store_id INT NOT NULL,
      product_id INT NOT NULL,
      PRIMARY KEY (store_id, product_id)
);

CREATE TABLE IF NOT EXISTS Price
(
      price_id INT AUTO_INCREMENT NOT NULL,
      value INT NOT NULL,
      is_sale BOOLEAN NOT NULL,
      sale_expiration VARCHAR(255),
      date_entered VARCHAR(255) NOT NULL,
      user_id VARCHAR(255),
      PRIMARY KEY (price_id)
);

CREATE TABLE IF NOT EXISTS Store_Product_Price
(
      store_id INT NOT NULL,
      product_id INT NOT NULL,
      price_id INT NOT NULL,
      PRIMARY KEY (store_id, product_id, price_id)

);

CREATE TABLE IF NOT EXISTS Product_Review
(
      user_id VARCHAR(255),
      product_id INT NOT NULL,
      comment VARCHAR(255),
      rating INT NOT NULL,
      PRIMARY KEY (user_id, product_id)
);

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

