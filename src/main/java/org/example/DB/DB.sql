DROP DATABASE IF EXISTS accountBook;
CREATE DATABASE `accountBook`;
USE `accountBook`;

CREATE TABLE `member` (
	`id` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`userName` VARCHAR(50) NOT NULL UNIQUE,
	`password` VARCHAR(100) NOT NULL,
	`nickname` VARCHAR(50) NOT NULL,
	`manager` BOOLEAN NOT NULL DEFAULT 0,
	`regDate` DATETIME NOT NULL,
	`modifyDate` DATETIME NOT NULL
);

CREATE TABLE `accountBook` (
	`id` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`memberId` INT UNSIGNED NOT NULL,
	`accountName` VARCHAR(50) NOT NULL,
	`balance` INT NOT NULL,
	`savingGoal` INT UNSIGNED NOT NULL,
	`memberIds`	TEXT NOT NULL,
	`regDate` DATETIME NOT NULL,
	`modifyDate` DATETIME NOT NULL
);

CREATE TABLE `history` (
	`id` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`date` DATE	NOT NULL,
	`content` TEXT NULL,
	`income` INT NULL,
	`expense` INT NULL,
	`categoryId` INT NOT NULL,
	`accounId` INT NOT NULL,
	`regDate` DATETIME NOT NULL,
	`modifyDate` DATETIME NOT NULL
);

CREATE TABLE `category` (
	`categoryId`INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`category` VARCHAR(50) NOT NULL
);

CREATE TABLE `wiseSaying` (
	`wiseSayingId` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`wiseSaying` TEXT NULL
);

INSERT INTO `member`
SET userName = 'admin',
`password` = 'rhksflwk',
`nickname` = '관리자',
`manager` = 1,
regDate = now(),
modifyDate = now();

INSERT INTO `category` (`category`)
VALUES
  ('고정비'),
  ('식비'),
  ('생활비'),
  ('유흥비'),
  ('교통비'),
  ('교육비'),
  ('금융비'),
  ('세금'),
  ('기타');











