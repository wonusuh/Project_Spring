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

INSERT INTO tbl_board (title, content, writer)
SELECT title, content, writer
FROM tbl_board;

SELECT *
FROM tbl_board
WHERE bno > 0
AND delflag = FALSE
ORDER BY bno DESC;

SELECT *
FROM tbl_board
WHERE bno > 0
AND delflag = FALSE
ORDER BY bno DESC
LIMIT 10 OFFSET 10;

CREATE TABLE tbl_reply (
rno INT AUTO_INCREMENT PRIMARY KEY,
replyText VARCHAR(500) NOT NULL,
replyer VARCHAR(50) NOT NULL,
replydate TIMESTAMP DEFAULT NOW(),
updatedate TIMESTAMP DEFAULT NOW(),
delflag BOOLEAN DEFAULT FALSE,
bno INT NOT null
);

ALTER TABLE tbl_reply
ADD CONSTRAINT fk_reply_board
FOREIGN KEY (bno)
REFERENCES tbl_board(bno);

CREATE INDEX idx_reply_board
ON tbl_reply (bno DESC, rno ASC);

SELECT * FROM tbl_reply;
