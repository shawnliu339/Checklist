-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: ocms
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
  `rank` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`checkitem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkitem`
--

LOCK TABLES `checkitem` WRITE;
/*!40000 ALTER TABLE `checkitem` DISABLE KEYS */;
INSERT INTO `checkitem` VALUES (1,'Opportunity identified that could be addressed by a software-based solution',NULL,NULL,NULL,NULL,1),(2,'A stakeholder wishes to make an investment in better understanding potential value',NULL,NULL,NULL,NULL,2),(3,'ソフトウェアによる解決策のニーズが裏付けされている',NULL,NULL,NULL,NULL,1),(4,'ステークホルダーが特定されている',NULL,NULL,NULL,NULL,2),(5,'ステークホルダーが識別されている',NULL,NULL,NULL,NULL,1),(6,'ステークホルダー間で代表者が合意されている',NULL,NULL,NULL,NULL,2),(7,'ステークホルダーが任命されている',NULL,NULL,NULL,NULL,1),(8,'自身の役割と権限について同意されている',NULL,NULL,NULL,NULL,2),(9,'Other stakeholders who share opportunity identified',NULL,NULL,NULL,NULL,3),(10,'潜在的な問題と本質が判明している',NULL,NULL,NULL,NULL,3),(11,'少なくとも1つのソフトウェアによる解決策が提案されている',NULL,NULL,NULL,NULL,4),(12,'解決策が成功したときの価値が設定されている',NULL,NULL,NULL,NULL,1),(13,'解決策の影響をステークホルダが理解している',NULL,NULL,NULL,NULL,2),(14,'ソフトウェアシステムの価値が理解されている',NULL,NULL,NULL,NULL,3),(15,'解決策の主要な部分が描かれている',NULL,NULL,NULL,NULL,1),(16,'解決策を開発、展開するときの制約が明らかになっている',NULL,NULL,NULL,NULL,2),(17,'リスクが管理下に置かれている',NULL,NULL,NULL,NULL,3),(18,'機会に対応するための論証された解決策が提供されている',NULL,NULL,NULL,NULL,1),(19,'有効なシステムが利用可能である',NULL,NULL,NULL,NULL,2),(20,'ステークホルダは展開する価値に同意している',NULL,NULL,NULL,NULL,3),(21,'ステークホルダは機会の解決策に満足している',NULL,NULL,NULL,NULL,4),(22,'運用で明らかな利益が生み出されている',NULL,NULL,NULL,NULL,1),(23,'少なくとも予想通りの投資効果が得られている',NULL,NULL,NULL,NULL,2),(24,'ステークホルダーの役割が定義されている',NULL,NULL,NULL,NULL,3),(25,'ステークホルダーが協業するやり方が同意されている',NULL,NULL,NULL,NULL,3),(26,'ステークホルダーがチームの仕事を尊重している',NULL,NULL,NULL,NULL,4),(27,'ステークホルダが役割を果たしている',NULL,NULL,NULL,NULL,1),(28,'チームにタイミング良くフィードバックを与えたり、決断の場に参加している',NULL,NULL,NULL,NULL,2),(29,'ステークホルダ間のコミュニケーションが良好である',NULL,NULL,NULL,NULL,3),(30,'ステークホルダにとっての価値が定義され、チームに受け入れられている',NULL,NULL,NULL,NULL,1),(31,'ステークホルダは優先度を同意している',NULL,NULL,NULL,NULL,2),(32,'システムの展開による最小限の期待値について同意している',NULL,NULL,NULL,NULL,3),(33,'ステークホルダの視点でシステムに対するフィードバックを提供している',NULL,NULL,NULL,NULL,1),(34,'システムの展開に対する準備が整ったことを確認できている',NULL,NULL,NULL,NULL,2),(35,'システムはステークホルダの最小限の期待以上である',NULL,NULL,NULL,NULL,1),(36,'ステークホルダのニーズと期待は満足されている',NULL,NULL,NULL,NULL,2),(37,'新しいシステムのニーズが明確である',NULL,NULL,NULL,NULL,1),(38,'ユーザが識別されている',NULL,NULL,NULL,NULL,2),(39,'最初の出資者が識別されている',NULL,NULL,NULL,NULL,3),(40,'システムの目的とスコープが同意されている',NULL,NULL,NULL,NULL,1),(41,'成功の判断基準が明確である',NULL,NULL,NULL,NULL,2),(42,'要求管理の仕組みが同意されている',NULL,NULL,NULL,NULL,3),(43,'制約と前提が識別されている',NULL,NULL,NULL,NULL,4),(44,'明確な全体像が関係者に共有されている',NULL,NULL,NULL,NULL,1),(45,'重要な利用シナリオが共有されている',NULL,NULL,NULL,NULL,2),(46,'要求の優先度が明確である',NULL,NULL,NULL,NULL,3),(47,'認識の不一致が対応されている',NULL,NULL,NULL,NULL,4),(48,'要求がもたらす影響力が理解されている',NULL,NULL,NULL,NULL,5),(49,'関係者が受理可能な解決策が示されている',NULL,NULL,NULL,NULL,1),(50,'同意された要求が変更される確度は低い',NULL,NULL,NULL,NULL,2),(51,'価値が明確である',NULL,NULL,NULL,NULL,3),(52,'システムの受理に必要十分な要求が実装されている',NULL,NULL,NULL,NULL,1),(53,'システムを稼働させる価値のある状態にあることにステークホルダが同意している',NULL,NULL,NULL,NULL,2),(54,'システムは要求とニーズを満足している',NULL,NULL,NULL,NULL,1),(55,'完成を妨げる未解決の要求が存在しない',NULL,NULL,NULL,NULL,2),(56,'重要な技術リスクに対応可能なアーキテクチャが採用されている',NULL,NULL,NULL,NULL,1),(57,'アーキテクチャの採用基準が同意されている',NULL,NULL,NULL,NULL,2),(58,'使用するプラットフォーム、技術、言語が選択されている',NULL,NULL,NULL,NULL,3),(59,'購入、構築、再利用の方針が決定している',NULL,NULL,NULL,NULL,4),(60,'重要なアーキテクチャの特性が論証できている',NULL,NULL,NULL,NULL,1),(61,'アーキテクチャが適切であることをステークホルダが同意している',NULL,NULL,NULL,NULL,2),(62,'重要なインタフェースとシステム構成が論証できている',NULL,NULL,NULL,NULL,3),(63,'システムは使用可能であり、要求された品質特性を達成できている',NULL,NULL,NULL,NULL,1),(64,'ユーザがシステムを操作可能である',NULL,NULL,NULL,NULL,2),(65,'機能と性能がテストされており、検収済みである',NULL,NULL,NULL,NULL,3),(66,'欠陥レベルが許容されている',NULL,NULL,NULL,NULL,4),(67,'リリース内容が周知されている',NULL,NULL,NULL,NULL,5),(68,'ユーザドキュメントが利用できる',NULL,NULL,NULL,NULL,1),(69,'ステークホルダがシステムを受理している',NULL,NULL,NULL,NULL,2),(70,'ステークホルダがシステムの運用方法を準備しようとしている',NULL,NULL,NULL,NULL,3),(71,'運用環境でシステムが使用されている',NULL,NULL,NULL,NULL,1),(72,'想定されたユーザに利用されている',NULL,NULL,NULL,NULL,2),(73,'システムの全機能を運用した事例が少なくとも一つある',NULL,NULL,NULL,NULL,3),(74,'システム保守のサービスレベルが同意されている',NULL,NULL,NULL,NULL,4),(75,'システムは保守されていない',NULL,NULL,NULL,NULL,1),(76,'システム更新によるリリースはない',NULL,NULL,NULL,NULL,2),(77,'システムは置き換えられるか開発が中止されている',NULL,NULL,NULL,NULL,3),(78,'チーム任務が明確である',NULL,NULL,NULL,NULL,1),(79,'チームは任務達成に必要な育成プランを持っている',NULL,NULL,NULL,NULL,2),(80,'要求される能力が識別されている',NULL,NULL,NULL,NULL,3),(81,'チームの規模が決定されている',NULL,NULL,NULL,NULL,4),(82,'チームは人気開始に必要なリソースを保有している',NULL,NULL,NULL,NULL,1),(83,'チームの組織構造と個人の役割が理解されている',NULL,NULL,NULL,NULL,2),(84,'メンバーは仕事の進め方を知っている',NULL,NULL,NULL,NULL,3),(85,'チームの組織メンバーが一つのユニットになって仕事に取り組んでいる',NULL,NULL,NULL,NULL,1),(86,'メンバー間のコミュニケーションがオープンで正直である',NULL,NULL,NULL,NULL,2),(87,'メンバーがチームの任務に集中している',NULL,NULL,NULL,NULL,3),(88,'メンバー個々人の目的の先にチームの成功がある',NULL,NULL,NULL,NULL,4),(89,'チームは効果的、効率的に動いている',NULL,NULL,NULL,NULL,1),(90,'状況変化に対応することができる',NULL,NULL,NULL,NULL,2),(91,'高い品質の成果物が作り出されている',NULL,NULL,NULL,NULL,3),(92,'撤回とやり直しが最低限である',NULL,NULL,NULL,NULL,4),(93,'常に無駄が排除されている',NULL,NULL,NULL,NULL,5),(94,'チームに責任は生じない',NULL,NULL,NULL,NULL,1),(95,'役割は移管されている',NULL,NULL,NULL,NULL,2),(96,'メンバーは他の仕事に割り当て可能である',NULL,NULL,NULL,NULL,3),(97,'仕事を立ち上げた人物が周知されている',NULL,NULL,NULL,NULL,1),(98,'仕事の制約が明確である',NULL,NULL,NULL,NULL,2),(99,'資金調達の目途が立っている',NULL,NULL,NULL,NULL,3),(100,'仕事の優先度が明確である',NULL,NULL,NULL,NULL,4),(101,'費用と労力が見積もられている',NULL,NULL,NULL,NULL,1),(102,'仕事を開始する資金とリソースが準備万端である',NULL,NULL,NULL,NULL,2),(103,'合否基準が決まっている',NULL,NULL,NULL,NULL,3),(104,'管理手順が同意されている',NULL,NULL,NULL,NULL,4),(105,'リスクの影響度が理解されている',NULL,NULL,NULL,NULL,5),(106,'仕事の依存関係が明確である',NULL,NULL,NULL,NULL,6),(107,'開発が開始している',NULL,NULL,NULL,NULL,1),(108,'仕事の進捗が計測されている',NULL,NULL,NULL,NULL,2),(109,'明確に定義された作業アイテムレベルまで仕事が詳細化されている',NULL,NULL,NULL,NULL,3),(110,'チームメンバーが作業アイテムを受理して作業を進めている',NULL,NULL,NULL,NULL,4),(111,'仕事が順調に進み、リスクが管理下に置かれている',NULL,NULL,NULL,NULL,1),(112,'未計画の仕事や再実施の作業が管理下に置かれている',NULL,NULL,NULL,NULL,2),(113,'見積もり内に作業アイテムが完了している',NULL,NULL,NULL,NULL,3),(114,'仕事の評価基準が追跡できている',NULL,NULL,NULL,NULL,4),(115,'成果提供まで完了している',NULL,NULL,NULL,NULL,1),(116,'仕事の成果が完了している',NULL,NULL,NULL,NULL,2),(117,'開発したソフトウェアシステムが顧客に受理されている',NULL,NULL,NULL,NULL,3),(118,'全ての残作業が完了しており、公式に仕事がクローズされている',NULL,NULL,NULL,NULL,1),(119,'仕事でやるべきすべてのことが達成されている',NULL,NULL,NULL,NULL,2),(120,'教訓とメトリクスが資産化されて再利用できる状態となっている',NULL,NULL,NULL,NULL,3),(121,'原則と制約が決定されている',NULL,NULL,NULL,NULL,1),(122,'原則と制約が宣言されている',NULL,NULL,NULL,NULL,2),(123,'プラクティスとツールが同意されている',NULL,NULL,NULL,NULL,3),(124,'チームは扱い方を理解している',NULL,NULL,NULL,NULL,4),(125,'重要なプラクティスとツールが準備されている',NULL,NULL,NULL,NULL,1),(126,'プラクティスとツール間のギャップが分析され、理解されている',NULL,NULL,NULL,NULL,2),(127,'能力のギャップが分析され、理解されている',NULL,NULL,NULL,NULL,3),(128,'プラクティスとツールが統合されている',NULL,NULL,NULL,NULL,4),(129,'チームの一部のメンバーが利用している',NULL,NULL,NULL,NULL,1),(130,'使用してするプラクティスとツールが定期的に監査されている',NULL,NULL,NULL,NULL,2),(131,'プラクティスとツールがチームによって改善されている',NULL,NULL,NULL,NULL,3),(132,'フィードバックの手続きが整っている',NULL,NULL,NULL,NULL,4),(133,'チームメンバー全員が利用している',NULL,NULL,NULL,NULL,1),(134,'全てのメンバーが仕事を進めるためにプラクティスとツールを入手できる',NULL,NULL,NULL,NULL,2),(135,'知0ム全体が仕事の仕方の監査と改善に取り組んでいる',NULL,NULL,NULL,NULL,3),(136,'仕事の仕方がチームで最適な形で活用されている',NULL,NULL,NULL,NULL,1),(137,'チームメンバーが計画通り進歩している',NULL,NULL,NULL,NULL,2),(138,'プラクティスが当たり前に適用されるチーム風土が整っている',NULL,NULL,NULL,NULL,3),(139,'プラクティスを実践する上でのツールの課題が解消されている',NULL,NULL,NULL,NULL,4),(140,'チームに利用されていない',NULL,NULL,NULL,NULL,1),(141,'次の利用時の教訓が共有されている',NULL,NULL,NULL,NULL,2);
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
  `checkitem_id` int(10) unsigned NOT NULL,
  `item_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`checkitem_status_id`),
  KEY `fk_chekitem_statuses_checkitem_idx` (`checkitem_id`),
  KEY `fk_checkitem_status_item_idx` (`item_id`),
  CONSTRAINT `fk_checkitem_status_checkitem` FOREIGN KEY (`checkitem_id`) REFERENCES `checkitem` (`checkitem_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_checkitem_status_item` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8 COMMENT='\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkitem_status`
