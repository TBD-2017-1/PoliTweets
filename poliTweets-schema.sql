CREATE DATABASE  IF NOT EXISTS `PoliTweets` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `PoliTweets`;
-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: PoliTweets
-- ------------------------------------------------------
-- Server version	5.7.18-0ubuntu0.16.04.1

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuario_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `conglomerado`
--

DROP TABLE IF EXISTS `conglomerado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conglomerado` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `cuentaTwitter` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `conglomerado_keyword`
--

DROP TABLE IF EXISTS `conglomerado_keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conglomerado_keyword` (
  `id` int(11) NOT NULL,
  `idconglomerado` int(11) NOT NULL,
  `idkeyword` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idconglomerado_idx` (`idconglomerado`),
  KEY `idkeyword_idx` (`idkeyword`),
  CONSTRAINT `conglome` FOREIGN KEY (`idconglomerado`) REFERENCES `conglomerado` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `key` FOREIGN KEY (`idkeyword`) REFERENCES `keyword` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `conglomerado_metrica`
--

DROP TABLE IF EXISTS `conglomerado_metrica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conglomerado_metrica` (
  `id` int(11) NOT NULL,
  `idconglomerado` int(11) NOT NULL,
  `idmetrica` int(11) NOT NULL,
  `valor` decimal(10,0) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `conglomer_idx` (`idconglomerado`),
  KEY `metric_idx` (`idmetrica`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `keyword`
--

DROP TABLE IF EXISTS `keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyword` (
  `id` int(11) NOT NULL,
  `value` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `metrica`
--

DROP TABLE IF EXISTS `metrica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `metrica` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido` (
  `id` int(11) NOT NULL,
  `idconglomerado` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `cuentaTwitter` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  KEY `idconglomerado_idx` (`idconglomerado`),
  CONSTRAINT `idconglomerado` FOREIGN KEY (`idconglomerado`) REFERENCES `conglomerado` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `partido_metrica`
--

DROP TABLE IF EXISTS `partido_metrica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido_metrica` (
  `id` int(11) NOT NULL,
  `idpartido` int(11) NOT NULL,
  `idmetrica` int(11) NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `valor` decimal(10,0) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `partido_idx` (`idpartido`),
  KEY `metrica_idx` (`idmetrica`),
  CONSTRAINT `metrica` FOREIGN KEY (`idmetrica`) REFERENCES `metrica` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `partido` FOREIGN KEY (`idpartido`) REFERENCES `partido` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `politico`
--

DROP TABLE IF EXISTS `politico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `politico` (
  `id` int(11) NOT NULL,
  `idpartido` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `cuentaTwitter` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parti_idx` (`idpartido`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `politico_keyword`
--

DROP TABLE IF EXISTS `politico_keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `politico_keyword` (
  `idpolitico_keyword` int(11) NOT NULL,
  `idkeyword` int(11) NOT NULL,
  `idpolitico` int(11) NOT NULL,
  PRIMARY KEY (`idpolitico_keyword`),
  KEY `idkeyword_idx` (`idkeyword`),
  KEY `polit_idx` (`idpolitico`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `politico_metrica`
--

DROP TABLE IF EXISTS `politico_metrica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `politico_metrica` (
  `idpolitico_metrica` int(11) NOT NULL,
  `idpolitico` int(11) NOT NULL,
  `idmetrica` int(11) NOT NULL,
  `valor` float DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`idpolitico_metrica`),
  KEY `idpolitico_idx` (`idpolitico`),
  KEY `metr_idx` (`idmetrica`),
  CONSTRAINT `idpolitico` FOREIGN KEY (`idpolitico`) REFERENCES `politico` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-08 10:13:24
