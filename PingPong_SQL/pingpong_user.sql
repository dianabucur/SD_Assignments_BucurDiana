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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `mail` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `admin` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `mail_UNIQUE` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Diana',NULL,NULL,'dia.bucur@yahoo.com','parola',1),(3,'zz',20,'Gender','z','z',1),(4,'a',1,'Female','q','q',0),(5,'w',1,'Male','w','w',0),(6,'w',1,'Male','e','w',0),(7,'w',1,'Male','r','w',0),(8,'w',1,'Male','t','w',0),(9,'w',1,'Male','y','w',0),(10,'w',1,'Male','u','w',0),(11,'w',1,'Male','p','w',0),(12,'w',1,'Male','s','w',0),(13,'w',1,'Male','d','w',0),(16,'Diana Bucur',21,'Female','diana.dbucur@gmail.com','parola',1),(20,'Eric Dumea',21,'Male','dumeae@gmail.com','parola',0),(21,'Gog Andrei',21,'Male','goga@gmail.com','parola',0),(22,'Alexandra Biris',21,'Female','aleb@gmail.com','parola',0),(23,'Vlad Piscu',22,'Male','vladp@yahoo.com','parola',0),(24,'Detesan Claudiu',25,'Male','detec@yahoo.com','parola',0),(25,'Andreea Ionutas',19,'Female','andreeai@gmail.com','parola',0),(26,'Iulia Zaharia',24,'Female','iuliaz@gmail.com','parola',0),(27,'Pantis Vlad',26,'Male','pantisv@gmail.com','parola',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
