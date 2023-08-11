drop table IF exists `order_basket`;
CREATE TABLE IF NOT EXISTS `order_basket` (
    `cart_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `product_id` bigint(20) unsigned NOT NULL,
    `iuser` bigint(20) unsigned NOT NULL,
    `count` int(11) NOT NULL,
    `created_at` datetime DEFAULT current_timestamp(),
    PRIMARY KEY (`cart_id`) USING BTREE,
    KEY `product_id` (`product_id`) USING BTREE,
    KEY `iuser` (`iuser`) USING BTREE,
    CONSTRAINT `order_basket_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
    CONSTRAINT `order_basket_ibfk_2` FOREIGN KEY (`iuser`) REFERENCES `user` (`iuser`)
    );


drop table IF exists `review`;
CREATE TABLE `review` (
    `review_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `iuser` BIGINT(20) UNSIGNED NOT NULL,
    `product_id` BIGINT(20) UNSIGNED NOT NULL,
    `ctnt` TEXT NOT NULL ,
    PRIMARY KEY (`review_id`) USING BTREE,
    INDEX `iuser` (`iuser`) USING BTREE,
    INDEX `product_id` (`product_id`) USING BTREE,
    CONSTRAINT `review_ibfk_1` FOREIGN KEY (`iuser`) REFERENCES `user` (`iuser`),
    CONSTRAINT `review_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
);

drop table if exists `orderlist`
CREATE TABLE IF NOT EXISTS `order_list` (
    `order_id` bigint(100) unsigned NOT NULL AUTO_INCREMENT,
    `iuser` bigint(20) unsigned NOT NULL,
    `payment` tinyint(3) unsigned NOT NULL DEFAULT 1,
    `shipment` tinyint(3) unsigned NOT NULL DEFAULT 1,
    `cancel` tinyint(3) unsigned NOT NULL DEFAULT 0,
    `created_at` datetime DEFAULT current_timestamp(),
    `call_user` varchar(50) DEFAULT NULL,
    `request` text DEFAULT NULL,
    `reciever` varchar(20) NOT NULL,
    `address` varchar(50) DEFAULT NULL,
    `address_detail` varchar(50) DEFAULT NULL,
    `delYn` tinyint(4) DEFAULT 0,
    PRIMARY KEY (`order_id`),
    KEY `iuser` (`iuser`),
    CONSTRAINT `order_list_ibfk_1` FOREIGN KEY (`iuser`) REFERENCES `user` (`iuser`)
    ) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

drop table if exists `order_detail`
CREATE TABLE IF NOT EXISTS `order_detail` (
    `order_detail_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `order_id` bigint(20) unsigned NOT NULL,
    `product_id` bigint(20) unsigned NOT NULL,
    `count` int(11) NOT NULL,
    `total_price` int(11) NOT NULL,
    `delYn` tinyint(4) DEFAULT 0,
    PRIMARY KEY (`order_detail_id`),
    KEY `order_id` (`order_id`),
    KEY `product_id` (`product_id`),
    CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_list` (`order_id`),
    CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
