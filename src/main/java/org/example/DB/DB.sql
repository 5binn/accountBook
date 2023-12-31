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
	`accountId` INT NOT NULL,
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






INSERT INTO `history`
SET `date` = '2023-12-10',
`content` = '내용',
`income` = 50000,
`expense` = 0,
categoryId = 2,
accountId = 6,
regDate = NOW(),
modifyDate = NOW();

SELECT * FROM `member`;
SELECT * FROM accountBook;
SELECT * FROM category;
SELECT * FROM history WHERE
YEAR(`date`) = 2022 AND MONTH(`date`) = 08

;
SELECT H.`date` , C.category ,
H.content , H.income , H.expense
FROM history AS H
JOIN category AS C
ON H.categoryId = C.categoryId
ORDER BY `date` ASC
;

SELECT sum(expense) FROM history
YEAR(`date`) = 2022 AND MONTH(`date`) = 08;

SELECT SUM(expense) FROM history WHERE
YEAR(`date`) = 2022 AND MONTH(`date`) = 08
AND accountId = 11;


DESC category





