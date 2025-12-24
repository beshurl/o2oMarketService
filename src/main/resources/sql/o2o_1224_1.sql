-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: o2omarket
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (0,'전체'),(1,'육류'),(2,'수산물'),(3,'채소'),(4,'과자'),(5,'과일'),(6,'냉동식품'),(7,'베이커리'),(8,'기타');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `product_id` int NOT NULL,
  `content` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rating` float NOT NULL,
  PRIMARY KEY (`comment_id`),
  UNIQUE KEY `uk_user_product` (`user_id`,`product_id`),
  KEY `fk_comment_product` (`product_id`),
  CONSTRAINT `fk_comment_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (14,'user1',1,'string',0),(15,'testuser1',4,'굳 ㅎㅎ',5);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon` (
  `coupon_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `coupon_type` enum('AMOUNT','PERCENT','SHIPPING') COLLATE utf8mb4_unicode_ci NOT NULL,
  `coupon_description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `discount_value` int NOT NULL,
  `valid_from` datetime NOT NULL,
  `valid_to` datetime NOT NULL,
  `is_used` tinyint(1) NOT NULL DEFAULT '0',
  `used_at` datetime DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`coupon_id`),
  KEY `fk_coupon_user` (`user_id`),
  CONSTRAINT `fk_coupon_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (1,'testuser1','AMOUNT','3,000원 즉시 할인 쿠폰',3000,'2025-01-01 00:00:00','2025-12-31 23:59:59',1,'2025-12-23 01:01:01','2025-12-22 14:35:00'),(2,'testuser1','PERCENT','결제 금액의 10% 할인 쿠폰',10,'2025-01-01 00:00:00','2025-06-30 23:59:59',1,'2025-02-10 12:30:00','2025-12-22 14:35:00'),(3,'testuser1','SHIPPING','배송비 무료 쿠폰',3000,'2025-01-01 00:00:00','2025-03-31 23:59:59',0,NULL,'2025-12-22 14:35:00');
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `discount_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `type` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rate` float DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`discount_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `discount_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,1,'DISCOUNT',20,'2025-12-16 00:00:00','2025-12-31 23:59:59',1),(2,3,'DEADLINE',50,'2025-12-16 18:00:00','2025-12-16 23:59:59',1);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `location_code` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `zone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `x_code` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `y_code` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`location_code`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `location_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,1,'육류 코너','3','5'),(2,2,'채소 코너','1','2'),(3,3,'과자 코너','4','1');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `notification_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_read` tinyint NOT NULL DEFAULT '0',
  `read_at` datetime DEFAULT NULL,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  `deleted_at` datetime DEFAULT NULL,
  `target_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `target_id` bigint DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`notification_id`),
  KEY `fk_notification_user` (`user_id`),
  CONSTRAINT `fk_notification_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'testuser1','order','주문 완료','주문이 정상적으로 완료되었습니다.',1,'2025-12-24 09:28:02',0,NULL,'order',101,'2025-01-01 10:00:00'),(2,'testuser1','delivery','배송 시작','상품이 배송을 시작했습니다.',0,NULL,0,NULL,'order',101,'2025-01-02 09:30:00'),(3,'testuser1','promotion','행사 알림','오늘만 특가 행사 진행 중!',1,'2025-01-03 12:00:00',0,NULL,NULL,NULL,'2025-01-03 09:00:00'),(4,'testuser1','info','서비스 점검 안내','내일 새벽 2시부터 서비스 점검이 예정되어 있습니다.',1,'2025-01-04 08:10:00',1,'2025-12-23 16:43:55',NULL,NULL,'2025-01-04 07:00:00'),(5,'testuser1','promotion','지난 행사 알림','종료된 행사 알림입니다.',1,'2024-12-20 10:00:00',1,'2024-12-21 09:00:00',NULL,NULL,'2024-12-20 08:00:00'),(6,'testuser1','order','Order completed','Your order has been completed.',0,NULL,0,NULL,'order',27,'2025-12-24 09:32:20');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `order_item_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `discount_applied` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `fk_order_detail_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE,
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (37,1,1,1,24000,1),(38,2,2,1,2000,0),(39,3,3,1,1500,1),(40,4,4,1,8900,0),(41,5,5,1,4500,0),(42,6,6,1,4500,0),(43,7,7,1,6000,0),(44,8,1,1,24000,1),(45,9,2,1,2000,0),(46,20,1,1,24000,1),(47,21,3,1,3000,0),(48,22,3,1,3000,0),(49,23,3,1,3000,0),(50,24,3,1,3000,0),(51,25,3,1,3000,0),(52,26,1,0,24000,1),(53,27,4,1,8900,0);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `order_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `total_price` int DEFAULT NULL,
  `zip_code` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `product_total` int NOT NULL DEFAULT '0',
  `delivery_fee` int NOT NULL DEFAULT '0',
  `discount` int NOT NULL DEFAULT '0',
  `payment_method` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `carrier` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tracking_number` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `estimated_date` datetime DEFAULT NULL,
  `coupon_id` bigint DEFAULT NULL,
  `used_points` int NOT NULL DEFAULT '0',
  `coupon_discount` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  KEY `fk_orders_coupon` (`coupon_id`),
  CONSTRAINT `fk_orders_coupon` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`coupon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'testuser1','2025-01-10 10:12:00','PENDING',27000,'06123',30000,3000,6000,'CARD',NULL,NULL,NULL,NULL,0,0),(2,'testuser1','2025-01-11 14:05:00','PROCESSING',5000,'06123',2000,3000,0,'CARD',NULL,NULL,NULL,NULL,0,0),(3,'testuser1','2025-01-12 09:30:00','SHIPPING',4500,'06123',3000,3000,1500,'CARD','CJ','1234567890','2025-01-13 18:00:00',NULL,0,0),(4,'testuser1','2025-01-12 18:40:00','DELIVERED',8900,'06123',8900,0,0,'CARD','CJ','1234567891','2025-01-13 18:00:00',NULL,0,0),(5,'testuser1','2025-01-13 11:20:00','CANCELLED',7500,'06123',4500,3000,0,'CARD',NULL,NULL,NULL,NULL,0,0),(6,'testuser1','2025-01-14 08:15:00','PROCESSING',7500,'06123',4500,3000,0,'CARD',NULL,NULL,NULL,NULL,0,0),(7,'testuser1','2025-01-15 16:50:00','SHIPPING',9000,'06123',6000,3000,0,'CARD','CJ','1234567892','2025-01-16 18:00:00',NULL,0,0),(8,'testuser1','2025-01-16 12:10:00','DELIVERED',24000,'06123',30000,0,6000,'CARD','CJ','1234567893','2025-01-17 18:00:00',NULL,0,0),(9,'testuser1','2025-01-17 09:05:00','PENDING',5000,'06123',2000,3000,0,'CARD',NULL,NULL,NULL,NULL,0,0),(10,'testuser1','2025-01-18 20:25:00','PROCESSING',28000,'06123',25000,3000,0,'CARD',NULL,NULL,NULL,NULL,0,0),(11,'testuser1','2025-01-10 10:12:00','PENDING',18000,'06123',15000,3000,0,'CARD',NULL,NULL,NULL,NULL,0,0),(12,'testuser1','2025-01-11 14:05:00','PROCESSING',23000,'06123',22000,3000,2000,'CARD',NULL,NULL,NULL,NULL,0,0),(13,'testuser1','2025-01-12 09:30:00','SHIPPING',15000,'06123',12000,3000,0,'CARD','CJ','1234567890','2025-01-13 18:00:00',NULL,0,0),(14,'testuser1','2025-01-12 18:40:00','DELIVERED',30000,'06123',34000,0,4000,'CARD','CJ','1234567891','2025-01-13 18:00:00',NULL,0,0),(15,'testuser1','2025-01-13 11:20:00','CANCELLED',21000,'06123',18000,3000,0,'CARD',NULL,NULL,NULL,NULL,0,0),(16,'testuser1','2025-01-14 08:15:00','PROCESSING',29000,'06123',27000,3000,1000,'CARD',NULL,NULL,NULL,NULL,0,0),(17,'testuser1','2025-01-15 16:50:00','SHIPPING',12000,'06123',9000,3000,0,'CARD','CJ','1234567892','2025-01-16 18:00:00',NULL,0,0),(18,'testuser1','2025-01-16 12:10:00','DELIVERED',41000,'06123',41000,0,0,'CARD','CJ','1234567893','2025-01-17 18:00:00',NULL,0,0),(19,'testuser1','2025-01-17 09:05:00','PENDING',19000,'06123',16000,3000,0,'CARD',NULL,NULL,NULL,NULL,0,0),(20,'testuser1','2025-12-22 19:55:18','주문 완료',24000,NULL,24000,0,0,NULL,NULL,NULL,NULL,NULL,0,0),(21,'testuser1','2025-12-23 00:32:23','주문 완료',4500,'06123',3000,3000,500,'CARD','준용배달업체','7903974919','2025-12-25 18:00:00',1,500,0),(22,'testuser1','2025-12-23 00:44:50','주문 완료',4500,'06123',3000,3000,500,'CARD','준용배달업체','8617262166','2025-12-25 18:00:00',1,500,0),(23,'testuser1','2025-12-23 00:51:29','주문 완료',4500,'06123',3000,3000,500,'CARD','준용배달업체','1349428603','2025-12-25 18:00:00',1,500,0),(24,'testuser1','2025-12-23 00:55:21','주문 완료',4500,'06123',3000,3000,500,'CARD','준용배달업체','9510089376','2025-12-25 18:00:00',1,500,0),(25,'testuser1','2025-12-23 01:01:01','주문 완료',4500,'06123',3000,3000,1500,'CARD','준용배달업체','5504547068','2025-12-25 18:00:00',1,500,1000),(26,'testuser1','2025-12-23 09:23:10','주문 완료',0,'string',0,0,500,'string','준용배달업체','7237199046','2025-12-25 18:00:00',NULL,500,0),(27,'testuser1','2025-12-24 09:32:20','주문 완료',11400,'',8900,2500,0,'card','준용배달업체','8404592082','2025-12-26 18:00:00',NULL,0,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `point`
--

DROP TABLE IF EXISTS `point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point` (
  `point_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `point_type` enum('EARN','USE','EXPIRE') COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount` int NOT NULL,
  `reason` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `expired_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`point_id`),
  KEY `fk_point_user` (`user_id`),
  CONSTRAINT `fk_point_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `point`
--

LOCK TABLES `point` WRITE;
/*!40000 ALTER TABLE `point` DISABLE KEYS */;
INSERT INTO `point` VALUES (1,'testuser1','EARN',500,'상품 구매 적립','2025-06-30 00:00:00','2025-12-22 14:28:39'),(2,'testuser1','EARN',1000,'이벤트 참여 적립','2025-12-31 00:00:00','2025-12-22 14:28:39'),(3,'testuser1','USE',300,'상품 결제 시 사용',NULL,'2025-12-22 14:28:39'),(4,'testuser1','EXPIRE',200,'유효기간 만료',NULL,'2025-12-22 14:28:39');
/*!40000 ALTER TABLE `point` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `point_history`
--

DROP TABLE IF EXISTS `point_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point_history` (
  `point_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `earned_points` int NOT NULL DEFAULT '0',
  `used_points` int NOT NULL DEFAULT '0',
  `expired_points` int NOT NULL DEFAULT '0',
  `earn_method` enum('PURCHASE','EVENT','ETC') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `expire_reason` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `valid_from` datetime DEFAULT NULL,
  `valid_to` datetime DEFAULT NULL,
  `occurred_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`point_id`),
  KEY `fk_point_history_user` (`user_id`),
  CONSTRAINT `fk_point_history_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `CONSTRAINT_1` CHECK (((`earned_points` >= 0) and (`used_points` >= 0) and (`expired_points` >= 0)))
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `point_history`
--

