CREATE DATABASE springdb;

CREATE USER 'springdbuser'@'localhost' IDENTIFIED BY 'springdbuser';

CREATE USER 'springdbuser'@'%' IDENTIFIED BY 'springdbuser';

GRANT ALL PRIVILEGES ON springdb.* TO 'springdbuser'@'localhost';

GRANT ALL PRIVILEGES ON springdb.* TO 'springdbuser'@'%';