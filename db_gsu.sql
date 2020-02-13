-------------------------------------
-- SCHEMA FOR MARIA DB ENVIRONMENT --
-- DATE CREATED: 12/02/2020		   --
-- BY: OTTO MOLINA				   --
-------------------------------------

CREATE DATABASE db_gsu default character set utf8 default collate utf8_bin;

CREATE USER 'app_gsu'@'localhost' IDENTIFIED BY 'app_gsu_123$';

GRANT ALL PRIVILEGES ON db_gsu.* TO 'app_gsu'@'localhost';

FLUSH PRIVILEGES;
