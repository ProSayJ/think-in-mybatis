drop database if exists mybatis;
create database mybatis;
use mybatis;
##############################################################################
################################### 单表 ######################################
##############################################################################
## 创建单表
create table t_user
(
    `id`        int primary key auto_increment,
    `last_name` varchar(50),
    `sex`       int
);
insert into t_user(`last_name`, `sex`)
values ('wzg168', 1);
select *
from t_user;