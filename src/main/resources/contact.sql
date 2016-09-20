CREATE DATABASE IF NOT EXISTS `bukinevichKirillStudentLabDB`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci'; 
USE `bukinevichKirillStudentLabDB`;


CREATE TABLE IF NOT EXISTS `address` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) unsigned NOT NULL,
  `country` varchar(15) DEFAULT NULL,
  `city` varchar(15) DEFAULT NULL,
  `street` varchar(15) DEFAULT NULL,
  `house` int(11) DEFAULT NULL,
  `flat` int(11) DEFAULT NULL,
  `index_address` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `employee_id` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

INSERT INTO `address` (`id`, `employee_id`, `country`, `city`, `street`, `house`, `flat`, `index_address`) VALUES
	(3, 3, 'Bel', 'Minsk', 'Pushkinskay', 102, 45, 341222),
	(38, 59, 'bel', 'Minsk', 'voyn', 144, 54, 145223),
	(44, 65, 'bel', 'Minsk', 'voyn', 12, 54, 145223),
	(46, 67, 'bel', 'Vitebsk', 'Pobediteley', 132, 34, 432534);

CREATE TABLE IF NOT EXISTS `attachments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `file_name` varchar(50) NOT NULL,
  `date_of_load` date NOT NULL,
  `comment` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

INSERT INTO `attachments` (`id`, `employee_id`, `file_name`, `date_of_load`, `comment`) VALUES
	(17, 59, '2DOZF3oEF70.jpg', '2016-09-16', ''),
	(23, 3, 'index(e_Exam)_html.png', '2016-09-19', ''),
	(26, 67, '2DOZF3oEF70.jpg', '2016-09-20', ''),
	(34, 3, 'images.jpg', '2016-09-20', '');


CREATE TABLE IF NOT EXISTS `main_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `patronymic` varchar(20) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `nationality` varchar(20) DEFAULT NULL,
  `family_status` varchar(15) DEFAULT NULL,
  `web_site` varchar(150) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `work_place` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

INSERT INTO `main_info` (`id`, `first_name`, `last_name`, `patronymic`, `date_of_birth`, `gender`, `nationality`, `family_status`, `web_site`, `email`, `work_place`) VALUES
	(3, 'Petr', 'Lomov', 'Pavlovich', '1985-01-26', 'male', 'bel', 'married', 'pet:com', 'kirillbukinevich@gmail.com', 'itechart'),
	(59, 'Vasy', 'Petrovich', 'Dworkin', '1987-02-25', 'male', 'bel', 'married', 'http://v.com', 'kirill_bukinevich@mail.ru', 'itechart'),
	(65, 'Philip', 'Fokin', 'Ilich', '1980-09-01', 'male', 'bel', 'married', 'http://ph.com', 'philip@gmail.com', 'itechart'),
	(67, 'Sergey', 'Kolov', 'Ivanovich', '1982-06-10', 'male', 'bel', 'married', 'http://s.com', 's@gmail.com', 'epam');

CREATE TABLE IF NOT EXISTS `phone` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) unsigned NOT NULL,
  `code_country` varchar(5) DEFAULT NULL,
  `code_operator` varchar(5) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `comment` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

INSERT INTO `phone` (`id`, `employee_id`, `code_country`, `code_operator`, `number`, `type`, `comment`) VALUES
	(2, 2, '+375', '29', 2849243, 'mobile', ''),
	(3, 3, '+375', '25', 3522413, 'mobile', ''),
	(7, 65, '+375', '25', 1234567, 'mobile', ''),
	(12, 59, '+375', '29', 2849243, 'mobile', ''),
	(21, 3, '333', '33', 1234567, 'mobile', ''),
	(22, 67, '375', '33', 4352564, 'mobile', '');


CREATE TABLE IF NOT EXISTS `photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) DEFAULT NULL,
  `photo_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `employee_id` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

INSERT INTO `photo` (`id`, `employee_id`, `photo_name`) VALUES
	(18, 3, 'images.jpg');
