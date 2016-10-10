CREATE DATABASE IF NOT EXISTS `bukinevichkirillstudentlabdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bukinevichkirillstudentlabdb`;


CREATE TABLE IF NOT EXISTS `attachments` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) unsigned NOT NULL,
  `file_name` varchar(50) NOT NULL,
  `date_of_load` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `comment` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_attachments_main_info_Cascadebukinevichkirillstudentlabdb` (`employee_id`),
  CONSTRAINT `FK_attachments_main_info_Cascadebukinevichkirillstudentlabdb` FOREIGN KEY (`employee_id`) REFERENCES `main_info` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8;

INSERT INTO `attachments` (`id`, `employee_id`, `file_name`, `date_of_load`, `comment`) VALUES
	(121, 67, 'readme.txt', '2016-10-09 09:40:36', '');


CREATE TABLE IF NOT EXISTS `main_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `patronymic` varchar(20) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `gender` enum('мужской','женский') DEFAULT NULL,
  `nationality` varchar(20) DEFAULT NULL,
  `family_status` varchar(15) DEFAULT NULL,
  `web_site` varchar(150) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `work_place` varchar(50) DEFAULT NULL,
  `country` varchar(15) DEFAULT NULL,
  `city` varchar(15) DEFAULT NULL,
  `street` varchar(15) DEFAULT NULL,
  `house` int(11) DEFAULT NULL,
  `flat` int(11) DEFAULT NULL,
  `index_address` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=223 DEFAULT CHARSET=utf8;

INSERT INTO `main_info` (`id`, `first_name`, `last_name`, `patronymic`, `date_of_birth`, `gender`, `nationality`, `family_status`, `web_site`, `email`, `work_place`, `country`, `city`, `street`, `house`, `flat`, `index_address`) VALUES
	(67, 'Сергей', 'Колов', 'Иванович', '1982-06-10', 'мужской', 'беларус', 'брак', 'http://s.com', 'kirill_bukinevich@mail.ru', 'epam', 'Беларусь', 'Минск', 'Пушкинская', 144, 49, 145223),
	(68, 'Анатолий', 'Дрибков', 'Владимирович', '1990-08-12', 'мужской', 'беларус', 'свободен', 'http://anatolyi.com', 'anatoliy@gmail.com', 'itechart', NULL, NULL, NULL, NULL, NULL, NULL),
	(69, 'Фёдор', 'Абрамов', 'Петрович', '1991-03-24', 'мужской', 'беларус', 'брак', 'http://fedor.com', 'fedor@gmail.com', 'epam', NULL, NULL, NULL, NULL, NULL, NULL),
	(70, 'Николай', 'Тулкин', 'Иванович', '1985-10-07', 'мужской', 'беларус', 'брак', 'http://nikolay.com', 'nik@gmail.com', 'itechart', NULL, NULL, NULL, NULL, NULL, NULL);


CREATE TABLE IF NOT EXISTS `phone` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) unsigned NOT NULL,
  `code_country` int(3) DEFAULT NULL,
  `code_operator` int(2) DEFAULT NULL,
  `number` int(11) NOT NULL,
  `type` varchar(20) DEFAULT NULL,
  `comment` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_phone_main_info_Cascade` (`employee_id`),
  CONSTRAINT `FK_phone_main_info_Cascade` FOREIGN KEY (`employee_id`) REFERENCES `main_info` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

INSERT INTO `phone` (`id`, `employee_id`, `code_country`, `code_operator`, `number`, `type`, `comment`) VALUES
	(55, 67, 123, 29, 2222222, 'мобильный', '');


CREATE TABLE IF NOT EXISTS `photo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) unsigned NOT NULL,
  `photo_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK_photo_main_info_Cascade` (`employee_id`),
  CONSTRAINT `FK_photo_main_info_Cascade` FOREIGN KEY (`employee_id`) REFERENCES `main_info` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

INSERT INTO `photo` (`id`, `employee_id`, `photo_name`) VALUES
	(22, 68, NULL),
	(23, 48, NULL),
	(24, 70, NULL),
	(26, 67, 'Servlet_HelloServletDirectory.png'),
	(30, 59, 'images.jpg');
