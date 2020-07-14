select * from user;

# can't write duplicate key in table 【mysql错误】
# 出现该错误是因为数据库中的外键约束报错，
# 在整个库中外键命名是唯一的，若命名重复则会报错，
# 可使用下面的语句查询外键所在的表
# SELECT `TABLE_SCHEMA`, `TABLE_NAME`
# FROM `information_schema`.`KEY_COLUMN_USAGE`
# WHERE `CONSTRAINT_NAME` IN ('外键名称');
# 另外，将外键重新命名则可解决该问题，
# 建议以 fk_id_1 ，fk_id_2类似模式加上数字命名
# 创建用户表
CREATE TABLE users (
                       id BIGINT(20) PRIMARY KEY NOT NULL COMMENT '主键',
                       name VARCHAR(30) DEFAULT NULL COMMENT '姓名',
                       age INT(11) DEFAULT NULL COMMENT '年龄',
                       email VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
                       manager_id BIGINT(20) DEFAULT NULL COMMENT '直属上级id',
                       create_time DATETIME DEFAULT NULL COMMENT '创建时间',
                       update_time DATETIME DEFAULT NULL COMMENT '修改时间',
                       version INT(11) DEFAULT '1' COMMENT '版本',
                       deleted INT(1) DEFAULT '0' COMMENT '逻辑删除标识(0.未删除,1.已删除)',
                       CONSTRAINT manager_fks FOREIGN KEY (manager_id)
                           REFERENCES users (id)
)  ENGINE=INNODB CHARSET=UTF8;

#初始化数据：
INSERT INTO users (id, name, age, email, manager_id
                  , create_time)
VALUES (1087982257332887553, '大boss', 40, 'boss@baomidou.com', NULL
       , '2019-01-11 14:20:20'),
       (1088248166370832385, '王天风', 25, 'wtf@baomidou.com', 1087982257332887553
       , '2019-02-05 11:12:22'),
       (1088250446457389058, '李艺伟', 28, 'lyw@baomidou.com', 1088248166370832385
       , '2019-02-14 08:31:16'),
       (1094590409767661570, '张雨琪', 31, 'zjq@baomidou.com', 1088248166370832385
       , '2019-01-14 09:15:15'),
       (1094592041087729666, '刘红雨', 32, 'lhm@baomidou.com', 1088248166370832385
       , '2019-01-14 09:48:16');

# 用来测试代码生成器
CREATE TABLE `t_s_user` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) DEFAULT NULL,
                            `birthday` date DEFAULT NULL,
                            `age` int(11) DEFAULT NULL,
                            `money` decimal(20,5) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

