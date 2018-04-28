-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS wedulpos DEFAULT CHARACTER SET UTF8;

-- User 테이블 생성
CREATE TABLE IF NOT EXISTS `wedulpos`.`user` (
  `idx` INT NOT NULL AUTO_INCREMENT COMMENT '고유 인덱스',
  `email` VARCHAR(255) NULL COMMENT '이메일',
  `password` TEXT NOT NULL COMMENT '비밀번호',
  `isadmin` BOOLEAN NOT NULL DEFAULT 0 COMMENT '최고관리자 여부',
  PRIMARY KEY (`idx`),
  UNIQUE INDEX `user_uq1` (`email`))
ENGINE = InnoDB
charset=utf8
COMMENT = '사용자';

-- 관리자 설정이 들어가는 테이블 생성
CREATE TABLE IF NOT EXISTS `wedulpos`.`variables` (
	`name` VARCHAR(255) NOT NULL COMMENT '설정 이름' PRIMARY KEY,
	`value` TEXT NOT NULL COMMENT '설정 값'
) charset=utf8;

-- otp 테이블.
CREATE TABLE IF NOT EXISTS `wedulpos`.`otp` (
	`type` INT NOT NULL COMMENT 'otp type : 0 (인증번호), 1(임시비밀번호)',
	`userid` VARCHAR(255) NOT NULL COMMENT '사용자 ID',
	`otp` TEXT NOT NULL COMMENT 'otp 번호',
	PRIMARY KEY(type, userid)
) charset=utf8;