CREATE DATABASE springdb;

CREATE USER 'springdbuser'@'localhost' IDENTIFIED BY 'springdbuser';

CREATE USER 'springdbuser'@'%' IDENTIFIED BY 'springdbuser';

GRANT ALL PRIVILEGES ON springdb.* TO 'springdbuser'@'localhost';

GRANT ALL PRIVILEGES ON springdb.* TO 'springdbuser'@'%';

CREATE TABLE tbl_testA (
col1 VARCHAR(500)
);

CREATE TABLE tbl_testB (
col2 VARBINARY(50)
);

SELECT * FROM tbl_testA;

SELECT * FROM tbl_testb;

CREATE TABLE tbl_board (
bno INT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(500) NOT NULL,
content VARCHAR(2000) NOT NULL,
writer VARCHAR(50) NOT NULL,
regdate TIMESTAMP DEFAULT NOW(),
updatedate TIMESTAMP DEFAULT NOW(),
delflag BOOLEAN DEFAULT FALSE
);

SELECT * FROM tbl_board;
