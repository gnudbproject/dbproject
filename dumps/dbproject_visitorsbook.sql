-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: dbproject
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `visitorsbook`
--

DROP TABLE IF EXISTS `visitorsbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visitorsbook` (
  `vbcode` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(12) NOT NULL,
  `vbtext` varchar(200) NOT NULL,
  `vbdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vbcode`),
  KEY `idx_userid` (`userid`),
  CONSTRAINT `idx_userid` FOREIGN KEY (`userid`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitorsbook`
--

LOCK TABLES `visitorsbook` WRITE;
/*!40000 ALTER TABLE `visitorsbook` DISABLE KEYS */;
INSERT INTO `visitorsbook` VALUES (1,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:22:09'),(2,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:22:14'),(3,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:22:17'),(4,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:22:31'),(5,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:22:59'),(6,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:23:04'),(7,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:23:06'),(8,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:23:09'),(9,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:23:14'),(10,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:23:18'),(11,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:23:22'),(12,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:23:26'),(13,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:23:31'),(14,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:23:36'),(15,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:23:59'),(16,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:24:04'),(17,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:24:07'),(18,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:24:13'),(19,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:24:27'),(20,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:24:34'),(21,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:24:40'),(22,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 11:24:45'),(23,'coding','Starbucks Is Getting Sued Over The Unicorn Frappuccino','2017-05-07 12:37:52'),(24,'coding','5 smart apps that make email less of a pain','2017-05-07 13:31:56'),(25,'coding','Parameter index out of range','2017-05-07 13:38:06'),(26,'coding','Take a breather with these meditation apps ','2017-05-07 13:41:53'),(27,'coding','Apple jumps to lead wearable computing with smartwatch ','2017-05-07 13:42:34'),(28,'coding','Chinese phones overtake Apple, Samsung at home ','2017-05-07 13:48:14'),(29,'coding','How to watch Netflix when you donât have an internet connection','2017-05-07 13:53:46'),(30,'coding','How to Make Better PasswordsâOnes You Can Remember','2017-05-07 13:54:52'),(31,'coding','Apple starting US tech manufacturing fund with $1 bn','2017-05-07 14:58:34');
/*!40000 ALTER TABLE `visitorsbook` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-11 18:49:50