--

LOCK TABLES `checkitem_status` WRITE;
/*!40000 ALTER TABLE `checkitem_status` DISABLE KEYS */;
INSERT INTO `checkitem_status` VALUES (45,5,'Breaked some rules during this sprint. In the next sprint teams should care about it.',NULL,1,1,NULL,1,1,24),(46,5,'',NULL,1,1,NULL,0,2,24),(47,5,'',NULL,1,1,NULL,1,3,25),(48,5,'',NULL,1,1,NULL,0,4,25),(49,4,'',NULL,1,1,NULL,1,5,26),(50,1,'',NULL,1,1,NULL,0,6,26),(51,1,'',NULL,1,1,NULL,0,7,27),(54,1,'',NULL,1,1,NULL,0,8,27),(55,4,'',NULL,1,1,NULL,0,9,24),(56,5,'',NULL,1,1,NULL,0,10,25),(57,5,'',NULL,1,1,NULL,0,11,25),(58,1,'',NULL,1,1,NULL,0,12,31),(59,1,'',NULL,1,1,NULL,0,13,31),(60,1,'',NULL,1,1,NULL,0,14,31),(61,1,'',NULL,1,1,NULL,0,15,32),(62,1,'',NULL,1,1,NULL,0,16,32),(63,1,'',NULL,1,1,NULL,0,17,32),(64,1,'',NULL,1,1,NULL,0,18,33),(65,1,'',NULL,1,1,NULL,0,19,33),(66,1,'',NULL,1,1,NULL,0,20,33),(67,1,'',NULL,1,1,NULL,0,21,33),(68,5,'',NULL,1,1,NULL,0,22,34),(69,5,'',NULL,1,1,NULL,0,23,34),(70,1,'',NULL,1,1,NULL,0,24,26),(71,1,'',NULL,1,1,NULL,0,25,27),(72,1,'',NULL,1,1,NULL,0,26,27),(73,1,'',NULL,1,1,NULL,0,27,35),(74,1,'',NULL,1,1,NULL,0,28,35),(75,1,'',NULL,1,1,NULL,0,29,35),(76,1,'',NULL,1,1,NULL,0,30,36),(77,1,'',NULL,1,1,NULL,0,31,36),(78,1,'',NULL,1,1,NULL,0,32,36),(79,1,'',NULL,1,1,NULL,0,33,37),(80,1,'',NULL,1,1,NULL,0,34,37),(81,1,'',NULL,1,1,NULL,0,35,38),(82,1,'',NULL,1,1,NULL,0,36,38),(163,1,NULL,NULL,1,1,NULL,0,37,41),(164,1,NULL,NULL,1,1,NULL,0,38,41),(165,1,NULL,NULL,1,1,NULL,0,39,41),(166,1,NULL,NULL,1,1,NULL,0,40,42),(167,1,NULL,NULL,1,1,NULL,0,41,42),(168,1,NULL,NULL,1,1,NULL,0,42,42),(169,1,NULL,NULL,1,1,NULL,0,43,42),(170,1,NULL,NULL,1,1,NULL,0,44,43),(171,1,NULL,NULL,1,1,NULL,0,45,43),(172,1,NULL,NULL,1,1,NULL,0,46,43),(173,1,NULL,NULL,1,1,NULL,0,47,43),(174,1,NULL,NULL,1,1,NULL,0,48,43),(175,1,NULL,NULL,1,1,NULL,0,49,44),(176,1,NULL,NULL,1,1,NULL,0,50,44),(177,1,NULL,NULL,1,1,NULL,0,51,44),(178,1,NULL,NULL,1,1,NULL,0,52,45),(179,1,NULL,NULL,1,1,NULL,0,53,45),(180,1,NULL,NULL,1,1,NULL,0,54,46),(181,1,NULL,NULL,1,1,NULL,0,55,46),(182,1,NULL,NULL,1,1,NULL,0,56,48),(183,1,NULL,NULL,1,1,NULL,0,57,48),(184,1,NULL,NULL,1,1,NULL,0,58,48),(185,1,NULL,NULL,1,1,NULL,0,59,48),(186,1,NULL,NULL,1,1,NULL,0,60,49),(187,1,NULL,NULL,1,1,NULL,0,61,49),(188,1,NULL,NULL,1,1,NULL,0,62,49),(189,1,NULL,NULL,1,1,NULL,0,63,50),(190,1,NULL,NULL,1,1,NULL,0,64,50),(191,1,NULL,NULL,1,1,NULL,0,65,50),(192,1,NULL,NULL,1,1,NULL,0,66,50),(193,1,NULL,NULL,1,1,NULL,0,67,50),(194,1,NULL,NULL,1,1,NULL,0,68,51),(195,1,NULL,NULL,1,1,NULL,0,69,51),(196,1,NULL,NULL,1,1,NULL,0,70,51),(197,1,NULL,NULL,1,1,NULL,0,71,52),(198,1,NULL,NULL,1,1,NULL,0,72,52),(199,1,NULL,NULL,1,1,NULL,0,73,52),(200,1,NULL,NULL,1,1,NULL,0,74,52),(201,1,NULL,NULL,1,1,NULL,0,75,53),(202,1,NULL,NULL,1,1,NULL,0,76,53),(203,1,NULL,NULL,1,1,NULL,0,77,53),(204,1,NULL,NULL,1,1,NULL,0,78,63),(205,1,NULL,NULL,1,1,NULL,0,79,63),(206,1,NULL,NULL,1,1,NULL,0,80,63),(207,1,NULL,NULL,1,1,NULL,0,81,63),(208,1,NULL,NULL,1,1,NULL,0,82,64),(209,1,NULL,NULL,1,1,NULL,0,83,64),(210,1,NULL,NULL,1,1,NULL,0,84,64),(211,1,NULL,NULL,1,1,NULL,0,85,65),(212,1,NULL,NULL,1,1,NULL,0,86,65),(213,1,NULL,NULL,1,1,NULL,0,87,65),(214,1,NULL,NULL,1,1,NULL,0,88,65),(215,1,NULL,NULL,1,1,NULL,0,89,66),(216,1,NULL,NULL,1,1,NULL,0,90,66),(217,1,NULL,NULL,1,1,NULL,0,91,66),(218,1,NULL,NULL,1,1,NULL,0,92,66),(219,1,NULL,NULL,1,1,NULL,0,93,66),(220,1,NULL,NULL,1,1,NULL,0,94,67),(221,1,NULL,NULL,1,1,NULL,0,95,67),(222,1,NULL,NULL,1,1,NULL,0,96,67),(223,1,NULL,NULL,1,1,NULL,0,97,56),(224,1,NULL,NULL,1,1,NULL,0,98,56),(225,1,NULL,NULL,1,1,NULL,0,99,56),(226,1,NULL,NULL,1,1,NULL,0,100,56),(227,1,NULL,NULL,1,1,NULL,0,101,57),(228,1,NULL,NULL,1,1,NULL,0,102,57),(229,1,NULL,NULL,1,1,NULL,0,103,57),(230,1,NULL,NULL,1,1,NULL,0,104,57),(231,1,NULL,NULL,1,1,NULL,0,105,57),(232,1,NULL,NULL,1,1,NULL,0,106,57),(233,1,NULL,NULL,1,1,NULL,0,107,58),(234,1,NULL,NULL,1,1,NULL,0,108,58),(235,1,NULL,NULL,1,1,NULL,0,109,58),(236,1,NULL,NULL,1,1,NULL,0,110,58),(237,1,NULL,NULL,1,1,NULL,0,111,59),(238,1,NULL,NULL,1,1,NULL,0,112,59),(239,1,NULL,NULL,1,1,NULL,0,113,59),(240,1,NULL,NULL,1,1,NULL,0,114,59),(241,1,NULL,NULL,1,1,NULL,0,115,60),(242,1,NULL,NULL,1,1,NULL,0,116,60),(243,1,NULL,NULL,1,1,NULL,0,117,60),(244,1,NULL,NULL,1,1,NULL,0,118,61),(245,1,NULL,NULL,1,1,NULL,0,119,61),(246,1,NULL,NULL,1,1,NULL,0,120,61),(247,1,NULL,NULL,1,1,NULL,0,121,69),(248,1,NULL,NULL,1,1,NULL,0,122,69),(249,1,NULL,NULL,1,1,NULL,0,123,69),(250,1,NULL,NULL,1,1,NULL,0,124,69),(251,1,NULL,NULL,1,1,NULL,0,125,70),(252,1,NULL,NULL,1,1,NULL,0,126,70),(253,1,NULL,NULL,1,1,NULL,0,127,70),(254,1,NULL,NULL,1,1,NULL,0,128,70),(255,1,NULL,NULL,1,1,NULL,0,129,71),(256,1,NULL,NULL,1,1,NULL,0,130,71),(257,1,NULL,NULL,1,1,NULL,0,131,71),(258,1,NULL,NULL,1,1,NULL,0,132,71),(259,1,NULL,NULL,1,1,NULL,0,133,72),(260,1,NULL,NULL,1,1,NULL,0,134,72),(261,1,NULL,NULL,1,1,NULL,0,135,72),(262,1,NULL,NULL,1,1,NULL,0,136,73),(263,1,NULL,NULL,1,1,NULL,0,137,73),(264,1,NULL,NULL,1,1,NULL,0,138,73),(265,1,NULL,NULL,1,1,NULL,0,139,73),(266,1,NULL,NULL,1,1,NULL,0,140,74),(267,1,NULL,NULL,1,1,NULL,0,141,74);
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
  `project_id` int(10) unsigned NOT NULL COMMENT '外部キー：\n引用表：project\n外部キー名：fk_checklist_project\n外部キー制限：On update cascade, On delete cascade',
  PRIMARY KEY (`checklist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checklist`
--

LOCK TABLES `checklist` WRITE;
/*!40000 ALTER TABLE `checklist` DISABLE KEYS */;
INSERT INTO `checklist` VALUES (1,'SEMAT',0,1);
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
INSERT INTO `item` VALUES (21,'Customer',27.333333333333332,1,21,1),(22,'Opportunity',47.166666666666664,1,21,1),(23,'ステークホルダー',8.333333333333334,1,21,2),(24,'Identified',83.33333333333334,1,22,1),(25,'解決策の方向付け',100,1,22,2),(26,'関係者の特定',50,1,23,1),(27,'代表者の選定',0,1,23,2),(31,'価値の設定',0,1,22,3),(32,'解決策決定',0,1,22,4),(33,'課題解決',0,1,22,5),(34,'利益獲得',100,1,22,6),(35,'活動の協業',0,1,23,3),(36,'合意形成',0,1,23,4),(37,'展開合意',0,1,23,5),(38,'利益満足',0,1,23,6),(39,'解決策',0,NULL,39,2),(40,'要求',0,NULL,39,1),(41,'構想',0,NULL,40,1),(42,'スコープ定義',0,NULL,40,2),(43,'一貫性・体系化',0,NULL,40,3),(44,'受理可能',0,NULL,40,4),(45,'実装',0,NULL,40,5),(46,'満足',0,NULL,40,6),(47,'ソフトウェアシステム',0,NULL,39,2),(48,'アーキテクチャ決定',0,NULL,47,1),(49,'論証完了',0,NULL,47,2),(50,'使用可能',0,NULL,47,3),(51,'準備可能',0,NULL,47,4),(52,'運用',0,NULL,47,5),(53,'退役',0,NULL,47,6),(54,'取り組み',0,NULL,54,3),(55,'仕事',0,NULL,54,1),(56,'着手',0,NULL,55,1),(57,'準備完了',0,NULL,55,2),(58,'開発開始',0,NULL,55,3),(59,'制御可能',0,NULL,55,4),(60,'完結',0,NULL,55,5),(61,'終了',0,NULL,55,6),(62,'チーム',0,NULL,54,2),(63,'立ち上げ',0,NULL,62,1),(64,'組織化',0,NULL,62,2),(65,'協調',0,NULL,62,3),(66,'最適化',0,NULL,62,4),(67,'休止',0,NULL,62,5),(68,'仕事の仕方',0,NULL,54,3),(69,'原則決定',0,NULL,68,1),(70,'準備完了',0,NULL,68,2),(71,'使用',0,NULL,68,3),(72,'定着',0,NULL,68,4),(73,'活用',0,NULL,68,5),(74,'廃止',0,NULL,68,6);
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
  `prj_id` int(10) unsigned NOT NULL COMMENT 'プロジェクトID',
  `name` varchar(30) NOT NULL COMMENT 'プロジェクト名',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,1,'ocms');
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

-- Dump completed on 2017-08-07 16:31:11
