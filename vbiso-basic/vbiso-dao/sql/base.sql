DROP DATABASE IF EXISTS vbiso;

CREATE DATABASE vbiso DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

DROP TABLE IF EXISTS category;
-- 分类表
CREATE TABLE category
(
  category_id   BIGINT       NOT NULL
    PRIMARY KEY,
  category_desc VARCHAR(100) NOT NULL,
  user_id       BIGINT       NOT NULL
)
  ENGINE = InnoDB
  COLLATE = utf8_bin;

DROP TABLE IF EXISTS users;

-- 用户表
CREATE TABLE users
(
  user_id       BIGINT          NOT NULL
    PRIMARY KEY,
  user_nick     VARCHAR(100)    NOT NULL,
  user_password VARCHAR(100)    NOT NULL,
  created_time  BIGINT          NULL,
  modify_time   BIGINT          NULL,
  user_mobile   VARCHAR(20)     NOT NULL,
  user_sex      INT DEFAULT '0' NOT NULL
)
  ENGINE = InnoDB
  COLLATE = utf8_bin;

DROP TABLE IF EXISTS expenses;

-- 支出表
CREATE TABLE expenses
(
  expenses_id   BIGINT       NOT NULL
    PRIMARY KEY,
  user_id       BIGINT       NOT NULL,
  expenses_date BIGINT       NOT NULL,
  expenses_data DOUBLE       NOT NULL,
  expenses_desc VARCHAR(200) NULL,
  category_id   BIGINT       NOT NULL
)
  ENGINE = InnoDB
  COLLATE =utf8_bin;

DROP TABLE IF EXISTS income;

-- 收入表
CREATE TABLE income
(
  income_id   BIGINT        NOT NULL
    PRIMARY KEY,
  user_id     BIGINT        NOT NULL,
  income_date BIGINT        NOT NULL,
  income_data DOUBLE        NOT NULL,
  income_desc VARCHAR(1024) NOT NULL,
  category_id BIGINT        NOT NULL
)
  ENGINE = InnoDB
  COLLATE =utf8_bin;

