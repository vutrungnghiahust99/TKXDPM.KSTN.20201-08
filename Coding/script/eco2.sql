-- MySQL Workbench 8.0

DROP DATABASE IF EXISTS ecodatabase;
create DATABASE ecodatabase;

--
-- Table structure for table `bike`
--
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

USE ecodatabase;
DROP TABLE IF EXISTS bike;
CREATE TABLE `bike` (
  `barcode` int(11) NOT NULL,
  `isInUse` bit(10) NOT NULL,
  `type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `value` int(11) NOT NULL,
  `priceForFirst30Minutes` int(11) NOT NULL,
  `priceFor15MinutesAfter30Minutes` int(11) NOT NULL,
  `remainBattery` int(11) NOT NULL,
  `maxTime` float NOT NULL,
  `licensePlate` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dockID` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`barcode`),
  KEY `dockID_idx` (`dockID`),
  CONSTRAINT `dockID` FOREIGN KEY (`dockID`) REFERENCES `dock` (`dockID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `bike` WRITE;
/*!40000 ALTER TABLE `bike` DISABLE KEYS */;
INSERT INTO `bike` VALUES (20200001,_binary '\0','Xe đạp điện',1200000,15000,5000,60,4,'XDD-0001','HUST'),(20200002,_binary '\0\0','Xe máy điện',5000000,30000,10000,80,8,'XMD-0002','NEU'),(20200003,_binary '\0','Xe máy điện',5000000,30000,10000,100,10,'XMD-0003','HUST'),(20200004,_binary '\0','Xe đạp điện',1200000,15000,5000,100,10,'XDD-0004','XDA'),(20200005,_binary '\0\0','Xe máy điện',5000000,30000,10000,100,10,'XMD-0005','XDA'),(20200006,_binary '\0','Xe đạp điện',1200000,15000,5000,100,10,'XDD-0006','UTC'),(20200007,_binary '\0','Xe đạp điện',1200000,15000,5000,100,10,'XDD-0007','HLU'),(20200008,_binary '\0\0','Xe máy điện',5000000,30000,10000,100,10,'XMD-0008','HLU'),(20200009,_binary '\0','Xe máy điện',5000000,30000,10000,100,10,'XMD-0009','UTC'),(20200010,_binary '\0\0','Xe máy điện',5000000,30000,10000,100,10,'XMD-0010','HUST'),(20200011,_binary '\0','Xe máy điện',5000000,30000,10000,100,10,'XMD-0011','NEU');
/*!40000 ALTER TABLE `bike` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `user`
--

USE ecodatabase;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
DROP TABLE IF EXISTS user;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `fullName` varchar(45) NOT NULL,
  `phoneNumber` int(11) NOT NULL,
  `province` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `dateOfBirth` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Lê Thế Nam',989999999,'Phúc Thọ','92, Phố Chùa Quỳnh','nam.lt173265@sis.hust.edu.vn','88888888','21/01/1999');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `card`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
USE ecodatabase;
DROP TABLE IF EXISTS card;
CREATE TABLE `card` (
  `userID` int(11) NOT NULL,
  `cardCode` varchar(45) NOT NULL,
  `owner` varchar(45) NOT NULL,
  `CVV` int(11) NOT NULL,
  `expiredDate` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`),
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,'12345678','Lê Thế Nam',8888,'31/12/2020');
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
USE ecodatabase;
DROP TABLE IF EXISTS dock;
CREATE TABLE `dock` (
  `dockID` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `area` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `numberOfDockingPoints` int(11) NOT NULL,
  PRIMARY KEY (`dockID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dock`
--

LOCK TABLES `dock` WRITE;
/*!40000 ALTER TABLE `dock` DISABLE KEYS */;
INSERT INTO `dock` VALUES ('HLU','Đại học Luật Hà Nội','Số 1, Đường Nguyễn Chí Thanh','Hà Nội',20),('HUST','Đại học Bách Khoa Hà Nội','Số 1, Đại Cồ Việt, Quận Hai Bà Trưng','Hà Nội',20),('NAM','Data Sciencetist','Hà Nội`','Hà Nội',20),('NEU','Đại học Kinh tế quốc dân','Số 18, Giải Phóng, Quận Hai Bà Trưng','Hà Nội',20),('UTC','Đại học Giao thông vận tải','Số 1, Đường Cầu Giấy','Hà Nội',20),('XDA','Đại học Xây dựng','Số 10, Giải Phóng','Hà Nội',20);
/*!40000 ALTER TABLE `dock` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;


--
-- Table structure for table `paymenttransaction`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
USE ecodatabase;
DROP TABLE IF EXISTS paymenttransaction;
CREATE TABLE `paymenttransaction` (
  `rentalCode` int(11) NOT NULL,
  `cardCode` varchar(45) NOT NULL,
  `owner` varchar(45) NOT NULL,
  `transactionContent` varchar(45) NOT NULL,
  `amount` int(11) NOT NULL,
  `balance` int(11) NOT NULL,
  `time` varchar(45) NOT NULL,
  `day` varchar(45) NOT NULL,
  PRIMARY KEY (`time`,`day`,`cardCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `paymenttransaction` WRITE;
/*!40000 ALTER TABLE `paymenttransaction` DISABLE KEYS */;
INSERT INTO `paymenttransaction` VALUES (4869,'123456789','Lê Thế Nam','Cộng tiền',420000,1980000,'10:45','4/12/2020'),(4869,'12345678','Lê Thế Nam','Đặt cọc',500000,1500000,'8:30','4/12/2020');
/*!40000 ALTER TABLE `paymenttransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rentbiketransaction`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
USE ecodatabase;
DROP TABLE IF EXISTS rentbiketransaction;
CREATE TABLE `rentbiketransaction` (
  `rentalCode` int(11) NOT NULL,
  `barcode` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `rentBikeCost` int(11) NOT NULL,
  `owner` varchar(45) NOT NULL,
  `priceFor30FirstMinutes` int(11) NOT NULL,
  `priceFor15MinutesAfter30Minutes` int(11) NOT NULL,
  `rentTime` varchar(45) NOT NULL,
  `returnTime` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`rentalCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `rentbiketransaction` WRITE;
/*!40000 ALTER TABLE `rentbiketransaction` DISABLE KEYS */;
INSERT INTO `rentbiketransaction` VALUES (4869,20200005,'Xe máy điện',80000,'Lê Thế Nam',30000,10000,'8:30','10:45');
/*!40000 ALTER TABLE `rentbiketransaction` ENABLE KEYS */;
UNLOCK TABLES;

