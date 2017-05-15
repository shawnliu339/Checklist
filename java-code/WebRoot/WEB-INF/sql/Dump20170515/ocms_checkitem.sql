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
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkitem`
--

LOCK TABLES `checkitem` WRITE;
/*!40000 ALTER TABLE `checkitem` DISABLE KEYS */;
INSERT INTO `checkitem` VALUES (1,'ソフトウェアによる解決策によって対応できる課題が識別されている',NULL,NULL,NULL,NULL,1),(2,'ステークホルダーは潜在的な価値をよく理解して投資したいと願っている',NULL,NULL,NULL,NULL,2),(3,'ソフトウェアによる解決策のニーズが裏付けされている',NULL,NULL,NULL,NULL,1),(4,'ステークホルダーが特定されている',NULL,NULL,NULL,NULL,2),(5,'ステークホルダーが識別されている',NULL,NULL,NULL,NULL,1),(6,'ステークホルダー間で代表者が合意されている',NULL,NULL,NULL,NULL,2),(7,'ステークホルダーが任命されている',NULL,NULL,NULL,NULL,1),(8,'自身の役割と権限について同意されている',NULL,NULL,NULL,NULL,2),(9,'ステークホルダ間で識別された課題が共有されている',NULL,NULL,NULL,NULL,3),(10,'潜在的な問題と本質が判明している',NULL,NULL,NULL,NULL,3),(11,'少なくとも1つのソフトウェアによる解決策が提案されている',NULL,NULL,NULL,NULL,4),(12,'解決策が成功したときの価値が設定されている',NULL,NULL,NULL,NULL,1),(13,'解決策の影響をステークホルダが理解している',NULL,NULL,NULL,NULL,2),(14,'ソフトウェアシステムの価値が理解されている',NULL,NULL,NULL,NULL,3),(15,'解決策の主要な部分が描かれている',NULL,NULL,NULL,NULL,1),(16,'解決策を開発、展開するときの制約が明らかになっている',NULL,NULL,NULL,NULL,2),(17,'リスクが管理下に置かれている',NULL,NULL,NULL,NULL,3),(18,'機会に対応するための論証された解決策が提供されている',NULL,NULL,NULL,NULL,1),(19,'有効なシステムが利用可能である',NULL,NULL,NULL,NULL,2),(20,'ステークホルダは展開する価値に同意している',NULL,NULL,NULL,NULL,3),(21,'ステークホルダは機会の解決策に満足している',NULL,NULL,NULL,NULL,4),(22,'運用で明らかな利益が生み出されている',NULL,NULL,NULL,NULL,1),(23,'少なくとも予想通りの投資効果が得られている',NULL,NULL,NULL,NULL,2),(24,'ステークホルダーの役割が定義されている',NULL,NULL,NULL,NULL,3),(25,'ステークホルダーが協業するやり方が同意されている',NULL,NULL,NULL,NULL,3),(26,'ステークホルダーがチームの仕事を尊重している',NULL,NULL,NULL,NULL,4),(27,'ステークホルダが役割を果たしている',NULL,NULL,NULL,NULL,1),(28,'チームにタイミング良くフィードバックを与えたり、決断の場に参加している',NULL,NULL,NULL,NULL,2),(29,'ステークホルダ間のコミュニケーションが良好である',NULL,NULL,NULL,NULL,3),(30,'ステークホルダにとっての価値が定義され、チームに受け入れられている',NULL,NULL,NULL,NULL,1),(31,'ステークホルダは優先度を同意している',NULL,NULL,NULL,NULL,2),(32,'システムの展開による最小限の期待値について同意している',NULL,NULL,NULL,NULL,3),(33,'ステークホルダの視点でシステムに対するフィードバックを提供している',NULL,NULL,NULL,NULL,1),(34,'システムの展開に対する準備が整ったことを確認できている',NULL,NULL,NULL,NULL,2),(35,'システムはステークホルダの最小限の期待以上である',NULL,NULL,NULL,NULL,1),(36,'ステークホルダのニーズと期待は満足されている',NULL,NULL,NULL,NULL,2),(37,'新しいシステムのニーズが明確である',NULL,NULL,NULL,NULL,1),(38,'ユーザが識別されている',NULL,NULL,NULL,NULL,2),(39,'最初の出資者が識別されている',NULL,NULL,NULL,NULL,3),(40,'システムの目的とスコープが同意されている',NULL,NULL,NULL,NULL,1),(41,'成功の判断基準が明確である',NULL,NULL,NULL,NULL,2),(42,'要求管理の仕組みが同意されている',NULL,NULL,NULL,NULL,3),(43,'制約と前提が識別されている',NULL,NULL,NULL,NULL,4),(44,'明確な全体像が関係者に共有されている',NULL,NULL,NULL,NULL,1),(45,'重要な利用シナリオが共有されている',NULL,NULL,NULL,NULL,2),(46,'要求の優先度が明確である',NULL,NULL,NULL,NULL,3),(47,'認識の不一致が対応されている',NULL,NULL,NULL,NULL,4),(48,'要求がもたらす影響力が理解されている',NULL,NULL,NULL,NULL,5),(49,'関係者が受理可能な解決策が示されている',NULL,NULL,NULL,NULL,1),(50,'同意された要求が変更される確度は低い',NULL,NULL,NULL,NULL,2),(51,'価値が明確である',NULL,NULL,NULL,NULL,3),(52,'システムの受理に必要十分な要求が実装されている',NULL,NULL,NULL,NULL,1),(53,'システムを稼働させる価値のある状態にあることにステークホルダが同意している',NULL,NULL,NULL,NULL,2),(54,'システムは要求とニーズを満足している',NULL,NULL,NULL,NULL,1),(55,'完成を妨げる未解決の要求が存在しない',NULL,NULL,NULL,NULL,2),(56,'重要な技術リスクに対応可能なアーキテクチャが採用されている',NULL,NULL,NULL,NULL,1),(57,'アーキテクチャの採用基準が同意されている',NULL,NULL,NULL,NULL,2),(58,'使用するプラットフォーム、技術、言語が選択されている',NULL,NULL,NULL,NULL,3),(59,'購入、構築、再利用の方針が決定している',NULL,NULL,NULL,NULL,4),(60,'重要なアーキテクチャの特性が論証できている',NULL,NULL,NULL,NULL,1),(61,'アーキテクチャが適切であることをステークホルダが同意している',NULL,NULL,NULL,NULL,2),(62,'重要なインタフェースとシステム構成が論証できている',NULL,NULL,NULL,NULL,3),(63,'システムは使用可能であり、要求された品質特性を達成できている',NULL,NULL,NULL,NULL,1),(64,'ユーザがシステムを操作可能である',NULL,NULL,NULL,NULL,2),(65,'機能と性能がテストされており、検収済みである',NULL,NULL,NULL,NULL,3),(66,'欠陥レベルが許容されている',NULL,NULL,NULL,NULL,4),(67,'リリース内容が周知されている',NULL,NULL,NULL,NULL,5),(68,'ユーザドキュメントが利用できる',NULL,NULL,NULL,NULL,1),(69,'ステークホルダがシステムを受理している',NULL,NULL,NULL,NULL,2),(70,'ステークホルダがシステムの運用方法を準備しようとしている',NULL,NULL,NULL,NULL,3),(71,'運用環境でシステムが使用されている',NULL,NULL,NULL,NULL,1),(72,'想定されたユーザに利用されている',NULL,NULL,NULL,NULL,2),(73,'システムの全機能を運用した事例が少なくとも一つある',NULL,NULL,NULL,NULL,3),(74,'システム保守のサービスレベルが同意されている',NULL,NULL,NULL,NULL,4),(75,'システムは保守されていない',NULL,NULL,NULL,NULL,1),(76,'システム更新によるリリースはない',NULL,NULL,NULL,NULL,2),(77,'システムは置き換えられるか開発が中止されている',NULL,NULL,NULL,NULL,3),(78,'チーム任務が明確である',NULL,NULL,NULL,NULL,1),(79,'チームは任務達成に必要な育成プランを持っている',NULL,NULL,NULL,NULL,2),(80,'要求される能力が識別されている',NULL,NULL,NULL,NULL,3),(81,'チームの規模が決定されている',NULL,NULL,NULL,NULL,4),(82,'チームは人気開始に必要なリソースを保有している',NULL,NULL,NULL,NULL,1),(83,'チームの組織構造と個人の役割が理解されている',NULL,NULL,NULL,NULL,2),(84,'メンバーは仕事の進め方を知っている',NULL,NULL,NULL,NULL,3),(85,'チームの組織メンバーが一つのユニットになって仕事に取り組んでいる',NULL,NULL,NULL,NULL,1),(86,'メンバー間のコミュニケーションがオープンで正直である',NULL,NULL,NULL,NULL,2),(87,'メンバーがチームの任務に集中している',NULL,NULL,NULL,NULL,3),(88,'メンバー個々人の目的の先にチームの成功がある',NULL,NULL,NULL,NULL,4),(89,'チームは効果的、効率的に動いている',NULL,NULL,NULL,NULL,1),(90,'状況変化に対応することができる',NULL,NULL,NULL,NULL,2),(91,'高い品質の成果物が作り出されている',NULL,NULL,NULL,NULL,3),(92,'撤回とやり直しが最低限である',NULL,NULL,NULL,NULL,4),(93,'常に無駄が排除されている',NULL,NULL,NULL,NULL,5),(94,'チームに責任は生じない',NULL,NULL,NULL,NULL,1),(95,'役割は移管されている',NULL,NULL,NULL,NULL,2),(96,'メンバーは他の仕事に割り当て可能である',NULL,NULL,NULL,NULL,3),(97,'仕事を立ち上げた人物が周知されている',NULL,NULL,NULL,NULL,1),(98,'仕事の制約が明確である',NULL,NULL,NULL,NULL,2),(99,'資金調達の目途が立っている',NULL,NULL,NULL,NULL,3),(100,'仕事の優先度が明確である',NULL,NULL,NULL,NULL,4),(101,'費用と労力が見積もられている',NULL,NULL,NULL,NULL,1),(102,'仕事を開始する資金とリソースが準備万端である',NULL,NULL,NULL,NULL,2),(103,'合否基準が決まっている',NULL,NULL,NULL,NULL,3),(104,'管理手順が同意されている',NULL,NULL,NULL,NULL,4),(105,'リスクの影響度が理解されている',NULL,NULL,NULL,NULL,5),(106,'仕事の依存関係が明確である',NULL,NULL,NULL,NULL,6),(107,'開発が開始している',NULL,NULL,NULL,NULL,1),(108,'仕事の進捗が計測されている',NULL,NULL,NULL,NULL,2),(109,'明確に定義された作業アイテムレベルまで仕事が詳細化されている',NULL,NULL,NULL,NULL,3),(110,'チームメンバーが作業アイテムを受理して作業を進めている',NULL,NULL,NULL,NULL,4),(111,'仕事が順調に進み、リスクが管理下に置かれている',NULL,NULL,NULL,NULL,1),(112,'未計画の仕事や再実施の作業が管理下に置かれている',NULL,NULL,NULL,NULL,2),(113,'見積もり内に作業アイテムが完了している',NULL,NULL,NULL,NULL,3),(114,'仕事の評価基準が追跡できている',NULL,NULL,NULL,NULL,4),(115,'成果提供まで完了している',NULL,NULL,NULL,NULL,1),(116,'仕事の成果が完了している',NULL,NULL,NULL,NULL,2),(117,'開発したソフトウェアシステムが顧客に受理されている',NULL,NULL,NULL,NULL,3),(118,'全ての残作業が完了しており、公式に仕事がクローズされている',NULL,NULL,NULL,NULL,1),(119,'仕事でやるべきすべてのことが達成されている',NULL,NULL,NULL,NULL,2),(120,'教訓とメトリクスが資産化されて再利用できる状態となっている',NULL,NULL,NULL,NULL,3),(121,'原則と制約が決定されている',NULL,NULL,NULL,NULL,1),(122,'原則と制約が宣言されている',NULL,NULL,NULL,NULL,2),(123,'プラクティスとツールが同意されている',NULL,NULL,NULL,NULL,3),(124,'チームは扱い方を理解している',NULL,NULL,NULL,NULL,4),(125,'重要なプラクティスとツールが準備されている',NULL,NULL,NULL,NULL,1),(126,'プラクティスとツール間のギャップが分析され、理解されている',NULL,NULL,NULL,NULL,2),(127,'能力のギャップが分析され、理解されている',NULL,NULL,NULL,NULL,3),(128,'プラクティスとツールが統合されている',NULL,NULL,NULL,NULL,4),(129,'チームの一部のメンバーが利用している',NULL,NULL,NULL,NULL,1),(130,'使用してするプラクティスとツールが定期的に監査されている',NULL,NULL,NULL,NULL,2),(131,'プラクティスとツールがチームによって改善されている',NULL,NULL,NULL,NULL,3),(132,'フィードバックの手続きが整っている',NULL,NULL,NULL,NULL,4),(133,'チームメンバー全員が利用している',NULL,NULL,NULL,NULL,1),(134,'全てのメンバーが仕事を進めるためにプラクティスとツールを入手できる',NULL,NULL,NULL,NULL,2),(135,'知0ム全体が仕事の仕方の監査と改善に取り組んでいる',NULL,NULL,NULL,NULL,3),(136,'仕事の仕方がチームで最適な形で活用されている',NULL,NULL,NULL,NULL,1),(137,'チームメンバーが計画通り進歩している',NULL,NULL,NULL,NULL,2),(138,'プラクティスが当たり前に適用されるチーム風土が整っている',NULL,NULL,NULL,NULL,3),(139,'プラクティスを実践する上でのツールの課題が解消されている',NULL,NULL,NULL,NULL,4),(140,'チームに利用されていない',NULL,NULL,NULL,NULL,1),(141,'次の利用時の教訓が共有されている',NULL,NULL,NULL,NULL,2);
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

-- Dump completed on 2017-05-15 17:21:41
