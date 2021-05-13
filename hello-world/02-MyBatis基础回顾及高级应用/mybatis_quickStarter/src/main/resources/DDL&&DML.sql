-- drop database if exists mybatis;
-- create database mybatis;
use mybatis;
##############################################################################
################################### 单表 ######################################
##############################################################################
## 创建单表
create table user_simple
(
    `id`        int primary key auto_increment,
    `user_name` varchar(50)
);
insert into user_simple(`user_name`) values ('admin123');