-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: pharmacydb
-- ------------------------------------------------------
-- Server version	5.6.39-log

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
-- Table structure for table `appointmentmedicines`
--

DROP TABLE IF EXISTS `appointmentmedicines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointmentmedicines` (
  `idAppointmentMedicines` int(11) NOT NULL AUTO_INCREMENT,
  `group` varchar(45) NOT NULL,
  `description` longtext NOT NULL,
  PRIMARY KEY (`idAppointmentMedicines`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointmentmedicines`
--

LOCK TABLES `appointmentmedicines` WRITE;
/*!40000 ALTER TABLE `appointmentmedicines` DISABLE KEYS */;
INSERT INTO `appointmentmedicines` VALUES (1,'Антибактериальные средства','Бактерицидное действие офлоксацина связано с ингибированием ДНК-гиразы и топоизомеразы IV'),(2,'Бронхолитические препараты','При приеме внутрь бромгексин практически полностью (99 %) всасывается в течение 30 минут. Биодоступность составляет 80 % вследствие эффекта \"первого прохождения\" через печень.'),(3,'Антиоксидантные препараты',' вещества, которые ингибируют окисление; любое из многочисленных химических веществ, в том числе естественные продукты деятельности организма и питательные вещества, поступающие с пищей, которые могут нейтрализовать окислительное действие свободных радикалов и других веществ[1]. Рассматриваются преимущественно в контексте окисления органических соединений.'),(4,'Витамины','группа низкомолекулярных органических соединений относительно простого строения и разнообразной химической природы. Это сборная по химической природе группа органических веществ, объединённая по признаку абсолютной необходимости их для гетеротрофного организма в качестве составной части пищи. '),(5,'Глюкокортикоиды','Глюкокортикоиды — стероидные гормоны, синтезируемые корой надпочечников. Природные глюкокортикоиды и их синтетические аналоги применяются в медицине при надпочечниковой недостаточности');
/*!40000 ALTER TABLE `appointmentmedicines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education`
--

DROP TABLE IF EXISTS `education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `education` (
  `idEducation` int(11) NOT NULL AUTO_INCREMENT,
  `modeEducation` varchar(45) NOT NULL,
  PRIMARY KEY (`idEducation`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education`
--

LOCK TABLES `education` WRITE;
/*!40000 ALTER TABLE `education` DISABLE KEYS */;
INSERT INTO `education` VALUES (1,'высшее образование'),(2,'среднее образование'),(3,'техническое образование');
/*!40000 ALTER TABLE `education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `idEmployee` int(11) NOT NULL AUTO_INCREMENT,
  `lastName` varchar(45) NOT NULL,
  `firstName` varchar(45) CHARACTER SET utf8 NOT NULL,
  `middleName` varchar(45) CHARACTER SET utf8 NOT NULL,
  `address` varchar(45) CHARACTER SET utf8 NOT NULL,
  `numberPhone` varchar(45) CHARACTER SET utf8 NOT NULL,
  `idEducationEmployee` int(11) NOT NULL,
  `idPositionEmployee` int(11) NOT NULL,
  `startWork` datetime NOT NULL,
  `salary` double NOT NULL,
  PRIMARY KEY (`idEmployee`),
  KEY `id_idx` (`idEducationEmployee`),
  KEY `idPositionEmployee_idx` (`idPositionEmployee`),
  CONSTRAINT `idEducationEmployee` FOREIGN KEY (`idEducationEmployee`) REFERENCES `education` (`idEducation`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idPositionEmployee` FOREIGN KEY (`idPositionEmployee`) REFERENCES `position` (`idPosition`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Кузякин','Дмитрий','Александрович','г. Гродно','333444555',2,2,'2014-04-20 00:00:00',500),(2,'Свистоплясов','Дмитрий','Сергеевич','г. Гродно','121234456',2,2,'2012-04-20 18:00:00',450),(3,'Лазуренко','Анатолий','Дмитриевич','г. Гродно','778943232',1,1,'2011-02-20 16:00:00',600);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moderelease`
--

DROP TABLE IF EXISTS `moderelease`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moderelease` (
  `idModeRelease` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  PRIMARY KEY (`idModeRelease`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moderelease`
--

LOCK TABLES `moderelease` WRITE;
/*!40000 ALTER TABLE `moderelease` DISABLE KEYS */;
INSERT INTO `moderelease` VALUES (1,'по рецепту'),(2,'без рецепта');
/*!40000 ALTER TABLE `moderelease` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pharmacy`
--

DROP TABLE IF EXISTS `pharmacy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pharmacy` (
  `idPharmacy` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `numberPhone` varchar(45) NOT NULL,
  `licenses` varchar(45) NOT NULL,
  PRIMARY KEY (`idPharmacy`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pharmacy`
--

LOCK TABLES `pharmacy` WRITE;
/*!40000 ALTER TABLE `pharmacy` DISABLE KEYS */;
INSERT INTO `pharmacy` VALUES (1,'Аптека Центральная','г. Гродно, ул Советская д 39','80152223344','0001559958742');
/*!40000 ALTER TABLE `pharmacy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `placestorage`
--

DROP TABLE IF EXISTS `placestorage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `placestorage` (
  `idPlaceStorage` int(11) NOT NULL AUTO_INCREMENT,
  `placeStorage` varchar(45) NOT NULL,
  PRIMARY KEY (`idPlaceStorage`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `placestorage`
--

LOCK TABLES `placestorage` WRITE;
/*!40000 ALTER TABLE `placestorage` DISABLE KEYS */;
INSERT INTO `placestorage` VALUES (1,'Склад №1'),(2,'Склад №2');
/*!40000 ALTER TABLE `placestorage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `idPosition` int(11) NOT NULL AUTO_INCREMENT,
  `position` varchar(45) NOT NULL,
  PRIMARY KEY (`idPosition`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'Администратор'),(2,'Продавец');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preparation`
--

DROP TABLE IF EXISTS `preparation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `preparation` (
  `idPreparation` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `idSupplierPreparation` int(11) NOT NULL,
  `idAppointmentMedicinesPreparation` int(11) NOT NULL,
  `idUnitPreparation` int(11) NOT NULL,
  `idPlaceStoragePreparation` int(11) NOT NULL,
  `releaseDate` datetime NOT NULL,
  `shelfDate` datetime NOT NULL,
  `cost` double NOT NULL,
  `idModeRaleasePreparation` int(11) NOT NULL,
  `countPreparation` int(11) NOT NULL,
  `idWayApplyPreparation` int(11) NOT NULL,
  PRIMARY KEY (`idPreparation`),
  KEY `idAppointmentMedicinesPreparation_idx` (`idAppointmentMedicinesPreparation`),
  KEY `idUnitPreparation_idx` (`idUnitPreparation`),
  KEY `idPlaceStoragePreparation_idx` (`idPlaceStoragePreparation`),
  KEY `idModeRaleasePreparation_idx` (`idModeRaleasePreparation`),
  KEY `idWayApplyPreparation_idx` (`idWayApplyPreparation`),
  KEY `idSupplierPreparation_idx` (`idSupplierPreparation`),
  CONSTRAINT `idAppointmentMedicinesPreparation` FOREIGN KEY (`idAppointmentMedicinesPreparation`) REFERENCES `appointmentmedicines` (`idAppointmentMedicines`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idModeRaleasePreparation` FOREIGN KEY (`idModeRaleasePreparation`) REFERENCES `moderelease` (`idModeRelease`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idPlaceStoragePreparation` FOREIGN KEY (`idPlaceStoragePreparation`) REFERENCES `placestorage` (`idPlaceStorage`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idSupplierPreparation` FOREIGN KEY (`idSupplierPreparation`) REFERENCES `supplier` (`idSupplier`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idUnitPreparation` FOREIGN KEY (`idUnitPreparation`) REFERENCES `unit` (`idUnit`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idWayApplyPreparation` FOREIGN KEY (`idWayApplyPreparation`) REFERENCES `wayapply` (`idWayApply`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preparation`
--

LOCK TABLES `preparation` WRITE;
/*!40000 ALTER TABLE `preparation` DISABLE KEYS */;
INSERT INTO `preparation` VALUES (1,'Мексидол',1,2,1,1,'2012-04-20 00:00:00','2014-04-20 00:00:00',7,1,4,1),(2,'Вермокс',2,2,2,1,'2010-03-20 14:00:00','2011-03-20 18:00:00',12,1,3,1),(3,'Лизобакт',3,1,1,2,'2017-09-15 00:00:00','2020-09-15 00:00:00',6.5,2,4,1);
/*!40000 ALTER TABLE `preparation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producer`
--

DROP TABLE IF EXISTS `producer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producer` (
  `idProducer` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `dateCreate` datetime NOT NULL,
  `numberPhone` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`idProducer`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producer`
--

LOCK TABLES `producer` WRITE;
/*!40000 ALTER TABLE `producer` DISABLE KEYS */;
INSERT INTO `producer` VALUES (1,'НПК Биотест ООО','г. Лида','2011-02-20 08:00:00','11999122','fors@mail.me'),(2,'БелАсептика ЗАО','г.Витебск','2014-03-20 07:00:00','4443337677','trust@mail.me'),(3,'ГАЛАСАР ИООО','г.Минск','2012-12-20 14:00:00','66223344','aptekaDwa@mail.me'),(4,'Борисовский завод медицинских препаратов ОАО','г.Гродно','2011-04-20 00:00:00','777666555','aptekaa@mail.me'),(5,'МАЛКУТ ЗАО ТМ ВИТУС','г.Брест','2031-02-20 00:00:00','345887532','frs@mail.me');
/*!40000 ALTER TABLE `producer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recede`
--

DROP TABLE IF EXISTS `recede`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recede` (
  `idRecede` int(11) NOT NULL AUTO_INCREMENT,
  `typeInvoice` varchar(45) NOT NULL,
  `dateComing` datetime NOT NULL,
  `idPlaceStorageRecede` int(11) NOT NULL,
  `idSupplierRecede` int(11) NOT NULL,
  `idPreparationRecede` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `cost` double NOT NULL,
  PRIMARY KEY (`idRecede`),
  KEY `idPlaceStorageRecipe_idx` (`idPlaceStorageRecede`),
  KEY `idPreparationRecede_idx` (`idPreparationRecede`),
  KEY `idSupplierRecede_idx` (`idSupplierRecede`),
  CONSTRAINT `idPlaceStorageRecipe` FOREIGN KEY (`idPlaceStorageRecede`) REFERENCES `placestorage` (`idPlaceStorage`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idPreparationRecede` FOREIGN KEY (`idPreparationRecede`) REFERENCES `preparation` (`idPreparation`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idSupplierRecede` FOREIGN KEY (`idSupplierRecede`) REFERENCES `supplier` (`idSupplier`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recede`
--

LOCK TABLES `recede` WRITE;
/*!40000 ALTER TABLE `recede` DISABLE KEYS */;
INSERT INTO `recede` VALUES (14,'приход','2013-04-20 18:00:00',1,1,2,4,5),(16,'приход','2011-04-20 00:00:00',1,1,1,6,5);
/*!40000 ALTER TABLE `recede` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `idrole` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idrole`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Администратор','admin','admin'),(2,'Продавец','user',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `idSupplier` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) CHARACTER SET utf8 NOT NULL,
  `agent` varchar(45) CHARACTER SET utf8 NOT NULL,
  `position` varchar(45) CHARACTER SET utf8 NOT NULL,
  `address` varchar(45) CHARACTER SET utf8 NOT NULL,
  `numberPhone` varchar(45) CHARACTER SET utf8 NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`idSupplier`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'А-фарм','Василий','Директор','г. Минск','1112233','afarm@mail.com'),(2,'Аптека-холдинг','Сергей','Зам директора','г. Гродно','4455232','hold@mail.com'),(3,'Архимед','Аркадий','Директор','г. Минск','5554433','arh@mail.com'),(4,'Верона','Сергей','Менеджер','г. Минск','4466882','veron@mail.com');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit` (
  `idUnit` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  PRIMARY KEY (`idUnit`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1,'шт'),(2,'мл'),(3,'г');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wayapply`
--

DROP TABLE IF EXISTS `wayapply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wayapply` (
  `idWayApply` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  PRIMARY KEY (`idWayApply`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wayapply`
--

LOCK TABLES `wayapply` WRITE;
/*!40000 ALTER TABLE `wayapply` DISABLE KEYS */;
INSERT INTO `wayapply` VALUES (1,'внутреннее'),(4,'наружное');
/*!40000 ALTER TABLE `wayapply` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-27 16:01:41
