

DROP TABLE IF EXISTS `appointment`;
CREATE TABLE IF NOT EXISTS `appointment` (
  `appointmentID` int(200) NOT NULL AUTO_INCREMENT,
  `userID` int(200) NOT NULL,
  `doctorID` int(200) NOT NULL,
  `appointmentDate` varchar(200) NOT NULL,
  `appointmentTime` varchar(200) NOT NULL,
  `tokenNo` int(200) NOT NULL,
  `payType` varchar(200) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`appointmentID`)
)




INSERT INTO `appointment` (`appointmentID`,`userID`,`doctorID`,`appointmentDate`,`appointmentTime`,`tokenNo`,`payType`,`amount`) VALUES (1,1,01,'01/10/2020','12.45',1,'Credit',500);
INSERT INTO `appointment` (`appointmentID`,`userID`,`doctorID`,`appointmentDate`,`appointmentTime`,`tokenNo`,`payType`,`amount`) VALUES (2,2,02,'02/03/2020','01.50',2,'Credit',1000);
INSERT INTO `appointment` (`appointmentID`,`userID`,`doctorID`,`appointmentDate`,`appointmentTime`,`tokenNo`,`payType`,`amount`) VALUES (3,5,10,'01/02/2020','10.05',23,'Credit',500);
INSERT INTO `appointment` (`appointmentID`,`userID`,`doctorID`,`appointmentDate`,`appointmentTime`,`tokenNo`,`payType`,`amount`) VALUES (4,7,9,'01/02/2020','10.05',23,'Credit',500);
