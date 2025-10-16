/* add user on the mariadb */
CREATE USER 'webstudent'@'localhost' IDENTIFIED BY 'webstudent123';
GRANT ALL PRIVILEGES ON * . * TO 'webstudent'@'localhost';

/* Create DB */
CREATE DATABASE  IF NOT EXISTS `student_db`;
USE `student_db`;

/* Create tables */
CREATE TABLE `studentdb`.`student` (
  `id` INT(11) NOT NULL AUTO_INCREMENT , 
  `first_name` VARCHAR(45) NULL DEFAULT NULL , 
  `last_name` VARCHAR(45) NULL DEFAULT NULL , 
  `email` VARCHAR(45) NULL DEFAULT NULL , 
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARSET=utf8mb3 COLLATE utf8mb3_unicode_ci;

/* Add data */
INSERT INTO `student` VALUES (1,'Mary','Public','mary@luv2code.com'),
  (2,'John','Doe','john@luv2code.com'),
  (3,'Ajay','Rao','ajay@luv2code.com'),
  (4,'Bill','Neely','bill@luv2code.com'),
  (5,'Maxwell','Dixon','max@luv2code.com');