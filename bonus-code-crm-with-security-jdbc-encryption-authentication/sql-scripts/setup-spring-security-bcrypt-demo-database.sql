DROP DATABASE  IF EXISTS `javaspring`;

CREATE DATABASE  IF NOT EXISTS `javaspring`;
USE `javaspring`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(76) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt

-- Default passwords here are: heslo
--

INSERT INTO `users` 
VALUES 
('adam','{bcrypt}$2a$04$Ohw6/eqV7SKh4/nK5K5F8.LsQDayQ2ie7bGc3PWISVIZwsfu1woeK',1),
('jano','{bcrypt}$2a$04$WZluFmZD.83XDan8.N79g.yFMfJ0Fc4i3mh3hn0cnxOXmzkQDvT3O',1),
('fero','{bcrypt}$2a$04$kg2JplhQ4Sez1V2gnBYDVeeKxM4IMgArqlAHormb.teh.GOf8Dp2G',1);


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('jano','ROLE_CUSTOMER'),
('fero','ROLE_CUSTOMER'),
('fero','ROLE_MANAGER'),
('adam','ROLE_CUSTOMER'),
('adam','ROLE_ADMIN');

DROP TABLE IF EXISTS `authors`;
CREATE TABLE `authors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `carts`;
CREATE TABLE `carts` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`cart_id`),
  CONSTRAINT `carts_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

INSERT INTO `carts`
VALUES
('1','jano'),
('2','fero'),
('3','adam');



DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `author` int(11) ,
  `reserved_from` date ,
  `reserved_to` date ,
  `reserved` int(1) NOT NULL,
  `username` varchar(50) ,
  `cart` int(11) ,
  PRIMARY KEY (`book_id`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  CONSTRAINT `books_ibfk_2` FOREIGN KEY (`author`) REFERENCES `authors` (`id`),
  CONSTRAINT `books_ibfk_3` FOREIGN KEY (`cart`) REFERENCES `carts` (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `styles`;
CREATE TABLE `styles` (
  `style_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`style_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `book_style`;
CREATE TABLE `book_style` (
  `book_id` int(11) NOT NULL,
  `style_id` int(11) NOT NULL,
  PRIMARY KEY (`book_id`, `style_id`),
  CONSTRAINT `book_style_ibfk_1`
  FOREIGN KEY (`book_id`) REFERENCES books (`book_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `book_style_ibfk_2`
    FOREIGN KEY (`style_id`) REFERENCES styles (`style_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;

