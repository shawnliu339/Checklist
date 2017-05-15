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
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `item_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '項目名',
  `percentage` double DEFAULT NULL COMMENT '得点',
  `checklist_id` int(10) unsigned DEFAULT NULL,
  `parent_id` int(10) unsigned DEFAULT '0',
  `rank` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (21,'顧客',13.333333333333334,1,21,1),(22,'機会',27.666666666666668,1,21,1),(23,'ステークホルダー',0,1,21,2),(24,'課題の識別',100,1,22,1),(25,'解決策の方向付け',66.66666666666666,1,22,2),(26,'関係者の特定',0,1,23,1),(27,'代表者の選定',0,1,23,2),(31,'価値の設定',0,1,22,3),(32,'解決策決定',0,1,22,4),(33,'課題解決',0,1,22,5),(34,'利益獲得',0,1,22,6),(35,'活動の協業',0,1,23,3),(36,'合意形成',0,1,23,4),(37,'展開合意',0,1,23,5),(38,'利益満足',0,1,23,6),(39,'解決策',0,NULL,39,2),(40,'要求',0,NULL,39,1),(41,'構想',0,NULL,40,1),(42,'スコープ定義',0,NULL,40,2),(43,'一貫性・体系化',0,NULL,40,3),(44,'受理可能',0,NULL,40,4),(45,'実装',0,NULL,40,5),(46,'満足',0,NULL,40,6),(47,'ソフトウェアシステム',0,NULL,39,2),(48,'アーキテクチャ決定',0,NULL,47,1),(49,'論証完了',0,NULL,47,2),(50,'使用可能',0,NULL,47,3),(51,'準備可能',0,NULL,47,4),(52,'運用',0,NULL,47,5),(53,'退役',0,NULL,47,6),(54,'取り組み',0,NULL,54,3),(55,'仕事',0,NULL,54,1),(56,'着手',0,NULL,55,1),(57,'準備完了',0,NULL,55,2),(58,'開発開始',0,NULL,55,3),(59,'制御可能',0,NULL,55,4),(60,'完結',0,NULL,55,5),(61,'終了',0,NULL,55,6),(62,'チーム',0,NULL,54,2),(63,'立ち上げ',0,NULL,62,1),(64,'組織化',0,NULL,62,2),(65,'協調',0,NULL,62,3),(66,'最適化',0,NULL,62,4),(67,'休止',0,NULL,62,5),(68,'仕事の仕方',0,NULL,54,3),(69,'原則決定',0,NULL,68,1),(70,'準備完了',0,NULL,68,2),(71,'使用',0,NULL,68,3),(72,'定着',0,NULL,68,4),(73,'活用',0,NULL,68,5),(74,'廃止',0,NULL,68,6);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-15 17:21:41
