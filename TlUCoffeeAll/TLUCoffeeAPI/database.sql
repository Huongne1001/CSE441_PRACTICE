CREATE TABLE `categories` ( 
  `id` INT AUTO_INCREMENT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `description` TEXT NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (`id`)
)
ENGINE = InnoDB;
CREATE TABLE `customers` ( 
  `id` INT AUTO_INCREMENT NOT NULL,
  `name` VARCHAR(100) NULL,
  `points` VARCHAR(50) NULL,
  `full_name` VARCHAR(100) NULL,
  `phone` VARCHAR(20) NULL,
  `preferred_payment` VARCHAR(255) NULL,
  `email` VARCHAR(100) NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (`id`)
)
ENGINE = InnoDB;
CREATE TABLE `order_details` ( 
  `id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `price` FLOAT NOT NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (`id`, `product_id`)
)
ENGINE = InnoDB;
CREATE TABLE `orders` ( 
  `id` INT AUTO_INCREMENT NOT NULL,
  `order_date` DATETIME NOT NULL,
  `usage_time` VARCHAR(255) NULL,
  `status` ENUM('pending','completed') NULL DEFAULT 'pending' ,
  `surcharge` FLOAT NULL,
  `payment_status` ENUM('paid','unpaid') NULL DEFAULT 'unpaid' ,
  CONSTRAINT `PRIMARY` PRIMARY KEY (`id`)
)
ENGINE = InnoDB;
CREATE TABLE `products` ( 
  `id` INT AUTO_INCREMENT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `category_id` INT NULL,
  `price` FLOAT NOT NULL,
  `image` VARCHAR(255) NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (`id`)
)
ENGINE = InnoDB;
ALTER TABLE `order_details` ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `order_details` ADD CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `products` ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

INSERT INTO categories (name, description) VALUES
('Coffee', 'Traditional and special coffee'),
('Tea', 'Tea and milk tea varieties'),
('Juice', 'Fresh fruit juices'),
('Smoothie', 'Fresh fruit smoothies'),
('Snacks', 'Light snacks and desserts');

-- Dữ liệu mẫu cho bảng products
INSERT INTO products (name, category_id, price, image) VALUES
('Black Coffee', 1, 25000, 'black-coffee.jpg'),
('Milk Coffee', 1, 30000, 'milk-coffee.jpg'),
('Americano', 1, 35000, 'americano.jpg'),
('Cappuccino', 1, 45000, 'cappuccino.jpg'),
('Lotus Tea', 2, 35000, 'lotus-tea.jpg'),
('Peach Tea', 2, 35000, 'peach-tea.jpg'),
('Traditional Milk Tea', 2, 40000, 'milk-tea.jpg'),
('Orange Juice', 3, 35000, 'orange-juice.jpg'),
('Pineapple Juice', 3, 35000, 'pineapple-juice.jpg'),
('Avocado Smoothie', 4, 40000, 'avocado-smoothie.jpg');

-- Dữ liệu mẫu cho bảng customers
INSERT INTO customers (name, points, full_name, phone, email) VALUES
('John Doe', '100', 'John Doe', '0901234567', 'john.doe@email.com'),
('Jane Smith', '150', 'Jane Smith', '0912345678', 'jane.smith@email.com'),
('Mike Johnson', '80', 'Mike Johnson', '0923456789', 'mike.j@email.com');

-- Dữ liệu mẫu cho bảng orders

INSERT INTO orders (order_date, usage_time, status, surcharge) VALUES
(NOW(), '30 minutes', 'completed', 0),
(NOW(), '45 minutes', 'completed', 10000),
(NOW(), '60 minutes', 'pending', 0);

-- Dữ liệu mẫu cho bảng order_details
INSERT INTO order_details (id, product_id, quantity, price) VALUES
(1, 1, 2, 25000),
(1, 4, 1, 45000),
(2, 7, 3, 40000),
(2, 8, 2, 35000),
(3, 2, 1, 30000),
(3, 5, 2, 35000);