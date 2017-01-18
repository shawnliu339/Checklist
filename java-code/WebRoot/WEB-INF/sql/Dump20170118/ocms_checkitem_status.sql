-- MySQL dump 10.13  Distrib 5.7.16, for Win64 (x86_64)
--
-- Host: localhost    Database: ocms
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `checkitem_status`
--

DROP TABLE IF EXISTS `checkitem_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checkitem_status` (
  `checkitem_status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `status` int(11) DEFAULT NULL COMMENT '状態\n０：NO　１：YES',
  `comment` varchar(200) DEFAULT NULL COMMENT 'コメント\nユーザが自由にコメント',
  `deliverables` varchar(100) DEFAULT NULL COMMENT 'コメントの根拠となる成果物',
  `prj_type` int(11) DEFAULT NULL COMMENT 'プロジェクトタイプ\n',
  `importance` int(11) DEFAULT NULL COMMENT '重要度',
  `history` datetime DEFAULT NULL COMMENT '変更履歴',
  `problem` int(11) DEFAULT NULL COMMENT '不遵守事項有無\n',
  `checkitem_id` int(10) unsigned NOT NULL,
  `item_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`checkitem_status_id`),
  KEY `fk_chekitem_statuses_checkitem_idx` (`checkitem_id`),
  KEY `fk_checkitem_status_item_idx` (`item_id`),
  CONSTRAINT `fk_checkitem_status_checkitem` FOREIGN KEY (`checkitem_id`) REFERENCES `checkitem` (`checkitem_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_checkitem_status_item` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8 COMMENT='\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkitem_status`
--

LOCK TABLES `checkitem_status` WRITE;
/*!40000 ALTER TABLE `checkitem_status` DISABLE KEYS */;
INSERT INTO `checkitem_status` VALUES (45,4,'',NULL,1,1,NULL,1,1,24),(46,5,'',NULL,1,1,NULL,1,2,24),(47,3,'',NULL,1,1,NULL,1,3,25),(48,5,'',NULL,1,1,NULL,0,4,25),(49,1,'',NULL,1,1,NULL,1,5,26),(50,1,'',NULL,1,1,NULL,0,6,26),(51,1,'',NULL,1,1,NULL,0,7,27),(54,1,'',NULL,1,1,NULL,0,8,27),(55,5,'',NULL,1,1,NULL,1,9,24),(56,5,'',NULL,1,1,NULL,0,10,25),(57,3,'',NULL,1,1,NULL,0,11,25),(58,1,'',NULL,1,1,NULL,0,12,31),(59,1,'',NULL,1,1,NULL,0,13,31),(60,1,'',NULL,1,1,NULL,0,14,31),(61,1,'',NULL,1,1,NULL,0,15,32),(62,1,'',NULL,1,1,NULL,0,16,32),(63,1,'',NULL,1,1,NULL,0,17,32),(64,1,'',NULL,1,1,NULL,0,18,33),(65,1,'',NULL,1,1,NULL,0,19,33),(66,1,'',NULL,1,1,NULL,0,20,33),(67,1,'',NULL,1,1,NULL,0,21,33),(68,1,'',NULL,1,1,NULL,0,22,34),(69,1,'',NULL,1,1,NULL,0,23,34),(70,1,'',NULL,1,1,NULL,0,24,26),(71,1,'',NULL,1,1,NULL,0,25,27),(72,1,'',NULL,1,1,NULL,0,26,27),(73,1,'',NULL,1,1,NULL,0,27,35),(74,1,'',NULL,1,1,NULL,0,28,35),(75,1,'',NULL,1,1,NULL,0,29,35),(76,1,'',NULL,1,1,NULL,0,30,36),(77,1,'',NULL,1,1,NULL,0,31,36),(78,1,'',NULL,1,1,NULL,0,32,36),(79,1,'',NULL,1,1,NULL,0,33,37),(80,1,'',NULL,1,1,NULL,0,34,37),(81,1,'',NULL,1,1,NULL,0,35,38),(82,1,'',NULL,1,1,NULL,0,36,38);
/*!40000 ALTER TABLE `checkitem_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-18 13:34:04
