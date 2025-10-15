/* add user on the mariadb */
CREATE USER 'webstudent'@'localhost' IDENTIFIED BY 'webstudent';
GRANT ALL PRIVILEGES ON * . * TO 'webstudent'@'localhost';

/* Create DB */
CREATE DATABASE  IF NOT EXISTS `web_student_tracker`;
USE `web_student_tracker`;

/* Create tables */
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3_unicode_ci;

/* Add data */
INSERT INTO `student` VALUES (1,'Mary','Public','mary@luv2code.com'),
  (2,'John','Doe','john@luv2code.com'),
  (3,'Ajay','Rao','ajay@luv2code.com'),
  (4,'Bill','Neely','bill@luv2code.com'),
  (5,'Maxwell','Dixon','max@luv2code.com');