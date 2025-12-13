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