LOCK TABLES `point_history` WRITE;
/*!40000 ALTER TABLE `point_history` DISABLE KEYS */;
INSERT INTO `point_history` VALUES (1,'testuser1',1000,0,0,'PURCHASE',NULL,'2025-01-01 00:00:00','2025-12-31 23:59:59','2025-01-01 10:00:00'),(2,'testuser1',0,500,0,NULL,NULL,NULL,NULL,'2025-02-01 12:00:00'),(3,'testuser1',0,0,300,NULL,'유효기간 만료','2025-01-01 00:00:00','2025-03-31 23:59:59','2025-04-01 00:00:00'),(4,'testuser1',2000,0,0,'EVENT',NULL,'2025-01-15 00:00:00','2025-07-15 23:59:59','2025-01-15 09:00:00'),(5,'testuser1',0,500,0,NULL,NULL,NULL,NULL,'2025-12-23 01:01:01'),(6,'testuser1',30,0,0,'PURCHASE',NULL,'2025-12-23 01:01:01','2026-12-23 01:01:01','2025-12-23 01:01:01'),(7,'testuser1',0,500,0,NULL,NULL,NULL,NULL,'2025-12-23 09:23:11'),(8,'testuser1',89,0,0,'PURCHASE',NULL,'2025-12-24 09:32:21','2026-12-24 09:32:21','2025-12-24 09:32:21');
/*!40000 ALTER TABLE `point_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` int NOT NULL,
  `count` int NOT NULL,
  `sold_count` int NOT NULL DEFAULT '0',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_discount` tinyint(1) DEFAULT '0',
  `is_deadline_sale` tinyint(1) DEFAULT '0',
  `deadline_at` datetime DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'한우 등심',30000,9,2,'비싸지만 맛있는 한우 등심','beef.jpg',0,0,NULL),(2,'상추',2000,20,0,'야채도 먹어야죠','lettuce.jpg',0,0,NULL),(3,'초코 과자',3000,15,6,'초코초코','choco.jpg',0,0,NULL),(4,'고등어',8900,9,2,'신선한 국산 고등어','/images/product/수산물_고등어.webp',0,0,NULL),(5,'꽁치 통조림',4500,25,0,'담백한 꽁치 통조림','/images/product/수산물_꽁치통조림.webp',0,0,NULL),(6,'가자미',4500,10,0,'맛있는 가자미','/images/product/수산물_가자미.jpg',0,0,NULL),(7,'제주 은갈치',6000,15,1,'생선은 역시 갈치','/images/product/수산물_제주은갈치.jpg',0,0,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `product_id` int NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`product_id`,`category_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `product_category_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `product_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,1),(4,2),(5,2),(6,2),(7,2),(2,3),(3,4);
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rating` float DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `user_id` (`user_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `birth_date` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `point` int DEFAULT '0',
  `address_detail` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1','1','1','1','1','1','1','1',1,NULL),('testuser1','password123','정준용','hong123@naver.com','서울시 강남구','010-1111-2222','남자','1999.03.01',9119,'111'),('testuser2','password123','김영희','kim123@naver.com','서울시 서초구','010-3333-4444','여자','2004.04.21',500,NULL),('testuser3','password123','이철수','lee123@naver.com','서울시 송파구','010-5555-6666','남자','2007.07.31',0,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_fcm_token`
--

DROP TABLE IF EXISTS `user_fcm_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_fcm_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `fcm_token` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_fcm_token`
--

LOCK TABLES `user_fcm_token` WRITE;
/*!40000 ALTER TABLE `user_fcm_token` DISABLE KEYS */;
INSERT INTO `user_fcm_token` VALUES (1,'testuser1','f7VsWBnLR1O8PLD_TY-KrG:APA91bHYtyT_0C9CTEcuvTvakrkU2avRPJzDug2Wv7mm6fQgK6Ro05F9-5Go7CpEV8sHehjTWjg147UUfLejK49cukXzLQ4LWikzf0vRmNZc0u5hq6T3kf0','2025-12-22 19:23:59','2025-12-24 09:36:28');
/*!40000 ALTER TABLE `user_fcm_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-24 10:05:48
