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
  `rank` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`checkitem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkitem`
--

LOCK TABLES `checkitem` WRITE;
/*!40000 ALTER TABLE `checkitem` DISABLE KEYS */;
INSERT INTO `checkitem` VALUES (1,'ソフトウェアによる解決策によって対応できる課題が識別されている',NULL,NULL,NULL,NULL,1),(2,'ステークホルダーは潜在的な価値をよく理解して投資したいと願っている',NULL,NULL,NULL,NULL,2),(3,'ソフトウェアによる解決策のニーズが裏付けされている',NULL,NULL,NULL,NULL,1),(4,'ステークホルダーが特定されている',NULL,NULL,NULL,NULL,2),(5,'ステークホルダーが識別されている',NULL,NULL,NULL,NULL,1),(6,'ステークホルダー間で代表者が合意されている',NULL,NULL,NULL,NULL,2),(7,'ステークホルダーが任命されている',NULL,NULL,NULL,NULL,1),(8,'自身の役割と権限について同意されている',NULL,NULL,NULL,NULL,2),(9,'ステークホルダ間で識別された課題が共有されている',NULL,NULL,NULL,NULL,3),(10,'潜在的な問題と本質が判明している',NULL,NULL,NULL,NULL,3),(11,'少なくとも1つのソフトウェアによる解決策が提案されている',NULL,NULL,NULL,NULL,4),(12,'解決策が成功したときの価値が設定されている',NULL,NULL,NULL,NULL,1),(13,'解決策の影響をステークホルダが理解している',NULL,NULL,NULL,NULL,2),(14,'ソフトウェアシステムの価値が理解されている',NULL,NULL,NULL,NULL,3),(15,'解決策の主要な部分が描かれている',NULL,NULL,NULL,NULL,1),(16,'解決策を開発、展開するときの制約が明らかになっている',NULL,NULL,NULL,NULL,2),(17,'リスクが管理下に置かれている',NULL,NULL,NULL,NULL,3),(18,'機会に対応するための論証された解決策が提供されている',NULL,NULL,NULL,NULL,1),(19,'有効なシステムが利用可能である',NULL,NULL,NULL,NULL,2),(20,'ステークホルダは展開する価値に同意している',NULL,NULL,NULL,NULL,3),(21,'ステークホルダは機会の解決策に満足している',NULL,NULL,NULL,NULL,4),(22,'運用で明らかな利益が生み出されている',NULL,NULL,NULL,NULL,1),(23,'少なくとも予想通りの投資効果が得られている',NULL,NULL,NULL,NULL,2),(24,'ステークホルダーの役割が定義されている',NULL,NULL,NULL,NULL,3),(25,'ステークホルダーが協業するやり方が同意されている',NULL,NULL,NULL,NULL,3),(26,'ステークホルダーがチームの仕事を尊重している',NULL,NULL,NULL,NULL,4),(27,'ステークホルダが役割を果たしている',NULL,NULL,NULL,NULL,1),(28,'チームにタイミング良くフィードバックを与えたり、決断の場に参加している',NULL,NULL,NULL,NULL,2),(29,'ステークホルダ間のコミュニケーションが良好である',NULL,NULL,NULL,NULL,3),(30,'ステークホルダにとっての価値が定義され、チームに受け入れられている',NULL,NULL,NULL,NULL,1),(31,'ステークホルダは優先度を同意している',NULL,NULL,NULL,NULL,2),(32,'システムの展開による最小限の期待値について同意している',NULL,NULL,NULL,NULL,3),(33,'ステークホルダの視点でシステムに対するフィードバックを提供している',NULL,NULL,NULL,NULL,1),(34,'システムの展開に対する準備が整ったことを確認できている',NULL,NULL,NULL,NULL,2),(35,'システムはステークホルダの最小限の期待以上である',NULL,NULL,NULL,NULL,1),(36,'ステークホルダのニーズと期待は満足されている',NULL,NULL,NULL,NULL,2);
/*!40000 ALTER TABLE `checkitem` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-19 12:25:28
