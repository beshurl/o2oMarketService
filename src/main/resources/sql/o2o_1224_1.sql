-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: o2omarket
-- ------------------------------------------------------
-- Server version	8.0.43

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
  `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
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
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `product_id` int NOT NULL,
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `rating` float NOT NULL,
  PRIMARY KEY (`comment_id`),
  UNIQUE KEY `uk_user_product` (`user_id`,`product_id`),
  KEY `fk_comment_product` (`product_id`),
  CONSTRAINT `fk_comment_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=397 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'김싸피',1,'삼겹살 두께가 적당해서 구우면 육즙이 잘 살아나요.',4.8),(2,'박싸피',1,'기름이 생각보다 많긴 했는데 맛은 좋았어요.',4.3),(3,'이싸피',1,'잡내 거의 없고 구이용으로 딱입니다.',4.7),(4,'최싸피',1,'포장 깔끔. 다음엔 할인 때 더 사둘 듯!',4.6),(5,'정싸피',1,'양이 살짝 아쉽지만 품질은 만족.',4.4),(6,'강싸피',1,'에어프라이어로 구워도 괜찮네요.',4.5),(7,'박싸피',2,'상추가 아삭하고 싱싱해서 쌈 싸먹기 좋아요.',4.7),(8,'이싸피',2,'잎이 조금 찢어진 게 몇 장 있었어요.',4.1),(9,'최싸피',2,'물기만 잘 빼면 상태 괜찮습니다.',4.4),(10,'정싸피',2,'양은 적당하고 신선도 좋네요.',4.6),(11,'강싸피',2,'쌈용으로 무난! 재구매 가능.',4.5),(12,'조싸피',2,'샐러드로 먹었는데 식감이 좋아요.',4.8),(13,'이싸피',3,'아이들 간식으로 인기 폭발이네요.',4.8),(14,'최싸피',3,'달긴 한데 한 번 먹으면 멈추기 힘듦.',4.6),(15,'정싸피',3,'초코가 꽤 진해서 좋았어요.',4.7),(16,'강싸피',3,'포장 단위가 편해서 간식용 굿.',4.5),(17,'조싸피',3,'바삭함은 좋은데 좀 부스러짐이 많았어요.',4.2),(18,'윤싸피',3,'우유랑 같이 먹으면 꿀조합!',4.9),(19,'최싸피',4,'비린내 거의 없고 구워 먹기 좋아요.',4.7),(20,'정싸피',4,'살이 도톰해서 만족했습니다.',4.8),(21,'강싸피',4,'간이 살짝 강한 느낌? 그래도 맛있음.',4.3),(22,'조싸피',4,'손질 상태가 깔끔해서 조리 편했어요.',4.6),(23,'윤싸피',4,'냄새 걱정했는데 괜찮았어요.',4.5),(24,'장싸피',4,'가격 대비 괜찮은 편.',4.4),(25,'정싸피',5,'국물에 밥 비벼 먹기 딱 좋아요.',4.6),(26,'강싸피',5,'비상식량 느낌으로 두면 든든.',4.5),(27,'조싸피',5,'간이 조금 센 편이라 야채랑 같이 먹었어요.',4.2),(28,'윤싸피',5,'꽁치 살이 부드럽네요.',4.7),(29,'장싸피',5,'가성비 괜찮고 맛 무난!',4.4),(30,'한싸피',5,'김치 넣고 조리하면 찐맛.',4.8),(31,'강싸피',6,'구워 먹으니 담백하고 맛있어요.',4.6),(32,'조싸피',6,'살이 조금 얇은 편이었어요.',4.1),(33,'윤싸피',6,'비린내 거의 없고 깔끔.',4.5),(34,'장싸피',6,'간장 찍어 먹으면 괜찮아요.',4.3),(35,'한싸피',6,'에어프라이어로도 잘 됩니다.',4.4),(36,'김싸피',6,'가격 생각하면 만족!',4.2),(37,'조싸피',7,'갈치가 부드럽고 구이로 좋네요.',4.7),(38,'윤싸피',7,'조금 작긴 했지만 맛은 확실.',4.4),(39,'장싸피',7,'비린내 없어서 만족.',4.6),(40,'한싸피',7,'가격대비 무난한 편.',4.3),(41,'김싸피',7,'조리해보니 살이 잘 발라져요.',4.5),(42,'박싸피',7,'부서짐 없이 상태 좋았습니다.',4.6),(43,'윤싸피',8,'향이 진해서 고기랑 먹기 좋아요.',4.6),(44,'장싸피',8,'잎 크기가 조금 들쭉날쭉했어요.',4),(45,'한싸피',8,'쌈/전 부치기 다 무난.',4.3),(46,'김싸피',8,'신선하고 향 괜찮습니다.',4.4),(47,'박싸피',8,'세척하면 바로 먹을 수 있어서 편해요.',4.5),(48,'이싸피',8,'양도 적당하고 상태 좋아요.',4.6),(49,'장싸피',9,'요리할 때 손질 필요 없어서 편해요.',4.6),(50,'한싸피',9,'향은 좋았는데 몇 개는 조금 물렁했어요.',4.1),(51,'김싸피',9,'볶음 요리에 넣기 딱입니다.',4.4),(52,'박싸피',9,'소분해서 냉동해두면 오래 써요.',4.5),(53,'이싸피',9,'가격 대비 무난합니다.',4.3),(54,'최싸피',9,'마늘 상태 전반적으로 괜찮아요.',4.4),(55,'한싸피',10,'국 끓이면 시원해요.',4.5),(56,'김싸피',10,'아삭함은 좋은데 양이 조금 적었어요.',4.1),(57,'박싸피',10,'무침해 먹기 딱 괜찮습니다.',4.4),(58,'이싸피',10,'물에 씻으니 잡내 거의 없네요.',4.3),(59,'최싸피',10,'가성비 무난!',4.2),(60,'정싸피',10,'상태 좋아서 재구매 할 듯.',4.6),(61,'김싸피',11,'치즈향 꽤 강해서 맥주 안주로 좋네요.',4.6),(62,'박싸피',11,'조금 짠 편이긴 해요.',4.2),(63,'이싸피',11,'바삭하고 맛있습니다.',4.5),(64,'최싸피',11,'양은 아쉽지만 맛은 만족.',4.3),(65,'정싸피',11,'간식으로 무난해요.',4.4),(66,'강싸피',11,'치즈 좋아하면 추천!',4.7),(67,'박싸피',12,'초코가 달달해서 간식으로 좋아요.',4.5),(68,'이싸피',12,'예전 맛이랑 비슷해서 추억 돋음.',4.6),(69,'최싸피',12,'조금 부스러지는 건 아쉬움.',4.1),(70,'정싸피',12,'커피랑 같이 먹으면 괜찮아요.',4.3),(71,'강싸피',12,'무난무난!',4.2),(72,'조싸피',12,'아이들이 좋아합니다.',4.7),(73,'이싸피',13,'바삭함 좋고 간이 적당해요.',4.5),(74,'최싸피',13,'조금 짠 편이라 물이 당김.',4.1),(75,'정싸피',13,'봉지 뜯으면 순삭.',4.6),(76,'강싸피',13,'가성비 좋아요.',4.3),(77,'조싸피',13,'양도 무난합니다.',4.4),(78,'윤싸피',13,'간식으로 딱!',4.7),(79,'최싸피',14,'바삭하고 기름쩐내 없어서 좋았어요.',4.6),(80,'정싸피',14,'양은 적지만 맛은 확실.',4.4),(81,'강싸피',14,'조금 짠 느낌 있어요.',4.1),(82,'조싸피',14,'맥주 안주로 최고.',4.7),(83,'윤싸피',14,'무난하게 맛있습니다.',4.5),(84,'장싸피',14,'가격 대비 괜찮네요.',4.3),(85,'정싸피',15,'당도 괜찮고 껍질도 얇아요.',4.6),(86,'강싸피',15,'몇 개는 살짝 무른 게 있었어요.',4),(87,'조싸피',15,'전체적으로 신선했어요.',4.4),(88,'윤싸피',15,'아이들이 잘 먹네요.',4.5),(89,'장싸피',15,'달달해서 만족!',4.7),(90,'한싸피',15,'양 많아서 가성비 좋음.',4.5),(91,'강싸피',16,'향 좋고 달콤했습니다.',4.7),(92,'조싸피',16,'몇 개가 눌려있어서 아쉬움.',4),(93,'윤싸피',16,'우유랑 먹으니 최고!',4.6),(94,'장싸피',16,'당도 무난하고 신선해요.',4.4),(95,'한싸피',16,'가격 생각하면 괜찮아요.',4.2),(96,'김싸피',16,'크기 일정하고 맛 좋네요.',4.5),(97,'조싸피',17,'숙성 잘 되면 달달해요.',4.5),(98,'윤싸피',17,'처음엔 덜 익어서 하루 더 뒀어요.',4.1),(99,'장싸피',17,'양 많아서 좋아요.',4.4),(100,'한싸피',17,'아침 대용으로 굿.',4.6),(101,'김싸피',17,'가성비 괜찮습니다.',4.3),(102,'박싸피',17,'무난하게 만족!',4.4),(103,'윤싸피',18,'아삭하고 당도 괜찮아요.',4.6),(104,'장싸피',18,'크기가 조금 들쭉날쭉했어요.',4.1),(105,'한싸피',18,'과즙 많고 맛있습니다.',4.5),(106,'김싸피',18,'껍질 상태도 좋아요.',4.4),(107,'박싸피',18,'가성비 무난.',4.2),(108,'이싸피',18,'샐러드용으로도 좋아요.',4.3),(109,'장싸피',19,'에어프라이어 돌리면 편해요.',4.5),(110,'한싸피',19,'간이 조금 세서 소스 없이도 됨.',4.2),(111,'김싸피',19,'도톰해서 식감 좋네요.',4.6),(112,'박싸피',19,'아이 반찬으로 무난.',4.3),(113,'이싸피',19,'조리 간편해서 만족.',4.4),(114,'최싸피',19,'기름에 부치면 더 맛있어요.',4.7),(115,'한싸피',20,'데리야끼 맛이 꽤 진해서 맛있어요.',4.6),(116,'김싸피',20,'숯불향 살짝 나서 만족.',4.5),(117,'박싸피',20,'조금 달긴 한데 간식으로 좋음.',4.2),(118,'이싸피',20,'해동만 잘 하면 퀄리티 괜찮아요.',4.4),(119,'최싸피',20,'양이 많아서 가성비 좋음.',4.6),(120,'정싸피',20,'술안주로 딱입니다.',4.7),(121,'김싸피',21,'속 꽉 차고 맛 괜찮아요.',4.5),(122,'박싸피',21,'조리하면 겉이 바삭해져서 좋음.',4.6),(123,'이싸피',21,'간이 조금 강해서 간장 덜 찍어야 해요.',4.2),(124,'최싸피',21,'양 많아서 냉동실에 쟁여두기 좋음.',4.4),(125,'정싸피',21,'에어프라이어로도 잘 됩니다.',4.3),(126,'강싸피',21,'무난하게 만족!',4.4),(127,'박싸피',22,'한입 크기라 도시락 반찬으로 좋아요.',4.5),(128,'이싸피',22,'살짝 달달한 맛이네요.',4.2),(129,'최싸피',22,'조리 간편해서 자주 먹을 듯.',4.4),(130,'정싸피',22,'식감 괜찮고 양도 무난.',4.3),(131,'강싸피',22,'가격 대비 만족.',4.2),(132,'조싸피',22,'아이들이 잘 먹어요.',4.7),(133,'이싸피',23,'버터향 좋아요. 커피랑 찰떡.',4.6),(134,'최싸피',23,'조금 달긴 하지만 맛있네요.',4.3),(135,'정싸피',23,'겉바속촉 느낌 좋아요.',4.5),(136,'강싸피',23,'가격 생각하면 무난.',4.2),(137,'조싸피',23,'간식으로 괜찮습니다.',4.4),(138,'윤싸피',23,'전자레인지 살짝 돌리면 더 맛있어요.',4.7),(139,'최싸피',24,'부드럽고 촉촉해서 토스트하기 좋네요.',4.5),(140,'정싸피',24,'유통기한 짧아서 빨리 먹어야 해요.',4.1),(141,'강싸피',24,'우유향 은은해서 만족.',4.4),(142,'조싸피',24,'샌드위치 만들기 좋아요.',4.3),(143,'윤싸피',24,'무난한 식빵입니다.',4.2),(144,'장싸피',24,'가격 대비 괜찮아요.',4.3),(145,'정싸피',25,'치즈맛 진하고 부드러워요.',4.7),(146,'강싸피',25,'단맛이 조금 강한 편.',4.2),(147,'조싸피',25,'커피랑 먹으면 딱입니다.',4.6),(148,'윤싸피',25,'식감 촉촉해서 좋아요.',4.5),(149,'장싸피',25,'양이 많아서 나눠 먹기 좋음.',4.4),(150,'한싸피',25,'재구매 의사 있어요.',4.6),(151,'강싸피',26,'블루베리 향 은은하고 쫀득해요.',4.6),(152,'조싸피',26,'조금 퍽퍽한 느낌 있어요(살짝 데우면 괜찮).',4.1),(153,'윤싸피',26,'크림치즈 바르면 맛있어요.',4.5),(154,'장싸피',26,'아침대용으로 무난.',4.3),(155,'한싸피',26,'가격 대비 괜찮네요.',4.2),(156,'김싸피',26,'식감이 좋아요.',4.4),(157,'조싸피',27,'가격은 비싸지만 퀄리티는 괜찮아요.',4.2),(158,'윤싸피',27,'용도가 애매했는데 써보니 나쁘지 않음.',4),(159,'장싸피',27,'설명대로 무난합니다.',4.1),(160,'한싸피',27,'생각보다 괜찮았어요.',4.3),(161,'김싸피',27,'기대만큼은 아니지만 쓸만해요.',4),(162,'박싸피',27,'특가 아니면 재구매는 고민.',4),(163,'윤싸피',28,'잡내 없고 구워 먹기 좋아요.',4.6),(164,'장싸피',28,'기름이 조금 있었지만 맛은 OK.',4.2),(165,'한싸피',28,'양념 없이도 담백하네요.',4.4),(166,'김싸피',28,'식감 좋고 촉촉해요.',4.5),(167,'박싸피',28,'가성비 괜찮습니다.',4.3),(168,'이싸피',28,'재구매 할 듯!',4.6),(169,'장싸피',29,'매콤달콤해서 밥도둑이에요.',4.6),(170,'한싸피',29,'매운맛이 생각보다 강했어요.',4.1),(171,'김싸피',29,'양 많아서 가족용으로 좋음.',4.4),(172,'박싸피',29,'야채 추가해서 볶으니 딱 좋네요.',4.5),(173,'이싸피',29,'간편하게 한 끼 해결!',4.3),(174,'최싸피',29,'재구매 의사 있어요.',4.6),(175,'한싸피',30,'양념이 달달해서 아이들이 좋아해요.',4.4),(176,'김싸피',30,'고기 질감 괜찮고 조리 편함.',4.5),(177,'박싸피',30,'조금 단 편이라 채소랑 같이 먹었어요.',4.1),(178,'이싸피',30,'가성비 무난.',4.2),(179,'최싸피',30,'당면 넣고 하면 더 맛있어요.',4.6),(180,'정싸피',30,'전체적으로 만족!',4.4),(181,'김싸피',31,'완전 만족!',5),(182,'박싸피',31,'신선하고 좋아요',4.5),(183,'이싸피',31,'가성비 좋네요',4),(184,'최싸피',31,'재구매 의사 있어요',4.5),(185,'정싸피',31,'추천합니다',4.6),(186,'강싸피',31,'품질 최고!',4.9),(187,'박싸피',32,'완전 만족!',5),(188,'이싸피',32,'신선하고 좋아요',4.8),(189,'최싸피',32,'가성비 좋네요',4.7),(190,'정싸피',32,'재구매 의사 있어요',4.5),(191,'강싸피',32,'추천합니다',4.6),(192,'조싸피',32,'품질 최고!',4.9),(193,'이싸피',33,'완전 만족!',5),(194,'최싸피',33,'신선하고 좋아요',4.8),(195,'정싸피',33,'가성비 좋네요',4.7),(196,'강싸피',33,'재구매 의사 있어요',4.5),(197,'조싸피',33,'추천합니다',4.6),(198,'윤싸피',33,'품질 최고!',4.9),(199,'최싸피',34,'완전 만족!',5),(200,'정싸피',34,'신선하고 좋아요',4.8),(201,'강싸피',34,'가성비 좋네요',4.7),(202,'조싸피',34,'재구매 의사 있어요',4.5),(203,'윤싸피',34,'추천합니다',4.6),(204,'장싸피',34,'품질 최고!',4.9),(205,'정싸피',35,'완전 만족!',5),(206,'강싸피',35,'신선하고 좋아요',4.8),(207,'조싸피',35,'가성비 좋네요',4.7),(208,'윤싸피',35,'재구매 의사 있어요',4.5),(209,'장싸피',35,'추천합니다',4.6),(210,'한싸피',35,'품질 최고!',4.9),(211,'강싸피',36,'완전 만족!',5),(212,'조싸피',36,'신선하고 좋아요',4.8),(213,'윤싸피',36,'가성비 좋네요',4.7),(214,'장싸피',36,'재구매 의사 있어요',4.5),(215,'한싸피',36,'추천합니다',4.6),(216,'김싸피',36,'품질 최고!',4.9),(217,'조싸피',37,'완전 만족!',5),(218,'윤싸피',37,'신선하고 좋아요',4.8),(219,'장싸피',37,'가성비 좋네요',4.7),(220,'한싸피',37,'재구매 의사 있어요',4.5),(221,'김싸피',37,'추천합니다',4.6),(222,'박싸피',37,'품질 최고!',4.9),(223,'윤싸피',38,'완전 만족!',5),(224,'장싸피',38,'신선하고 좋아요',4.8),(225,'한싸피',38,'가성비 좋네요',4.7),(226,'김싸피',38,'재구매 의사 있어요',4.5),(227,'박싸피',38,'추천합니다',4.6),(228,'이싸피',38,'품질 최고!',4.9),(229,'장싸피',39,'완전 만족!',5),(230,'한싸피',39,'신선하고 좋아요',4.8),(231,'김싸피',39,'가성비 좋네요',4.7),(232,'박싸피',39,'재구매 의사 있어요',4.2),(233,'이싸피',39,'추천합니다',4.6),(234,'최싸피',39,'품질 최고!',4.9),(235,'한싸피',40,'완전 만족!',5),(236,'김싸피',40,'신선하고 좋아요',4.8),(237,'박싸피',40,'가성비 좋네요',4.7),(238,'이싸피',40,'재구매 의사 있어요',4.5),(239,'최싸피',40,'추천합니다',4.6),(240,'정싸피',40,'품질 최고!',4.9),(241,'김싸피',41,'완전 만족!',5),(242,'박싸피',41,'신선하고 좋아요',4.8),(243,'이싸피',41,'가성비 좋네요',4.7),(244,'최싸피',41,'재구매 의사 있어요',4.5),(245,'정싸피',41,'추천합니다',4.6),(246,'강싸피',41,'품질 최고!',4.9),(247,'박싸피',42,'완전 만족!',5),(248,'이싸피',42,'신선하고 좋아요',4.8),(249,'최싸피',42,'가성비 좋네요',4.7),(250,'정싸피',42,'재구매 의사 있어요',4.5),(251,'강싸피',42,'추천합니다',4.6),(252,'조싸피',42,'품질 최고!',4.9),(253,'이싸피',43,'완전 만족!',5),(254,'최싸피',43,'신선하고 좋아요',4.8),(255,'정싸피',43,'가성비 좋네요',4.7),(256,'강싸피',43,'재구매 의사 있어요',4.5),(257,'조싸피',43,'추천합니다',4.6),(258,'윤싸피',43,'품질 최고!',4.9),(259,'최싸피',44,'완전 만족!',5),(260,'정싸피',44,'신선하고 좋아요',4.8),(261,'강싸피',44,'가성비 좋네요',4.7),(262,'조싸피',44,'재구매 의사 있어요',4.5),(263,'윤싸피',44,'추천합니다',4.6),(264,'장싸피',44,'품질 최고!',4.9),(265,'정싸피',45,'완전 만족!',5),(266,'강싸피',45,'신선하고 좋아요',4.8),(267,'조싸피',45,'가성비 좋네요',4.7),(268,'윤싸피',45,'재구매 의사 있어요',4.5),(269,'장싸피',45,'추천합니다',4.6),(270,'한싸피',45,'품질 최고!',4.9),(271,'강싸피',46,'완전 만족!',5),(272,'조싸피',46,'신선하고 좋아요',4.8),(273,'윤싸피',46,'가성비 좋네요',4.7),(274,'장싸피',46,'재구매 의사 있어요',4.5),(275,'한싸피',46,'추천합니다',4.6),(276,'김싸피',46,'품질 최고!',4.9),(277,'조싸피',47,'완전 만족!',5),(278,'윤싸피',47,'신선하고 좋아요',4.8),(279,'장싸피',47,'가성비 좋네요',4.7),(280,'한싸피',47,'재구매 의사 있어요',4.5),(281,'김싸피',47,'추천합니다',4.6),(282,'박싸피',47,'품질 최고!',4.9),(283,'윤싸피',48,'완전 만족!',5),(284,'장싸피',48,'신선하고 좋아요',4.8),(285,'한싸피',48,'가성비 좋네요',4.7),(286,'김싸피',48,'재구매 의사 있어요',4.5),(287,'박싸피',48,'추천합니다',4.6),(288,'이싸피',48,'품질 최고!',4.9),(289,'장싸피',49,'완전 만족!',5),(290,'한싸피',49,'신선하고 좋아요',4.8),(291,'김싸피',49,'가성비 좋네요',4.3),(292,'박싸피',49,'재구매 의사 있어요',4.5),(293,'이싸피',49,'추천합니다',4.6),(294,'최싸피',49,'품질 최고!',4.9),(295,'한싸피',50,'완전 만족!',5),(296,'김싸피',50,'신선하고 좋아요',4.5),(297,'박싸피',50,'가성비 좋네요',4.7),(298,'이싸피',50,'재구매 의사 있어요',4.5),(299,'최싸피',50,'추천합니다',4.6),(300,'정싸피',50,'품질 최고!',4.9),(301,'김싸피',51,'완전 만족!',5),(302,'박싸피',51,'신선하고 좋아요',4.8),(303,'이싸피',51,'가성비 좋네요',4.3),(304,'최싸피',51,'재구매 의사 있어요',4.5),(305,'정싸피',51,'추천합니다',4.6),(306,'강싸피',51,'품질 최고!',4.9),(307,'박싸피',52,'완전 만족!',5),(308,'이싸피',52,'신선하고 좋아요',4.5),(309,'최싸피',52,'가성비 좋네요',4.7),(310,'정싸피',52,'재구매 의사 있어요',4.5),(311,'강싸피',52,'추천합니다',4.6),(312,'조싸피',52,'품질 최고!',4.9),(313,'이싸피',53,'완전 만족!',5),(314,'최싸피',53,'신선하고 좋아요',4.1),(315,'정싸피',53,'가성비 좋네요',4.7),(316,'강싸피',53,'재구매 의사 있어요',4.5),(317,'조싸피',53,'추천합니다',4.6),(318,'윤싸피',53,'품질 최고!',4.9),(319,'최싸피',54,'완전 만족!',5),(320,'정싸피',54,'신선하고 좋아요',4.1),(321,'강싸피',54,'가성비 좋네요',4.7),(322,'조싸피',54,'재구매 의사 있어요',4.5),(323,'윤싸피',54,'추천합니다',4.6),(324,'장싸피',54,'품질 최고!',4.9),(325,'정싸피',55,'완전 만족!',5),(326,'강싸피',55,'신선하고 좋아요',4.3),(327,'조싸피',55,'가성비 좋네요',4.7),(328,'윤싸피',55,'재구매 의사 있어요',4.5),(329,'장싸피',55,'추천합니다',4.6),(330,'한싸피',55,'품질 최고!',4.9),(331,'강싸피',56,'완전 만족!',5),(332,'조싸피',56,'신선하고 좋아요',4.8),(333,'윤싸피',56,'가성비 좋네요',4.7),(334,'장싸피',56,'재구매 의사 있어요',4.5),(335,'한싸피',56,'추천합니다',4.5),(336,'김싸피',56,'품질 최고!',4.9),(337,'조싸피',57,'완전 만족!',5),(338,'윤싸피',57,'신선하고 좋아요',4.8),(339,'장싸피',57,'가성비 좋네요',4.7),(340,'한싸피',57,'재구매 의사 있어요',4.5),(341,'김싸피',57,'추천합니다',4.6),(342,'박싸피',57,'품질 최고!',4.9),(343,'윤싸피',58,'완전 만족!',5),(344,'장싸피',58,'신선하고 좋아요',4.3),(345,'한싸피',58,'가성비 좋네요',4.7),(346,'김싸피',58,'재구매 의사 있어요',4.5),(347,'박싸피',58,'추천합니다',4.6),(348,'이싸피',58,'품질 최고!',4.9),(349,'장싸피',59,'완전 만족!',5),(350,'한싸피',59,'신선하고 좋아요',4.8),(351,'김싸피',59,'가성비 좋네요',4.2),(352,'박싸피',59,'재구매 의사 있어요',4.5),(353,'이싸피',59,'추천합니다',4.6),(354,'최싸피',59,'품질 최고!',4.9),(355,'한싸피',60,'완전 만족!',5),(356,'김싸피',60,'신선하고 좋아요',4.8),(357,'박싸피',60,'가성비 좋네요',4.7),(358,'이싸피',60,'재구매 의사 있어요',4.5),(359,'최싸피',60,'추천합니다',4.2),(360,'정싸피',60,'품질 최고!',4.9),(361,'김싸피',61,'완전 만족!',5),(362,'박싸피',61,'신선하고 좋아요',4.8),(363,'이싸피',61,'가성비 좋네요',4.7),(364,'최싸피',61,'재구매 의사 있어요',4.5),(365,'정싸피',61,'추천합니다',4.6),(366,'강싸피',61,'품질 최고!',4.9),(367,'박싸피',62,'완전 만족!',5),(368,'이싸피',62,'신선하고 좋아요',4.8),(369,'최싸피',62,'가성비 좋네요',4.3),(370,'정싸피',62,'재구매 의사 있어요',4.5),(371,'강싸피',62,'추천합니다',4.6),(372,'조싸피',62,'품질 최고!',4.9),(373,'이싸피',63,'완전 만족!',5),(374,'최싸피',63,'신선하고 좋아요',4.8),(375,'정싸피',63,'가성비 좋네요',4.7),(376,'강싸피',63,'재구매 의사 있어요',4.5),(377,'조싸피',63,'추천합니다',4.6),(378,'윤싸피',63,'품질 최고!',4.9),(379,'최싸피',64,'완전 만족!',5),(380,'정싸피',64,'신선하고 좋아요',4.8),(381,'강싸피',64,'가성비 좋네요',4.7),(382,'조싸피',64,'재구매 의사 있어요',4),(383,'윤싸피',64,'추천합니다',4.6),(384,'장싸피',64,'품질 최고!',4.9),(385,'정싸피',65,'완전 만족!',5),(386,'강싸피',65,'신선하고 좋아요',4.3),(387,'조싸피',65,'가성비 좋네요',4.7),(388,'윤싸피',65,'재구매 의사 있어요',4.5),(389,'장싸피',65,'추천합니다',4.6),(390,'한싸피',65,'품질 최고!',4.9),(391,'강싸피',66,'완전 만족!',5),(392,'조싸피',66,'신선하고 좋아요',4.8),(393,'윤싸피',66,'가성비 좋네요',4.2),(394,'장싸피',66,'재구매 의사 있어요',4.5),(395,'한싸피',66,'추천합니다',4.6),(396,'김싸피',66,'품질 최고!',4.9);
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
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `coupon_type` enum('AMOUNT','PERCENT','SHIPPING') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `coupon_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
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
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rate` float DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`discount_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `discount_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,1,'DISCOUNT',20,'2025-12-16 00:00:00','2025-12-31 23:59:59',1),(2,3,'DEADLINE',50,'2025-12-16 18:00:00','2025-12-16 23:59:59',1),(3,31,'DISCOUNT',20,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(4,32,'DISCOUNT',15,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(5,33,'DISCOUNT',25,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(6,34,'DISCOUNT',10,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(7,35,'DISCOUNT',18,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(8,36,'DISCOUNT',22,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(9,37,'DISCOUNT',12,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(10,38,'DISCOUNT',17,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(11,39,'DISCOUNT',20,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(12,40,'DISCOUNT',15,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(13,41,'DISCOUNT',10,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(14,42,'DISCOUNT',25,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(15,43,'DISCOUNT',10,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(16,44,'DISCOUNT',12,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(17,45,'DISCOUNT',15,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(18,46,'DISCOUNT',10,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(19,47,'DISCOUNT',13,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(20,48,'DISCOUNT',18,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(21,49,'DISCOUNT',30,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(22,50,'DISCOUNT',25,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(23,51,'DISCOUNT',20,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(24,52,'DISCOUNT',15,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(25,53,'DISCOUNT',20,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(26,54,'DISCOUNT',18,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(27,55,'DEADLINE',50,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(28,56,'DEADLINE',45,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(29,57,'DEADLINE',55,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(30,58,'DEADLINE',40,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(31,59,'DEADLINE',50,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(32,60,'DEADLINE',45,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(33,61,'DEADLINE',60,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(34,62,'DEADLINE',40,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(35,63,'DEADLINE',50,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(36,64,'DEADLINE',45,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(37,65,'DEADLINE',40,'2025-11-01 00:00:00','2026-01-31 00:00:00',1),(38,66,'DEADLINE',50,'2025-11-01 00:00:00','2026-01-31 00:00:00',1);
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
  `zone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `x_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `y_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`location_code`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `location_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,1,'육류 코너','3','5'),(2,2,'채소 코너','1','2'),(3,3,'과자 코너','4','1'),(4,31,'A','1','1'),(5,32,'A','2','1'),(6,33,'A','3','1'),(7,34,'A','4','1'),(8,35,'A','1','2'),(9,36,'A','2','2'),(10,37,'B','1','1'),(11,38,'B','2','1'),(12,39,'B','3','1'),(13,40,'B','4','1'),(14,41,'B','1','2'),(15,42,'B','2','2'),(16,43,'C','1','1'),(17,44,'C','2','1'),(18,45,'C','3','1'),(19,46,'C','4','1'),(20,47,'C','1','2'),(21,48,'C','2','2'),(22,49,'A','3','2'),(23,50,'A','4','2'),(24,51,'A','1','3'),(25,52,'A','2','3'),(26,53,'A','3','3'),(27,54,'A','4','3'),(28,55,'A','1','4'),(29,56,'A','2','4'),(30,57,'A','3','4'),(31,58,'A','4','4'),(32,59,'B','1','3'),(33,60,'B','2','3'),(34,61,'B','3','3'),(35,62,'B','4','3'),(36,63,'C','1','4'),(37,64,'C','2','4'),(38,65,'C','3','4'),(39,66,'C','4','4');
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
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `order_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `total_price` int DEFAULT NULL,
  `zip_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `product_total` int NOT NULL DEFAULT '0',
  `delivery_fee` int NOT NULL DEFAULT '0',
  `discount` int NOT NULL DEFAULT '0',
  `payment_method` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `carrier` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tracking_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
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
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `point_type` enum('EARN','USE','EXPIRE') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount` int NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
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
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `earned_points` int NOT NULL DEFAULT '0',
  `used_points` int NOT NULL DEFAULT '0',
  `expired_points` int NOT NULL DEFAULT '0',
  `earn_method` enum('PURCHASE','EVENT','ETC') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `expire_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
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
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` int NOT NULL,
  `count` int NOT NULL,
  `sold_count` int NOT NULL DEFAULT '0',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_discount` tinyint(1) DEFAULT '0',
  `is_deadline_sale` tinyint(1) DEFAULT '0',
  `deadline_at` datetime DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'냉장 삼겹살 구이 500g',22800,5,2,'냉장 삼겹살 구이 500g 입니다','/images/product/육류_냉장_삼겹살_구이_500g_22800.avif',0,0,NULL),(2,'상추',2000,20,0,'야채도 먹어야죠','/images/product/채소_상추.avif',0,0,NULL),(3,'홈런볼 초코 5개입',6480,15,6,'홈런볼 초코 5개입 입니다.','/images/product/과자_홈런볼_초코_5개입_6480.avif',0,0,NULL),(4,'고등어',8900,9,2,'신선한 국산 고등어','/images/product/수산물_고등어.webp',0,0,NULL),(5,'꽁치 통조림',4500,25,0,'담백한 꽁치 통조림','/images/product/수산물_꽁치통조림.webp',0,0,NULL),(6,'가자미',4500,10,0,'맛있는 가자미','/images/product/수산물_가자미.jpg',0,0,NULL),(7,'제주 은갈치',6000,15,1,'생선은 역시 갈치','/images/product/수산물_제주은갈치.jpg',0,0,NULL),(8,'깻잎',2000,10,1,'깻잎 입니다.','/images/product/채소_깻잎.avif',0,0,NULL),(9,'깐 마늘',3000,10,1,'깐 마늘입니다.','/images/product/채소_깐마늘.avif',0,0,NULL),(10,'콩나물',2000,15,1,'콩나물 입니다.','/images/product/채소_콩나물.avif',0,0,NULL),(11,'예감 치즈그라탕맛',3780,8,1,'예감 치즈그라탕 입니다.','/images/product/과자_예감_치즈그라탕맛_3780.avif',0,0,NULL),(12,'칸쵸',3980,10,1,'칸쵸 입니다.','/images/product/과자_칸쵸_3980.avif',0,0,NULL),(13,'콘칩',3080,5,1,'콘칩 입니다.','/images/product/과자_콘칩_3080.avif',0,0,NULL),(14,'포카칩',1480,7,1,'포카칩 입니다.','/images/product/과자_포카칩_1480.avif',0,0,NULL),(15,'감귤 3kg',19900,3,1,'감귤 3kg 입니다.','/images/product/과일_감귤_3kg_19900.avif',0,0,NULL),(16,'딸기 350g',20000,4,1,'딸기 350g 입니다.','/images/product/과일_딸기_350g_20000.avif',0,0,NULL),(17,'바나나 1.2kg',5990,5,1,'바나나 1.2kg 입니다.','/images/product/과일_바나나_1.2kg_5990.avif',0,0,NULL),(18,'사과 (8~11개입)',13520,8,1,'사과 (8~11개입)입니다.','/images/product/과일_사과_(8~11개입)_13520.avif',0,0,NULL),(19,'도톰 동그랑땡 795g',9480,8,1,'도톰 동그랑땡 795g 입니다.','/images/product/냉동식품_도톰_동그랑땡_795g_9480.avif',0,0,NULL),(20,'숯불 데리야끼 닭꼬치 800g',14980,8,1,'숯불 데리야끼 닭꼬치 800g 입니다.','/images/product/냉동식품_숯불데리야끼닭꼬치_800g_14980.avif',0,0,NULL),(21,'왕교자 1.12kg',10980,10,1,'왕교자 1.12kg 입니다.','/images/product/냉동식품_왕교자_1.12kg_10980.avif',0,0,NULL),(22,'한입떡갈비 490g',8980,10,1,'한입떡갈비 490g 입니다.','/images/product/냉동식품_한입떡갈비_490g_8980.avif',0,0,NULL),(23,'오븐에 구운 버터 휘낭시에 360g',7980,5,1,'오븐에 구운 버터 휘낭시에 360g 입니다.','/images/product/베이커리_오븐에구운버터휘낭시에_360g_7980.avif',0,0,NULL),(24,'우유식빵 300g',3980,5,1,'우유식빵 300g 입니다.','/images/product/베이커리_우유식빵_300g_3980.avif',0,0,NULL),(25,'우유 치즈 케익 700g',10180,5,1,'우유 치즈 케익 700g 입니다.','/images/product/베이커리_우유치즈케익_700g_10180.avif',0,0,NULL),(26,'탕종베이글 블루베리 450g',5980,5,1,'탕종베이글 블루베리 450g 입니다.','/images/product/베이커리_탕종베이글블루베리_450g_5980.avif',0,0,NULL),(27,'기타',150000,2,1,'기타 입니다.','/images/product/기타_기타.avif',0,0,NULL),(28,'닭다리살 구이 600g',10480,5,1,'닭다리살 구이 600g 입니다.','/images/product/육류_닭다리살_구이_600g_10480.avif',0,0,NULL),(29,'고추장 제육볶음 1000g',10890,5,1,'고추장 제육볶음 1000g 입니다.','/images/product/육류_고추장_제육볶음_1000g_10890.avif',0,0,NULL),(30,'양념 소불고기 600g',10080,3,1,'양념 소불고기 600g 입니다.','/images/product/육류_양념_소불고기_600g_10080.avif',0,0,NULL),(31,'한우 불고기 600g',12900,110,0,'한우 불고기 600g 입니다.','https://sitem.ssgcdn.com/04/62/72/item/1000632726204_i1_1200.jpg',1,0,NULL),(32,'돼지 목살 구이용 500g',9900,95,0,'돼지 목살 구이용 500g 입니다.','https://sitem.ssgcdn.com/22/40/53/item/1000631534022_i1_1200.jpg',1,0,NULL),(33,'닭가슴살 1kg',8900,120,0,'닭가슴살 1kg 입니다.','https://sitem.ssgcdn.com/14/21/88/item/1000632882114_i1_1200.jpg',1,0,NULL),(34,'오리훈제 슬라이스 500g',10900,80,0,'오리훈제 슬라이스 500g 입니다.','https://sitem.ssgcdn.com/41/98/21/item/1000072219841_i1_1200.jpg',1,0,NULL),(35,'소갈비찜용 1kg',21900,70,0,'소갈비찜용 1kg 입니다.','https://sitem.ssgcdn.com/56/25/83/item/1000527832556_i1_1200.jpg',1,0,NULL),(36,'양념 닭갈비 800g',11800,105,0,'양념 닭갈비 800g 입니다.','https://sitem.ssgcdn.com/05/62/74/item/1000648746205_i1_1200.jpg',1,0,NULL),(37,'생연어 필렛 300g',15900,85,0,'생연어 필렛 300g 입니다.','https://sitem.ssgcdn.com/51/61/10/item/1000737106151_i1_1200.jpg',1,0,NULL),(38,'대하 새우 500g',14900,75,0,'대하 새우 500g 입니다.','https://sitem.ssgcdn.com/37/56/08/item/1000031085637_i1_1200.jpg',1,0,NULL),(39,'손질 오징어 400g',7900,100,0,'손질 오징어 400g 입니다.','https://sitem.ssgcdn.com/06/97/29/item/1000709299706_i1_1200.jpg',1,0,NULL),(40,'손질 홍합 1kg',6900,90,0,'손질 홍합 1kg 입니다.','https://sitem.ssgcdn.com/59/98/29/item/1000273299859_i1_1200.jpg',1,0,NULL),(41,'국산 조기 5마리(약 600g)',11900,60,0,'국산 조기 5마리 상품입니다.','https://sitem.ssgcdn.com/94/44/78/item/1000632784494_i1_1200.jpg',1,0,NULL),(42,'참치(회용) 200g',13900,65,0,'회용 참치 200g 입니다.','https://sitem.ssgcdn.com/01/67/33/item/1000614336701_i1_1200.jpg',1,0,NULL),(43,'친환경 시금치 200g',2500,115,0,'친환경 시금치 200g 입니다.','https://sitem.ssgcdn.com/70/34/62/item/1000641623470_i1_1200.jpg',1,0,NULL),(44,'브로콜리 2송이(약 500g)',3500,95,0,'브로콜리 2송이 상품입니다.','https://sitem.ssgcdn.com/74/94/18/item/1000677189474_i1_1200.jpg',1,0,NULL),(45,'파프리카 혼합 3입(약 450g)',4500,80,0,'파프리카 혼합 3입 상품입니다.','https://sitem.ssgcdn.com/54/76/40/item/1000029407654_i1_1200.jpg',1,0,NULL),(46,'애호박 2개(약 500g)',2800,110,0,'애호박 2개 상품입니다.','https://sitem.ssgcdn.com/22/93/33/item/1000631339322_i1_1200.jpg',1,0,NULL),(47,'새송이버섯 400g',3200,90,0,'새송이버섯 400g 입니다.','https://sitem.ssgcdn.com/96/40/83/item/1000632834096_i1_1200.jpg',1,0,NULL),(48,'방울토마토 500g',5900,70,0,'방울토마토 500g 입니다.','https://sitem.ssgcdn.com/06/01/25/item/1000633250106_i1_1200.jpg',1,0,NULL),(49,'감자칩 오리지널 160g',1900,120,0,'감자칩 오리지널 160g 입니다.','/images/product/과자_감자칩.jfif',1,0,NULL),(50,'초코칩쿠키 300g',3200,95,0,'초코칩쿠키 300g 입니다.','/images/product/과자_초코칩쿠키.jfif',1,0,NULL),(51,'젤리믹스 250g',2800,85,0,'젤리믹스 250g 입니다.','/images/product/과자_젤리믹스.jfif',1,0,NULL),(52,'프레첼 200g',2600,75,0,'프레첼 200g 입니다.','/images/product/과자_프레첼.jpg',1,0,NULL),(53,'견과바 12개입(240g)',4900,60,0,'견과바 12개입 상품입니다.','/images/product/과자_견과바.jfif',1,0,NULL),(54,'콘스낵 매콤 180g',2100,105,0,'콘스낵 매콤 180g 입니다.','/images/product/과자_콘스낵매콤.jfif',1,0,NULL),(55,'냉동 소고기 국거리 400g',6900,12,0,'냉동 소고기 국거리 400g 입니다.','/images/product/육류_냉동소고기국거리.jfif',0,1,'2025-12-25 23:59:59'),(56,'돼지 앞다리 수육용 700g',7900,9,0,'돼지 앞다리 수육용 700g 입니다.','/images/product/육류_돼지앞다리수육용.jfif',0,1,'2025-12-25 23:59:59'),(57,'닭봉 1kg',5800,18,0,'닭봉 1kg 입니다.','/images/product/육류_닭봉.jfif',0,1,'2025-12-25 23:59:59'),(58,'햄 스테이크 300g',3900,7,0,'햄 스테이크 300g 입니다.','/images/product/육류_햄스테이크.jpg',0,1,'2025-12-25 23:59:59'),(59,'손질 고등어 필렛 400g',6900,14,0,'손질 고등어 필렛 400g 입니다.','/images/product/수산물_손질고등어필렛.jfif',0,1,'2025-12-25 23:59:59'),(60,'생굴 500g',7900,6,0,'생굴 500g 입니다.','/images/product/수산물_생굴.jfif',0,1,'2025-12-25 23:59:59'),(61,'냉동 문어다리 300g',9900,5,0,'냉동 문어다리 300g 입니다.','/images/product/냉동식품_냉동문어다리.jfif',0,1,'2025-12-25 23:59:59'),(62,'명란젓 200g',8500,11,0,'명란젓 200g 입니다.','/images/product/수산물_명란젓.jfif',0,1,'2025-12-25 23:59:59'),(63,'양배추 1통(약 1kg)',2800,20,0,'양배추 1통 상품입니다.','/images/product/채소_양배추.jpg',0,1,'2025-12-25 23:59:59'),(64,'당근 1kg',3300,16,0,'당근 1kg 입니다.','/images/product/채소_당근.jfif',0,1,'2025-12-25 23:59:59'),(65,'샐러드 믹스 150g',2500,8,0,'샐러드 믹스 150g 입니다.','/images/product/채소_샐러드믹스.jpg',0,1,'2025-12-25 23:59:59'),(66,'대파 500g',2200,13,0,'대파 500g 입니다.','/images/product/채소_대파.jfif',0,1,'2025-12-25 23:59:59');
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
INSERT INTO `product_category` VALUES (1,1),(28,1),(29,1),(30,1),(31,1),(32,1),(33,1),(34,1),(35,1),(36,1),(55,1),(56,1),(57,1),(58,1),(4,2),(5,2),(6,2),(7,2),(37,2),(38,2),(39,2),(40,2),(41,2),(42,2),(59,2),(60,2),(61,2),(62,2),(2,3),(8,3),(9,3),(10,3),(43,3),(44,3),(45,3),(46,3),(47,3),(48,3),(63,3),(64,3),(65,3),(66,3),(3,4),(11,4),(12,4),(13,4),(14,4),(49,4),(50,4),(51,4),(52,4),(53,4),(54,4),(15,5),(16,5),(17,5),(18,5),(19,6),(20,6),(21,6),(22,6),(23,7),(24,7),(25,7),(26,7),(27,8);
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
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
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
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `birth_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `point` int DEFAULT '0',
  `address_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1','1','1','1','1','1','1','1',1,NULL),('testuser1','password123','정준용','hong123@naver.com','서울시 강남구','010-1111-2222','남자','1999.03.01',1664,'111'),('testuser2','password123','김영희','kim123@naver.com','서울시 서초구','010-3333-4444','여자','2004.04.21',500,NULL),('testuser3','password123','이철수','lee123@naver.com','서울시 송파구','010-5555-6666','남자','2007.07.31',0,NULL),('강싸피','password123','강싸피','kangssafy06@naver.com','서울시 강동구','010-1000-0006','여자','2002.06.15',230,NULL),('김싸피','password123','김싸피','kimssafy01@naver.com','서울시 강남구','010-1000-0001','남자','1998.01.10',1200,NULL),('박싸피','password123','박싸피','parkssafy02@naver.com','서울시 서초구','010-1000-0002','여자','1999.02.11',3400,NULL),('윤싸피','password123','윤싸피','yoonssafy08@naver.com','서울시 노원구','010-1000-0008','여자','2003.08.17',4200,NULL),('이싸피','password123','이싸피','leessafy03@naver.com','서울시 송파구','010-1000-0003','남자','2000.03.12',0,NULL),('장싸피','password123','장싸피','jangssafy09@naver.com','서울시 관악구','010-1000-0009','남자','1995.09.18',50,NULL),('정싸피','password123','정싸피','jungssafy05@naver.com','서울시 용산구','010-1000-0005','남자','1997.05.14',15000,'111'),('조싸피','password123','조싸피','jossafy07@naver.com','서울시 성동구','010-1000-0007','남자','1996.07.16',999,NULL),('최싸피','password123','최싸피','choissafy04@naver.com','서울시 마포구','010-1000-0004','여자','2001.04.13',800,NULL),('한싸피','password123','한싸피','hanssafy10@naver.com','서울시 동작구','010-1000-0010','여자','2004.10.19',700,NULL);
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
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fcm_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_fcm_token`
--

LOCK TABLES `user_fcm_token` WRITE;
/*!40000 ALTER TABLE `user_fcm_token` DISABLE KEYS */;
INSERT INTO `user_fcm_token` VALUES (94,'1','fMRrFfl-S8OvXuFAGaSAXl:APA91bE_RSfnYhx4uO2KYHi81uvajjqNW0Wbg_qLa_gYZ7oQkeHSqk2d59zRK3K-t5ZrEf6pBm3Pi5AYeomA2CVZ4KrPIS9Dql9pc_ccJrtdeG65lzxA-vU','2025-12-25 22:37:44','2025-12-25 23:31:08');
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

-- Dump completed on 2025-12-26  0:32:56
