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
  `bikeCode` INT(11) NOT NULL,
  `isInUse` INT(10) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `value` INT(11) NOT NULL,
  `priceForFirst30Minutes` INT(11) NOT NULL,
  `priceFor15MinutesAfter30Minutes` INT(11) NOT NULL,
  `remainBattery` INT(11) NOT NULL,
  `maxTime` FLOAT NOT NULL,
  `licensePlate` VARCHAR(45) NOT NULL,
  `dockID` CHAR(10) NOT NULL,
  PRIMARY KEY (`bikeCode`),
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
  `email` VARCHAR(45) NOT NULL,
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
-- Table `ecodatabase`.`rentbiketransaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecodatabase`.`rentbiketransaction` ;

CREATE TABLE IF NOT EXISTS `ecodatabase`.`rentbiketransaction` (
  `rentalCode` VARCHAR(45) NOT NULL,
  `bikeCode` INT(11) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `rentBikeCost` INT(11) NOT NULL,
  `owner` VARCHAR(45) NOT NULL,
  `priceFor30FirstMinutes` INT(11) NOT NULL,
  `priceFor15MinutesAfter30Minutes` INT(11) NOT NULL,
  `rentTime` VARCHAR(45) NOT NULL,
  `returnTime` VARCHAR(45) NULL DEFAULT NULL,
  `deposit` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`rentalCode`),
  CONSTRAINT `bikeCode`
    FOREIGN KEY (`bikeCode`)
    REFERENCES `ecodatabase`.`bike` (`bikeCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `ecodatabase`.`paymenttransaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecodatabase`.`paymenttransaction` ;

CREATE TABLE IF NOT EXISTS `ecodatabase`.`paymenttransaction` (
  `rentalCode` VARCHAR(45) NOT NULL,
  `cardCode` VARCHAR(45) NOT NULL,
  `owner` VARCHAR(45) NOT NULL,
  `transactionContent` VARCHAR(45) NOT NULL,
  `amount` INT(11) NOT NULL,
  `time` VARCHAR(45) NOT NULL,
  `day` varchar(45) NOT NULL,
  PRIMARY KEY (`time`,`day`,`cardCode`),
  CONSTRAINT `rentalCode`
    FOREIGN KEY (`rentalCode`)
    REFERENCES `ecodatabase`.`rentbiketransaction` (`rentalCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



INSERT INTO `dock` VALUES
('HLU','Đại học Luật Hà Nội','Số 1, Đường Nguyễn Chí Thanh','Hà Nội',20),
('HUST','Đại học Bách Khoa Hà Nội','Số 1, Đại Cồ Việt, Quận Hai Bà Trưng','Hà Nội',20),
('TNL','Lord of Thunder','Phố Hồng Mai,Quận Hai Bà Trưng','Hà Nội',20),
('NEU','Đại học Kinh tế quốc dân','Số 18, Đường Giải Phóng, Quận Hai Bà Trưng','Hà Nội',20),
('UTC','Đại học Giao thông vận tải','Số 1, Đường Cầu Giấy','Hà Nội',20),
('XDA','Đại học Xây dựng','Số 10, Đường Giải Phóng','Hà Nội',4),
('DSL','B1 Bách Khoa','Số 1, Phố Tạ Quang Bửu','Hà Nội',20),
('C1','C1 Bách Khoa Hà Nội','Số 1, Đại Cồ Việt, Quận Hai Bà Trưng','Hà Nội',20),
('D6','D6 Bách Khoa Hà Nội','số 1, Đường Giải Phóng','Hà Nội',20),
('VINC','Vincom Phạm Ngọc Thạch','Số 18, Đường Phạm Ngọc Thạch','Hà Nội',20),
('FTU','Đại học Ngoại Thương','Số 1, Đường Láng','Hà Nội',20),
('TMU','Đại học Thương Mại','79 Đường Hồ Tùng Mậu, Mai Dịch, Cầu Giấy','Hà Nội',20);




INSERT INTO `bike` VALUES
(20200000,0,'Xe đạp điện',10000000,15000,5000,60,4,'XDD-200000','HUST'),
(20200001,0,'Xe đạp điện',1200000,15000,5000,60,4,'XDD-200001','HUST'),
(20200002,0,'Xe máy điện',2000000,30000,10000,80,8,'XMD-200002','NEU'),
(20200003,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200003','TNL'),
(20200004,0,'Xe đạp điện',1200000,15000,5000,100,10,'XDD-200004','XDA'),
(20200005,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200005','XDA'),
(20200006,0,'Xe đạp điện',1200000,15000,5000,100,10,'XDD-200006','UTC'),
(20200007,0,'Xe đạp điện',1200000,15000,5000,100,10,'XDD-200007','HLU'),
(20200008,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200008','HLU'),
(20200009,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200009','UTC'),
(20200010,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200010','HUST'),
(20200011,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200011','DSL'),
(20200012,0,'Xe đạp điện',1200000,15000,5000,60,4,'XDD-200012','C1'),
(20200013,0,'Xe máy điện',2000000,30000,10000,80,8,'XMD-200013','DSL'),
(20200014,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200014','FTU'),
(20200015,0,'Xe đạp điện',1200000,15000,5000,100,10,'XDD-200015','TMU'),
(20200016,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200016','VINC'),
(20200017,0,'Xe đạp điện',1200000,15000,5000,100,10,'XDD-200017','DSL'),
(20200018,0,'Xe đạp điện',1200000,15000,5000,100,10,'XDD-200018','C1'),
(20200019,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200019','VINC'),
(20200020,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200020','D6'),
(20200021,0,'Xe đạp điện',1200000,15000,5000,60,4,'XDD-200021','HUST'),
(20200022,0,'Xe máy điện',2000000,30000,10000,80,8,'XMD-200022','NEU'),
(20200023,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200023','TNL'),
(20200024,0,'Xe đạp điện',1200000,15000,5000,100,10,'XDD-200024','XDA'),
(20200025,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200025','XDA'),
(20200026,0,'Xe đạp điện',1200000,15000,5000,100,10,'XDD-200026','UTC'),
(20200027,0,'Xe đạp điện',1200000,15000,5000,100,10,'XDD-200027','HLU'),
(20200028,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200028','HLU'),
(20200029,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200029','UTC'),
(20200030,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200030','HUST'),
(20200031,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200031','DSL'),
(20200032,0,'Xe đạp điện',1200000,15000,5000,60,4,'XDD-200032','C1'),
(20200033,0,'Xe máy điện',2000000,30000,10000,80,8,'XMD-200033','DSL'),
(20200034,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200034','FTU'),
(20200035,0,'Xe đạp điện',1200000,15000,5000,100,10,'XDD-2000035','TMU'),
(20200036,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200036','VINC'),
(20200037,0,'Xe đạp điện',1200000,15000,5000,100,10,'XDD-200037','DSL'),
(20200038,0,'Xe đạp điện',1200000,15000,5000,100,10,'XDD-200038','C1'),
(20200039,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200039','VINC'),
(20200040,0,'Xe máy điện',2000000,30000,10000,100,10,'XMD-200040','D6');



INSERT INTO `user` VALUES (1,'Group 8',0986172930,'Phúc Thọ','92, Phố Chùa Quỳnh','nam.lt173265@sis.hust.edu.vn','88888888','1999-01-21');



INSERT INTO `card` VALUES (1,'118131_group8_2020','Group 8',427,'1125');

INSERT INTO `rentbiketransaction` VALUES
('202000012020-12-12 10:09:39',20200001,'Xe đạp điện',25000,'Group 8',15000,5000,'2020-12-12 10:09:39','2020-12-7 11:10:34', 480000),
('202000012020-12-12 12:09:39',20200006,'Xe máy điện',25000,'Group 8',15000,5000,'2020-12-12 12:09:39','2020-12-12 13:09:54', 480000);

INSERT INTO `paymenttransaction` VALUES
('202000012020-12-12 10:09:39','118131_group8_2020','Group 8','Trừ tiền cọc',480000,'10:09:54','2020-12-12'),
('202000012020-12-12 10:09:39','118131_group8_2020','Group 8','Hoàn tiền',455000,'11:10:34','2020-12-12'),
('202000012020-12-12 12:09:39','118131_group8_2020','Group 8','Trừ tiền cọc',480000,'12:09:54','2020-12-12'),
('202000012020-12-12 12:09:39','118131_group8_2020','Group 8','Hoàn tiền',455000,'13:09:54','2020-12-12');

