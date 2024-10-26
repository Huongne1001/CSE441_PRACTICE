CREATE TABLE `categories` ( 
  `id` INT AUTO_INCREMENT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `customers` ( 
  `id` INT AUTO_INCREMENT NOT NULL,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `fullname` VARCHAR(100) NULL,
  `address` VARCHAR(255) NULL,
  `phone` VARCHAR(20) NULL,
  `email` VARCHAR(100) NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `orders` ( 
  `id` INT AUTO_INCREMENT NOT NULL,
  `order_date` DATETIME NOT NULL,
  `estimate_delivery_time` DATETIME NOT NULL,
  `address`  VARCHAR(255) NOT NULL,
  `customer_id` INT NOT NULL,
  `status` ENUM('pending', 'completed') NULL DEFAULT 'pending',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE `products` ( 
  `id` INT AUTO_INCREMENT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `category_id` INT NOT NULL,
  `description` TEXT,
  `price` INT NOT NULL,
  `image` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE `order_details` ( 
  `id` INT AUTO_INCREMENT NOT NULL,
  `product_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- Dữ liệu mẫu cho bảng categories
INSERT INTO `categories` (`name`, `description`) VALUES
('Cafe', 'Danh mục các loại cafe'),
('Nước trái cây', 'Các loại nước ép và sinh tố trái cây'),
('Trà đặc biệt', 'Các loại trà đặc biệt'),
('Bánh ngọt', 'Các loại bánh ngọt ngon miệng'),
('Bánh mặn', 'Các loại bánh mặn hấp dẫn');

-- Dữ liệu mẫu cho bảng customers
INSERT INTO `customers` (`username`, `password`, `fullname`, `address`, `phone`, `email`) VALUES
('huong012', 'hashed_password', 'Huong Le', '123 Đường A, Quận B, TP.C', '0912345678', 'huong012@gmail.com');

-- Dữ liệu mẫu cho bảng products
INSERT INTO `products` (`name`, `category_id`, `description`, `price`, `image`) VALUES
('AMERICANO', 1, 'Cafe đen kiểu Mỹ', 49000, 'americano.png'),
('CAPPUCCINO', 1, 'Cafe sữa với bọt sữa dày', 45000, 'cappuccino.png'),
('ESPRESSO SỮA ĐÁ', 1, 'Cafe espresso với sữa', 45000, 'espresso_sua_da.png'),
('MOCHA ĐÁ', 1, 'Cafe mocha pha đá', 49000, 'mocha_da.png'),
('CAFE ĐEN ĐÁ', 1, 'Cafe đen pha đá', 49000, 'cafe_den_da.png');

-- Thêm sản phẩm cho danh mục 'Nước trái cây'
INSERT INTO `products` (`name`, `category_id`, `description`, `price`, `image`) VALUES
('Nước Cam Tươi', 2, 'Nước cam ép tươi, giàu vitamin C', 55000, 'nuoc_cam.png'),
('Sinh Tố Dâu', 2, 'Sinh tố dâu tươi, ngọt mát', 60000, 'sinh_to_dau.png'),
('Nước Ép Táo', 2, 'Nước ép táo tươi ngon', 52000, 'nuoc_ep_tao.png'),
('Sinh Tố Xoài', 2, 'Sinh tố xoài thơm ngon, giàu dinh dưỡng', 60000, 'sinh_to_xoai.png');

-- Thêm sản phẩm cho danh mục 'Trà đặc biệt'
INSERT INTO `products` (`name`, `category_id`, `description`, `price`, `image`) VALUES
('Trà Đào Cam Sả', 3, 'Trà đào kết hợp với cam và sả', 49000, 'tra_dao_cam_sa.png'),
('Trà Sữa Trân Châu', 3, 'Trà sữa với trân châu dẻo', 55000, 'tra_sua_tran_chau.png'),
('Trà Matcha', 3, 'Trà matcha Nhật Bản', 65000, 'tra_matcha.png'),
('Trà Hoa Cúc', 3, 'Trà hoa cúc thanh mát', 45000, 'tra_hoa_cuc.png');

-- Thêm sản phẩm cho danh mục 'Bánh ngọt'
INSERT INTO `products` (`name`, `category_id`, `description`, `price`, `image`) VALUES
('Bánh Mousse Dâu', 4, 'Bánh mousse dâu ngọt ngào', 75000, 'banh_mousse_dau.png'),
('Bánh Tiramisu', 4, 'Bánh tiramisu truyền thống', 80000, 'banh_tiramisu.png'),
('Bánh Macaron', 4, 'Bánh macaron màu sắc', 45000, 'banh_macaron.png'),
('Bánh Cheesecake', 4, 'Bánh cheesecake mềm mịn', 70000, 'banh_cheesecake.png');

-- Thêm sản phẩm cho danh mục 'Bánh mặn'
INSERT INTO `products` (`name`, `category_id`, `description`, `price`, `image`) VALUES
('Bánh Mì Thịt Nguội', 5, 'Bánh mì kẹp thịt nguội và rau tươi', 45000, 'banh_mi_thit_nguoi.png'),
('Bánh Pizza Mini', 5, 'Pizza mini với topping đa dạng', 55000, 'banh_pizza_mini.png'),
('Bánh Croissant Phô Mai', 5, 'Croissant nhân phô mai', 50000, 'banh_croissant_pho_mai.png'),
('Bánh Mặn Xúc Xích', 5, 'Bánh mặn kẹp xúc xích', 45000, 'banh_man_xuc_xich.png');

-- Dữ liệu mẫu cho bảng orders
INSERT INTO `orders` (`order_date`, `estimate_delivery_time`, `address`, `customer_id`, `status`) VALUES
('2024-10-25 10:30:00', '2024-10-25 11:00:00', '123 Đường A, Quận B, TP.C', 1, 'pending');

-- Dữ liệu mẫu cho bảng order_details
INSERT INTO `order_details` (`product_id`, `order_id`, `quantity`, `price`) VALUES
(1, 1, 2, 98000),
(3, 1, 1, 45000);


