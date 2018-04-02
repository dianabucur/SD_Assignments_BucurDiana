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
-- Table structure for table `enrolled`
--

DROP TABLE IF EXISTS `enrolled`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enrolled` (
  `playerid` int(11) NOT NULL,
  `tournamentid` int(11) NOT NULL,
  PRIMARY KEY (`playerid`,`tournamentid`),
  KEY `fk_enrolled_tournament1_idx` (`tournamentid`),
  CONSTRAINT `fk_enrolled_tournament1` FOREIGN KEY (`tournamentid`) REFERENCES `tournament` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_enrolled_user` FOREIGN KEY (`playerid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrolled`
--

LOCK TABLES `enrolled` WRITE;
/*!40000 ALTER TABLE `enrolled` DISABLE KEYS */;
INSERT INTO `enrolled` VALUES (4,1),(6,1),(7,1),(8,1),(10,1),(11,1),(12,1),(13,1),(3,3),(4,3),(5,3),(6,3),(7,3),(8,3),(9,3),(10,3),(4,4),(5,4),(6,4),(7,4),(8,4),(10,4),(11,4),(12,4),(4,5),(16,5),(3,6),(4,6),(5,6),(7,6),(8,6),(9,6),(10,6),(12,6),(3,7),(4,7),(5,7),(7,7),(8,7),(9,7),(10,7),(12,7),(3,8),(4,8),(5,8),(7,8),(8,8),(9,8),(10,8),(12,8),(3,9),(4,9),(5,9),(7,9),(8,9),(9,9),(10,9),(12,9),(1,10),(20,10),(21,10),(22,10),(24,10),(25,10),(26,10),(27,10);
/*!40000 ALTER TABLE `enrolled` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-02 18:20:01
