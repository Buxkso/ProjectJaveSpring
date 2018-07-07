DROP DATABASE  IF EXISTS `javaspring`;

CREATE DATABASE  IF NOT EXISTS `javaspring`;
USE `javaspring`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt

-- Default passwords here are: heslo
--

INSERT INTO `users` 
VALUES 
('john','{bcrypt}$2a$04$s4qqUk4c3SsYtvT8D2B3cee9GbwsulrwBeJ9XrLHCGmlS1VhTDepS.5PM0K',1),
('mary','{bcrypt}$2a$04$WWhWc.MfPxKsTvGrORb.duiu4iCE8NmdNd6zGQns54FMLh2niVINi.5PM0K',1),
('susan','{bcrypt}$2a$04$KpllvgzlsocxjzIpSfK8WOjHeoNJWhitHh4Erlgzn5j9vPbvdF4W..5PM0K',1);


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('john','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('susan','ROLE_EMPLOYEE'),
('susan','ROLE_ADMIN');

DROP TABLE IF EXISTS `authors`;
CREATE TABLE `authors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `author` int(11) NOT NULL,
  `reserved_from` date NOT NULL,
  `reserved_to` date NOT NULL,
  `reserved` tinyint(1) NOT NULL,
  `username` varchar(50) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  CONSTRAINT `books_ibfk_2` FOREIGN KEY (`author`) REFERENCES `authors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `styles`;
CREATE TABLE `styles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `book_style`;
CREATE TABLE `book_style` (
  `book_id` int(11) NOT NULL,
  `style_id` int(11) NOT NULL,
  FOREIGN KEY (`book_id`) REFERENCES books (`id`) ON DELETE CASCADE ON UPDATE RESTRICT ,
    FOREIGN KEY (`style_id`) REFERENCES styles (`id`) ON DELETE CASCADE ON UPDATE RESTRICT ,
    PRIMARY KEY (`book_id`, `style_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

