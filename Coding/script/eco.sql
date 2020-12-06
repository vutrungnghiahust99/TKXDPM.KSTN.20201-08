SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ecodatabase
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ecodatabase`;

-- -----------------------------------------------------
-- Schema ecodatabase
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ecodatabase` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ecodatabase` ;

-- -----------------------------------------------------
-- Table `ecodatabase`.`dock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecodatabase`.`dock` ;
CREATE TABLE IF NOT EXISTS `ecodatabase`.`dock` (
  `dockID` CHAR(10) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `area` VARCHAR(45) NOT NULL,
  `numberOfDockingPoints` INT(11) NOT NULL,
  PRIMARY KEY (`dockID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecodatabase`.`bike`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecodatabase`.`bike` ;
CREATE TABLE IF NOT EXISTS `ecodatabase`.`bike` (
  `barcode` INT(11) NOT NULL,
  `isInUse` BIT(10) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `value` INT(11) NOT NULL,
  `priceForFirst30Minutes` INT(11) NOT NULL,
  `priceFor15MinutesAfter30Minutes` INT(11) NOT NULL,
  `remainBattery` INT(11) NOT NULL,
  `maxTime` FLOAT NOT NULL,
  `licensePlate` VARCHAR(45) NOT NULL,
  `dockID` CHAR(10) NOT NULL,
  PRIMARY KEY (`barcode`),
  -- INDEX `dockID_idx` (`dockID` ASC) VISIBLE,
  CONSTRAINT `dockID`
    FOREIGN KEY (`dockID`)
    REFERENCES `ecodatabase`.`dock` (`dockID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecodatabase`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecodatabase`.`user` ;
CREATE TABLE IF NOT EXISTS `ecodatabase`.`user` (
  `userID` INT(11) NOT NULL,
  `fullName` VARCHAR(45) NOT NULL,
  `phoneNumber` INT(11) NOT NULL,
  `province` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `mail` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `dateOfBirth` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecodatabase`.`card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecodatabase`.`card` ;

CREATE TABLE IF NOT EXISTS `ecodatabase`.`card` (
  `userID` INT(11) NOT NULL,
  `cardCode` VARCHAR(45) NOT NULL,
  `owner` VARCHAR(45) NOT NULL,
  `CVV` INT(11) NOT NULL,
  `expiredDate` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userID`),
  CONSTRAINT `userID`
    FOREIGN KEY (`userID`)
    REFERENCES `ecodatabase`.`user` (`userID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecodatabase`.`paymenttransaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecodatabase`.`paymenttransaction` ;

CREATE TABLE IF NOT EXISTS `ecodatabase`.`paymenttransaction` (
  `rentalCode` INT(11) NOT NULL,
  `cardCode` VARCHAR(45) NOT NULL,
  `owner` VARCHAR(45) NOT NULL,
  `transactionContent` VARCHAR(45) NOT NULL,
  `amount` INT(11) NOT NULL,
  `balance` INT(11) NOT NULL,
  `time` VARCHAR(45) NOT NULL,
  `day` varchar(45) NOT NULL,
  PRIMARY KEY (`time`,`day`,`cardCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecodatabase`.`rentbiketransaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecodatabase`.`rentbiketransaction` ;

CREATE TABLE IF NOT EXISTS `ecodatabase`.`rentbiketransaction` (
  `rentalCode` INT(11) NOT NULL,
  `barcode` INT(11) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `rentBikecodatabasest` INT(11) NOT NULL,
  `owner` VARCHAR(45) NOT NULL,
  `priceFor30FirstMinutes` INT(11) NOT NULL,
  `priceFor15MinutesAfter30Minutes` INT(11) NOT NULL,
  `rentTime` VARCHAR(45) NOT NULL,
  `returnTime` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`rentalCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



INSERT INTO `dock` VALUES ('HLU','Đại học Luật Hà Nội','Số 1, Đường Nguyễn Chí Thanh','Hà Nội',20),('HUST','Đại học Bách Khoa Hà Nội','Số 1, Đại Cồ Việt, Quận Hai Bà Trưng','Hà Nội',20),('NAM','Data Sciencetist','Hà Nội`','Hà Nội',20),('NEU','Đại học Kinh tế quốc dân','Số 18, Giải Phóng, Quận Hai Bà Trưng','Hà Nội',20),('UTC','Đại học Giao thông vận tải','Số 1, Đường Cầu Giấy','Hà Nội',20),('XDA','Đại học Xây dựng','Số 10, Giải Phóng','Hà Nội',20);




INSERT INTO `bike` VALUES (20200001,_binary '\0','Xe đạp điện',1200000,15000,5000,60,4,'XDD-0001','HUST'),(20200002,_binary '\0\0','Xe máy điện',5000000,30000,10000,80,8,'XMD-0002','NEU'),(20200003,_binary '\0','Xe máy điện',5000000,30000,10000,100,10,'XMD-0003','HUST'),(20200004,_binary '\0','Xe đạp điện',1200000,15000,5000,100,10,'XDD-0004','XDA'),(20200005,_binary '\0\0','Xe máy điện',5000000,30000,10000,100,10,'XMD-0005','XDA'),(20200006,_binary '\0','Xe đạp điện',1200000,15000,5000,100,10,'XDD-0006','UTC'),(20200007,_binary '\0','Xe đạp điện',1200000,15000,5000,100,10,'XDD-0007','HLU'),(20200008,_binary '\0\0','Xe máy điện',5000000,30000,10000,100,10,'XMD-0008','HLU'),(20200009,_binary '\0','Xe máy điện',5000000,30000,10000,100,10,'XMD-0009','UTC'),(20200010,_binary '\0\0','Xe máy điện',5000000,30000,10000,100,10,'XMD-0010','HUST'),(20200011,_binary '\0','Xe máy điện',5000000,30000,10000,100,10,'XMD-0011','NEU');



INSERT INTO `user` VALUES (1,'Lê Thế Nam',989999999,'Phúc Thọ','92, Phố Chùa Quỳnh','nam.lt173265@sis.hust.edu.vn','88888888','21/01/1999');



INSERT INTO `card` VALUES (1,'12345678','Lê Thế Nam',8888,'31/12/2020');



INSERT INTO `paymenttransaction` VALUES 
(4869,'123456789','Lê Thế Nam','Cộng tiền',420000,1980000,'10:45','4/12/2020'),
(4869,'12345678','Lê Thế Nam','Đặt cọc',500000,1500000,'8:30','4/12/2020');




INSERT INTO `rentbiketransaction` VALUES (4869,20200005,'Xe máy điện',80000,'Lê Thế Nam',30000,10000,'8:30','10:45');
