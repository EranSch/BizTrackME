CREATE DATABASE  IF NOT EXISTS `it351db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `it351db`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: it351db
-- ------------------------------------------------------
-- Server version	5.6.15-log

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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Test','Testman','123 Fake St.','(123) 456-7890'),(2,'Billy','McTestman','123 Fake St.','(123) 456-7890'),(3,'John','Testburgh','98 Super Drive','789-908-9843'),(4,'Trackpants','McGee','24 South Ave.','1-800-999-PANTS'),(5,'Will','Testerson','56 Junktown Lane','(970) 247-5212'),(6,'Fillibuster','McTest','1000 East Road','(154) 978-3982'),(7,'Frank en.','Stein','1280 Gulash Ct.','(980) 378-9831'),(8,'Hectar','Fruitfly','72 Westerly End','(456) 789-2019'),(9,'Jill','Testerelli','28 Underwood Alley','(679) 789-3982'),(10,'John','Johnston','45 NW 5th St','(783) 555-4567'),(11,'Frost E.','Snowmanshire','123 Arctic Cr.','(999) 123-8943'),(12,'Jill','McTosser','56 Eleven Ave','(123)  456-7848'),(13,'Till','McGreeson','5121 SW 6th St','(456) 873-3982'),(14,'Reginold','Coddle','50 Fifty St','(432) 387-9783'),(15,'Tina','Fey','78 SW J Blvd','(534) 784-2984'),(16,'Alan','Peterson','76 Fluid Drive','(543) 785-8920'),(17,'Bill','Peterson','76 Fluid Drive','(543) 785-8920'),(19,'Alex','Arreli','1232 Street St','(789) 987-9087'),(21,'Sally','Talsdale','123 Hello Drive','(843) 245-5498');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) NOT NULL,
  `sku` varchar(10) NOT NULL,
  `price` float NOT NULL,
  `color` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Jumbo Shrimp','SKU1983',23.99,'Pink'),(2,'Harry Oyster','SKU1984',59.1,NULL),(3,'Franken Berries','SKU1985',200.12,NULL),(4,'Frontal Lobe','SKU1986',45.89,'Pink'),(5,'Replacment Battery','SKU1987',99.99,NULL),(6,'Jittery Feline','SKU1988',100.25,NULL),(7,'Plump Vine','SKU1989',1.5,'Green'),(8,'Jackson Flipper','SKU1980',5000,NULL),(9,'Aftertast Perfume','SKU3409',99.95,NULL),(10,'Blocking Function','SKU8749',999.99,NULL),(11,'Jack Knife','SKU7489',487.45,'Silver'),(12,'Swilly Gig','SKU73871',200,NULL),(14,'Failed Drive','SKU0002',0.95,NULL),(15,'Flawed Capacitor','SKU7487',0.5,NULL),(16,'Flawed Microwave','SKU7488',55.6,NULL),(17,'350GB Floppy','SKU7584',8000,NULL),(20,'Test Product','SKU3487',99.99,'Red');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-01-27 19:23:22
