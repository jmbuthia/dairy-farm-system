CREATE DATABASE  IF NOT EXISTS `sdcms` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sdcms`;
-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: sdcms
-- ------------------------------------------------------
-- Server version	5.5.44-0+deb8u1

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
-- Table structure for table `cow`
--

DROP TABLE IF EXISTS `cow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cow` (
  `ownerIdNo` int(11) NOT NULL,
  `cowtag` varchar(45) NOT NULL,
  `breed` varchar(45) DEFAULT NULL,
  `cowName` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `fathersTag` varchar(45) DEFAULT NULL,
  `mothersTag` varchar(45) DEFAULT NULL,
  `colour` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  PRIMARY KEY (`ownerIdNo`,`cowtag`),
  UNIQUE KEY `cowtag_UNIQUE` (`cowtag`),
  CONSTRAINT `fk_cow_1` FOREIGN KEY (`ownerIdNo`) REFERENCES `farmer` (`idNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cow`
--

LOCK TABLES `cow` WRITE;
/*!40000 ALTER TABLE `cow` DISABLE KEYS */;
INSERT INTO `cow` VALUES (12345,'cowtag','breed','cowName','status','fathersTag','mothersTag','colour','2017-01-24'),(12345,'cw13','fffff','fffffff','Sold','fffff','ffffff','ffffff',NULL),(12345678,'cw12','freshian','njeri','alive','bull45','cw13','black','2014-12-20'),(25456988,'cw20','jjjj','bbh','alive','cw20','cw20','Black-White','2017-01-25'),(25456988,'cw21','wwww','wwww','alive','bull20','cw12','Brown','2017-01-18'),(25456988,'cw70','dddd','dddd','alive','cw70','cw70','Brown','2017-01-18'),(25456988,'cw99','gggg','karedi','alive','bbbbbb','nbbbb','Black','2017-01-26'),(65489254,'cw98','jhhh','jhj','alive','hhjj','juh','Brown','2017-05-24');
/*!40000 ALTER TABLE `cow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dairyMeal`
--

DROP TABLE IF EXISTS `dairyMeal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dairyMeal` (
  `cowtag` varchar(20) NOT NULL,
  `amountInKg` double DEFAULT NULL,
  `pricePerKg` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cowtag`,`time`),
  CONSTRAINT `fk_dairyMeal_1` FOREIGN KEY (`cowtag`) REFERENCES `cow` (`cowtag`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dairyMeal`
--

LOCK TABLES `dairyMeal` WRITE;
/*!40000 ALTER TABLE `dairyMeal` DISABLE KEYS */;
INSERT INTO `dairyMeal` VALUES ('cw12',20.8,37.78,785.824,'2017-01-24 22:40:08'),('cw20',16,66,1056,'2017-01-30 23:27:16');
/*!40000 ALTER TABLE `dairyMeal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deworm`
--

DROP TABLE IF EXISTS `deworm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deworm` (
  `cowtag` varchar(20) NOT NULL,
  `nameOfMedicine` varchar(45) DEFAULT NULL,
  `amountOfDose` double DEFAULT NULL,
  `pricePerDose` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cowtag`,`time`),
  CONSTRAINT `fk_deworming_1` FOREIGN KEY (`cowtag`) REFERENCES `cow` (`cowtag`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deworm`
--

LOCK TABLES `deworm` WRITE;
/*!40000 ALTER TABLE `deworm` DISABLE KEYS */;
INSERT INTO `deworm` VALUES ('cw12','nilzan',20.8,37.78,785.824,'2017-01-24 21:02:51'),('cw20','bhjn',3.761,17.733,66.693813,'2017-01-29 04:00:57'),('cw20','vvvvv',0.08,10,0.8,'2017-01-29 17:57:44'),('cw20','fffff',1.2,180.1,216.12,'2017-01-29 17:58:14'),('cw20','frrrr',3.3333,27.99999,93.332366667,'2017-01-29 17:59:10'),('cw20','dddddddd',5.768,23.3356,134.5997408,'2017-01-29 18:00:49'),('cw20','ddd',5,12.5,62.5,'2017-01-31 23:31:05'),('cw70','jhbh',2.1,23.2,48.72,'2017-01-29 03:57:09');
/*!40000 ALTER TABLE `deworm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diseases`
--

DROP TABLE IF EXISTS `diseases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diseases` (
  `cowtag` varchar(20) NOT NULL,
  `nameOfDisease` varchar(45) DEFAULT NULL,
  `nameOfMedicine` varchar(45) DEFAULT NULL,
  `amountOfDose` double DEFAULT NULL,
  `pricePerDose` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cowtag`,`time`),
  CONSTRAINT `fk_diseases_1` FOREIGN KEY (`cowtag`) REFERENCES `cow` (`cowtag`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diseases`
--

LOCK TABLES `diseases` WRITE;
/*!40000 ALTER TABLE `diseases` DISABLE KEYS */;
INSERT INTO `diseases` VALUES ('cw12','digana','jetxoo',20.8,37.78,785.824,'2017-01-24 21:05:02'),('cw20','foot','yellow',0.4,600,240,'2017-01-30 21:55:09'),('cw20','gggg','hggg',2,30,60,'2017-01-31 00:09:31');
/*!40000 ALTER TABLE `diseases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `farmer`
--

DROP TABLE IF EXISTS `farmer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `farmer` (
  `idNo` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `isAccepted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `idNo_UNIQUE` (`idNo`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `farmer`
--

LOCK TABLES `farmer` WRITE;
/*!40000 ALTER TABLE `farmer` DISABLE KEYS */;
INSERT INTO `farmer` VALUES (45555555,'admin','Admin','Admin','Nakuru','1999-01-05',1),(1234,'emailAddress','firstName','lastName','location','2017-01-24',1),(12345,'emailAddress1','firstName','lastName','location','2017-01-24',1),(12333255,'fxdxdxd','jgh','ggcfd','hgftdd','1999-01-04',1),(65489254,'h@h','hkjj','hhhhhh','Nakuru','1999-01-31',1),(25456988,'j@j','hhhhh','ggcfd','ggggggg','1999-01-12',1),(12345678,'jmbuthia12@gmail.com','johnson','mbuthia','nakuru','2017-01-01',1),(12347,'johnson','firstName','lastName','location','2017-01-25',1),(87654321,'johnson@gmail.com','johnson','mbuthia','nakuru','2017-01-01',0),(55662255,'k@k','jjf','hhh','Nairobi','1999-01-13',0),(45555666,'l@l','dddd','dddd','null','1990-12-12',0),(54879622,'p@p','ddddd','juhyh','null','1990-12-12',0),(45454545,'q@q','jpj','mbutgi','Nakuru','1999-04-14',0),(32003697,'sthiga@gmailcom','stephen','thiga','Nakuru','1999-02-04',0),(65825888,'u@u','lkj','dff','Nakuru','1999-02-02',0),(12548632,'vet@vet.com','vet','vet','Nakuru','1999-06-01',1),(55555555,'veterinary','veterinary','veterinary','Nakuru','1999-02-02',1);
/*!40000 ALTER TABLE `farmer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hay`
--

DROP TABLE IF EXISTS `hay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hay` (
  `cowtag` varchar(20) NOT NULL,
  `balesUsed` double DEFAULT NULL,
  `pricePerBale` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cowtag`,`time`),
  CONSTRAINT `fk_hay_1` FOREIGN KEY (`cowtag`) REFERENCES `cow` (`cowtag`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hay`
--

LOCK TABLES `hay` WRITE;
/*!40000 ALTER TABLE `hay` DISABLE KEYS */;
INSERT INTO `hay` VALUES ('cw12',20.8,37.78,785.824,'2017-01-24 22:35:31'),('cw20',2,230,460,'2017-01-30 21:48:19'),('cw98',1,150,150,'2017-05-28 14:41:51');
/*!40000 ALTER TABLE `hay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `email` varchar(20) NOT NULL,
  `password` longtext NOT NULL,
  `category` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`email`),
  CONSTRAINT `fk_login_1` FOREIGN KEY (`email`) REFERENCES `farmer` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('admin','7JTC0UIn52+PK9mUKnsWTA==','admin'),('fxdxdxd','wsnx3Ps8kCbDAJPDgWBbXg==','farmer'),('h@h','uzVngG1zYtDJHJcEydmdpA==','farmer'),('j@j','uzVngG1zYtDJHJcEydmdpA==','farmer'),('jmbuthia12@gmail.com','uzVngG1zYtDJHJcEydmdpA==','admin'),('johnson','johnson','farmer'),('k@k','RA+2chVVDL4c9Tb4blq2Kcbrkk/LvyktD8QSY+A6R0s=','farmer'),('l@l','RA+2chVVDL4c9Tb4blq2Kcbrkk/LvyktD8QSY+A6R0s=','farmer'),('p@p','RA+2chVVDL4c9Tb4blq2Kcbrkk/LvyktD8QSY+A6R0s=','farmer'),('q@q','uzVngG1zYtDJHJcEydmdpA==','farmer'),('sthiga@gmailcom','ZWbK+Lboj1+FE593hKsJaA==','farmer'),('u@u','RA+2chVVDL4c9Tb4blq2Kcbrkk/LvyktD8QSY+A6R0s=','farmer'),('vet@vet.com','uzVngG1zYtDJHJcEydmdpA==','veterinary'),('veterinary','93P9vew7hYAQXBMFTJ74Vw==','veterinary');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `milkProduced`
--

DROP TABLE IF EXISTS `milkProduced`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `milkProduced` (
  `cowtag` varchar(45) NOT NULL,
  `amountOfLitres` double DEFAULT NULL,
  `pricePerLitre` double DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`cowtag`,`time`),
  CONSTRAINT `fk_milkProduced_1` FOREIGN KEY (`cowtag`) REFERENCES `cow` (`cowtag`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `milkProduced`
--

LOCK TABLES `milkProduced` WRITE;
/*!40000 ALTER TABLE `milkProduced` DISABLE KEYS */;
INSERT INTO `milkProduced` VALUES ('cw12',20,30,'2017-01-24 16:27:45',600),('cw12',20.8,37.78,'2017-01-24 16:38:45',785.8240000000001),('cw12',20.8,37.78,'2017-01-24 16:56:00',785.8240000000001),('cw12',20.8,37.78,'2017-01-24 18:19:49',785.824),('cw12',20.8,37.78,'2017-01-24 18:20:32',785.824),('cw12',124.17,46.67,'2017-01-24 18:23:24',5795.0139),('cw20',2.354,45.6,'2017-01-30 17:24:36',107.3424),('cw20',20.23,46.67,'2017-01-30 17:48:58',944.1341),('cw70',0.988,28.754,'2017-02-06 16:49:53',28.408952),('cw98',20,60,'2017-05-28 14:42:15',1200);
/*!40000 ALTER TABLE `milkProduced` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recommendationRequest`
--

DROP TABLE IF EXISTS `recommendationRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recommendationRequest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cowtag` varchar(45) NOT NULL,
  `farmerEmail` varchar(45) NOT NULL,
  `message` longtext NOT NULL,
  `isRead` tinyint(2) NOT NULL DEFAULT '0',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`cowtag`,`farmerEmail`),
  KEY `fk_recommendationRequest_1_idx` (`cowtag`),
  KEY `fk_recommendationRequest_2_idx` (`farmerEmail`),
  CONSTRAINT `fk_recommendationRequest_1` FOREIGN KEY (`cowtag`) REFERENCES `cow` (`cowtag`) ON UPDATE CASCADE,
  CONSTRAINT `fk_recommendationRequest_2` FOREIGN KEY (`farmerEmail`) REFERENCES `farmer` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recommendationRequest`
--

LOCK TABLES `recommendationRequest` WRITE;
/*!40000 ALTER TABLE `recommendationRequest` DISABLE KEYS */;
INSERT INTO `recommendationRequest` VALUES (1,'cw20','j@j','',1,'2017-06-13 20:41:52'),(2,'cw20','j@j','knjhgvgfcf',0,'2017-06-12 19:41:31'),(3,'cw20','j@j','Hii ngombe yangu inalala kama <span style=\"color: rgb(204, 0, 0);\"><span style=\"font-weight: bold;\"><span style=\"text-decoration: underline;\">imesimama</span></span></span>, Shida ni gani <span style=\"font-style: italic;\">tafathali</span>??<br>',0,'2017-06-12 19:42:45'),(4,'cw20','j@j','<img src=\"http://localhost:8080/sdcms/javax.faces.resource/images/sdcms.png.xhtml\"><hr>Hii ngombe yangu inalala kama <span style=\"color: rgb(204, 0, 0);\"><span style=\"font-weight: bold;\"><span style=\"text-decoration: underline;\">imesimama</span></span></span>, Shida ni gani <span style=\"font-style: italic;\">tafathali</span>??',0,'2017-06-12 19:48:53');
/*!40000 ALTER TABLE `recommendationRequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recommendationResponse`
--

DROP TABLE IF EXISTS `recommendationResponse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recommendationResponse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cowtag` varchar(45) NOT NULL,
  `officerEmail` varchar(45) NOT NULL,
  `message` longtext NOT NULL,
  `responseFor` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_recommendationResponse_1_idx` (`responseFor`),
  KEY `fk_recommendationResponse_2_idx` (`cowtag`),
  KEY `fk_recommendationResponse_3_idx` (`officerEmail`),
  CONSTRAINT `fk_recommendationResponse_1` FOREIGN KEY (`responseFor`) REFERENCES `recommendationRequest` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_recommendationResponse_2` FOREIGN KEY (`cowtag`) REFERENCES `recommendationRequest` (`cowtag`) ON UPDATE CASCADE,
  CONSTRAINT `fk_recommendationResponse_3` FOREIGN KEY (`officerEmail`) REFERENCES `farmer` (`email`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recommendationResponse`
--

LOCK TABLES `recommendationResponse` WRITE;
/*!40000 ALTER TABLE `recommendationResponse` DISABLE KEYS */;
INSERT INTO `recommendationResponse` VALUES (1,'cw20','veterinary','tuma maswali ya maana<br>',1,'2017-06-13 20:30:00'),(2,'cw20','veterinary','hugygyfyf',1,'2017-06-13 20:41:52');
/*!40000 ALTER TABLE `recommendationResponse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salt`
--

DROP TABLE IF EXISTS `salt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salt` (
  `cowtag` varchar(20) NOT NULL,
  `nameOfSalt` varchar(45) DEFAULT NULL,
  `amountInKg` double DEFAULT NULL,
  `pricePerKg` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cowtag`,`time`),
  CONSTRAINT `fk_salting_1` FOREIGN KEY (`cowtag`) REFERENCES `cow` (`cowtag`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salt`
--

LOCK TABLES `salt` WRITE;
/*!40000 ALTER TABLE `salt` DISABLE KEYS */;
INSERT INTO `salt` VALUES ('cw12','molarplus',20.8,37.78,785.824,'2017-01-24 22:42:41'),('cw12','molarplus',20.8,37.78,785.824,'2017-01-24 22:43:19'),('cw20','hhhh',4,40,160,'2017-01-31 01:23:49'),('cw20','jhj',2,37,74,'2017-01-31 01:25:17');
/*!40000 ALTER TABLE `salt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silage`
--

DROP TABLE IF EXISTS `silage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `silage` (
  `cowtag` varchar(20) NOT NULL,
  `amountInKg` double DEFAULT NULL,
  `pricePerKg` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cowtag`,`time`),
  CONSTRAINT `fk_silage_1` FOREIGN KEY (`cowtag`) REFERENCES `cow` (`cowtag`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silage`
--

LOCK TABLES `silage` WRITE;
/*!40000 ALTER TABLE `silage` DISABLE KEYS */;
INSERT INTO `silage` VALUES ('cw12',20.8,37.78,785.824,'2017-01-24 22:37:16'),('cw20',20,13,260,'2017-01-30 23:22:27'),('cw20',6,66,396,'2017-01-30 23:24:28'),('cw20',6,4,24,'2017-01-31 00:10:02');
/*!40000 ALTER TABLE `silage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spray`
--

DROP TABLE IF EXISTS `spray`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spray` (
  `cowtag` varchar(20) NOT NULL,
  `nameOfAcaricide` varchar(45) DEFAULT NULL,
  `amountOfDose` double DEFAULT NULL,
  `pricePerDose` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cowtag`,`time`),
  CONSTRAINT `fk_spray_1` FOREIGN KEY (`cowtag`) REFERENCES `cow` (`cowtag`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spray`
--

LOCK TABLES `spray` WRITE;
/*!40000 ALTER TABLE `spray` DISABLE KEYS */;
INSERT INTO `spray` VALUES ('cw12','dip',20.8,37.78,785.824,'2017-01-24 22:45:47'),('cw20','hhg',3.21,148.9,477.969,'2017-01-31 01:26:55'),('cw20','hhh',5,189,945,'2017-01-31 01:29:59'),('cw20','huhu',8,257.48,2059.84,'2017-01-31 01:30:56');
/*!40000 ALTER TABLE `spray` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `water`
--

DROP TABLE IF EXISTS `water`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `water` (
  `cowtag` varchar(20) NOT NULL,
  `amountInLitres` double DEFAULT NULL,
  `pricePerLitre` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cowtag`,`time`),
  CONSTRAINT `fk_water_1` FOREIGN KEY (`cowtag`) REFERENCES `cow` (`cowtag`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `water`
--

LOCK TABLES `water` WRITE;
/*!40000 ALTER TABLE `water` DISABLE KEYS */;
INSERT INTO `water` VALUES ('cw12',20.8,37.78,785.824,'2017-01-24 20:57:35'),('cw12',20,30,600,'2017-01-24 20:58:29'),('cw20',100,0.01,1,'2017-01-30 19:08:29');
/*!40000 ALTER TABLE `water` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-13 23:49:23
