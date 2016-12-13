-- MySQL dump 10.13  Distrib 5.7.16, for osx10.11 (x86_64)
--
-- Host: localhost    Database: ocms
-- ------------------------------------------------------
-- Server version	5.7.16

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
-- Table structure for table `checkitem`
--

DROP TABLE IF EXISTS `checkitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checkitem` (
  `checkitem_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(100) DEFAULT NULL COMMENT '項目内容',
  `related_item_id` int(11) DEFAULT NULL COMMENT '関連項目\n',
  `related_goal_id` int(11) DEFAULT NULL COMMENT '関連ゴール\n',
  `description` varchar(200) DEFAULT NULL COMMENT '補足説明：\nガイド的な情報を付与\n',
  `typical_deliverables` varchar(60) DEFAULT NULL COMMENT '典型的な成果物：\n成果物の例',
  PRIMARY KEY (`checkitem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkitem`
--

LOCK TABLES `checkitem` WRITE;
/*!40000 ALTER TABLE `checkitem` DISABLE KEYS */;
INSERT INTO `checkitem` VALUES (1,'ソフトウェアによる解決策によって対応できる課題が識別されている',NULL,NULL,NULL,NULL),(2,'ソフトウェアによる解決策のニースが裏付けれている',NULL,NULL,NULL,NULL),(3,'ステークホルダーが識別されている',NULL,NULL,NULL,NULL),(4,'ステークホルダーが任命されている',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `checkitem` ENABLE KEYS */;
UNLOCK TABLES;

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
  `checkitem_id` int(10) unsigned DEFAULT NULL,
  `item_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`checkitem_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkitem_status`
--

LOCK TABLES `checkitem_status` WRITE;
/*!40000 ALTER TABLE `checkitem_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `checkitem_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checklist`
--

DROP TABLE IF EXISTS `checklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checklist` (
  `checklist_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT 'チェックリスト名\n',
  `percentage` double DEFAULT NULL COMMENT '得点',
  `project_id` int(10) unsigned DEFAULT NULL COMMENT '外部キー：\n引用表：project\n外部キー名：fk_checklist_project\n外部キー制限：On update cascade, On delete cascade',
  PRIMARY KEY (`checklist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checklist`
--

LOCK TABLES `checklist` WRITE;
/*!40000 ALTER TABLE `checklist` DISABLE KEYS */;
/*!40000 ALTER TABLE `checklist` ENABLE KEYS */;
UNLOCK TABLES;

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
  `parent_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (8,'顧客',NULL,NULL,NULL),(9,'機会',NULL,NULL,8),(10,'課題の識別',NULL,NULL,9),(11,'解決策の方向付け',NULL,NULL,9),(12,'ステークホルダー',NULL,NULL,8),(13,'関係者の特定',NULL,NULL,12),(14,'代表者の選定',NULL,NULL,12);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `project_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `prj_id` int(10) unsigned DEFAULT NULL COMMENT 'プロジェクトID',
  `name` varchar(30) NOT NULL COMMENT 'プロジェクト名',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-13 19:52:25
