CREATE DATABASE  IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `library`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: library
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
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `idAuthor` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `yearOfBirth` int(11) DEFAULT NULL,
  `yearOfDeath` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAuthor`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'NIN','Anaïs',1903,1977),(2,'BURROUGHS','William S.',1914,1997),(3,'HOBB','Robin',1952,NULL),(4,'SIDDHARTHA','Gautama',-566,-483),(5,'MARC AURÈLE','',121,180),(8,'THILLIEZ','Franck',1973,NULL),(9,'DANTEC','Maurice G.',1959,2016),(10,'A RENSEIGNER',NULL,NULL,NULL),(12,'COLOANE','Francisco',1910,2002);
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `isbn` bigint(40) unsigned NOT NULL,
  `title` varchar(45) NOT NULL,
  `subtitle` varchar(45) DEFAULT NULL,
  `Author_idAuthor` int(11) NOT NULL,
  `Catalog_idCatalog` int(11) NOT NULL,
  PRIMARY KEY (`isbn`),
  KEY `fk_Book_Author1_idx` (`Author_idAuthor`),
  KEY `fk_Book_Catalog1_idx` (`Catalog_idCatalog`),
  CONSTRAINT `fk_Book_Author1` FOREIGN KEY (`Author_idAuthor`) REFERENCES `author` (`idAuthor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Book_Catalog1` FOREIGN KEY (`Catalog_idCatalog`) REFERENCES `catalog` (`idCatalog`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (454663,'Essai','',4,9),(2070466299,'Les racines du mal','',9,4),(2070784509,'Le festin nu','',2,1),(2080700162,'Pensées pour moi-même','',5,6),(2253025214,'Venus Erotica','',1,1),(2253027472,'Les petits oiseaux','',1,1),(2266205048,'L\'anneau de Moébius','',8,3),(2266262319,'Angor','',8,3),(2369141174,'Antartida','',12,1),(2369141204,'Le sillage de la baleine','',12,1),(2752908008,'Tierra del fuego','',12,1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borrow` (
  `Subscriber_idSubscriber` int(11) NOT NULL,
  `Copy_idCopy` int(11) NOT NULL,
  `current` tinyint(1) DEFAULT NULL,
  `Copy_Book_isbn` bigint(40) unsigned NOT NULL,
  PRIMARY KEY (`Subscriber_idSubscriber`,`Copy_idCopy`,`Copy_Book_isbn`),
  KEY `fk_Borrow_Copy1_idx` (`Copy_idCopy`),
  KEY `fk_Borrow_Isbn_idx` (`Copy_Book_isbn`),
  CONSTRAINT `fk_Borrow_Copy1` FOREIGN KEY (`Copy_idCopy`) REFERENCES `copy` (`idCopy`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Borrow_Isbn` FOREIGN KEY (`Copy_Book_isbn`) REFERENCES `copy` (`Book_isbn`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Borrow_Subscriber1` FOREIGN KEY (`Subscriber_idSubscriber`) REFERENCES `subscriber` (`idSubscriber`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow` VALUES (1,5,0,2266205048),(1,21,1,2752908008),(2,1,1,2253027472),(2,4,1,2266205048),(2,8,1,2070466299),(2,15,0,2266205048),(2,19,1,2752908008),(2,24,0,2369141174),(3,1,0,2253027472),(3,3,0,2253025214),(4,6,0,2070784509),(4,7,1,2266262319),(4,10,1,2070466299),(4,20,0,2369141174),(7,4,0,2266205048),(7,6,0,2070784509),(7,7,0,2266262319),(7,8,0,2070466299),(7,12,0,2080700162),(7,19,0,2752908008),(7,21,0,2752908008),(12,20,1,2369141174),(12,23,1,2369141204),(15,3,0,2253025214);
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalog`
--

DROP TABLE IF EXISTS `catalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalog` (
  `idCatalog` int(11) NOT NULL AUTO_INCREMENT,
  `catalogName` varchar(45) NOT NULL,
  PRIMARY KEY (`idCatalog`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog`
--

LOCK TABLES `catalog` WRITE;
/*!40000 ALTER TABLE `catalog` DISABLE KEYS */;
INSERT INTO `catalog` VALUES (1,'Roman'),(2,'Fantasy'),(3,'Thriller'),(4,'Série noire'),(5,'Manga'),(6,'Philosophie'),(9,'A renseigner'),(10,'Comics'),(12,'Album de jeunesse');
/*!40000 ALTER TABLE `catalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `copy`
--

DROP TABLE IF EXISTS `copy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `copy` (
  `idCopy` int(11) NOT NULL AUTO_INCREMENT,
  `available` tinyint(1) NOT NULL DEFAULT '1',
  `underRepair` tinyint(1) NOT NULL DEFAULT '0',
  `Book_isbn` bigint(40) unsigned NOT NULL,
  PRIMARY KEY (`idCopy`),
  KEY `fk_Copy_Book1_idx` (`Book_isbn`),
  CONSTRAINT `fk_Copy_Book1` FOREIGN KEY (`Book_isbn`) REFERENCES `book` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `copy`
--

LOCK TABLES `copy` WRITE;
/*!40000 ALTER TABLE `copy` DISABLE KEYS */;
INSERT INTO `copy` VALUES (1,0,0,2253027472),(2,1,0,2080700162),(3,1,0,2253025214),(4,0,0,2266205048),(5,1,0,2266205048),(6,1,0,2070784509),(7,0,0,2266262319),(8,0,0,2070466299),(9,1,0,2266262319),(10,0,0,2070466299),(11,0,1,2253025214),(12,1,0,2080700162),(13,1,0,2070784509),(14,1,0,2253027472),(15,1,0,2266205048),(16,0,0,2369141204),(18,1,0,2369141204),(19,0,0,2752908008),(20,0,0,2369141174),(21,0,0,2752908008),(22,1,0,2253025214),(23,0,0,2369141204),(24,1,0,2369141174);
/*!40000 ALTER TABLE `copy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriber`
--

DROP TABLE IF EXISTS `subscriber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscriber` (
  `idSubscriber` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  PRIMARY KEY (`idSubscriber`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriber`
--

LOCK TABLES `subscriber` WRITE;
/*!40000 ALTER TABLE `subscriber` DISABLE KEYS */;
INSERT INTO `subscriber` VALUES (1,'JAOUAD','Fahd'),(2,'CASTEL','Eric'),(3,'GUIGUE','Phil'),(4,'DESCOMBES','Franck'),(7,'DERAMOND','Sigrid'),(8,'DU LAC','La dame'),(12,'LE BIHAN','Yannick'),(15,'TITECA','Raynald');
/*!40000 ALTER TABLE `subscriber` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'library'
--

--
-- Dumping routines for database 'library'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-31  9:57:40
