# CREATE DATABASE Items;
USE Items;

# CREATE TABLE item(
   #item_id INT NOT NULL AUTO_INCREMENT,
   #item_name VARCHAR(100) NOT NULL,
   #item_price DOUBLE NOT NULL,
   #PRIMARY KEY ( item_id )
# );

INSERT INTO item (item_name, item_price) VALUES ("tomato", 1.5);
SELECT * FROM item;
