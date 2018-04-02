-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: pingpong
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `match`
--

DROP TABLE IF EXISTS `match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player1` int(11) DEFAULT NULL,
  `player2` int(11) DEFAULT NULL,
  `tournament` int(11) DEFAULT NULL,
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_match_user3_idx` (`player1`),
  KEY `fk_match_tournament1_idx` (`tournament`),
  CONSTRAINT `fk_match_tournament1` FOREIGN KEY (`tournament`) REFERENCES `tournament` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_match_user3` FOREIGN KEY (`player1`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match`
--

LOCK TABLES `match` WRITE;
/*!40000 ALTER TABLE `match` DISABLE KEYS */;
INSERT INTO `match` VALUES (18,3,4,3),(19,5,6,3),(20,7,8,3),(21,9,10,3),(23,4,5,3),(24,7,9,3),(26,4,9,3),(27,4,5,4),(28,6,7,4),(29,8,10,4),(30,11,12,4),(35,4,6,4),(36,4,10,4),(37,10,0,4),(38,3,4,6),(39,5,7,6),(40,8,9,6),(41,10,12,6),(42,4,5,6),(43,4,5,6),(44,3,4,7),(45,5,7,7),(46,8,9,7),(47,10,12,7),(48,3,4,7),(49,5,7,7),(50,8,9,7),(51,10,12,7),(52,3,4,7),(53,5,7,7),(54,8,9,7),(55,10,12,7),(56,3,4,7),(57,5,7,7),(58,8,9,7),(59,10,12,7),(60,3,4,7),(61,5,7,7),(62,8,9,7),(63,10,12,7),(64,3,4,7),(65,5,7,7),(66,8,9,7),(67,10,12,7),(68,3,4,7),(69,5,7,7),(70,8,9,7),(71,10,12,7),(72,4,0,7),(73,3,4,8),(74,5,7,8),(75,8,9,8),(76,10,12,8),(77,4,5,8),(79,12,12,8),(87,1,20,10),(88,21,22,10),(89,24,25,10),(90,26,27,10),(91,22,0,10);
/*!40000 ALTER TABLE `match` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-02 18:20:00
