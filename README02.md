# lf-mk-mybatisPlus
Mybatis-Plus（MP）在 MyBatis 的基础上只做增强不做改变，简化开发、提高效率
>课程地址：https://www.imooc.com/learn/1171
>

## 1.概述
### 1.1 高级功能课程简介

    ·逻辑删除，自动填充，乐观锁插件
    ·性能分析插件，多租户sql解析器
    ·动态表名sql解析器，sql注入器

### 1.2 基础数据表和基础项目介绍
## 2.逻辑删除
### 2.1 简介
### 2.2 实现

    ·全局开启逻辑删除，则所有的delete方法将由 delete变为update is_use= 1

### 2.3 查询中排除删除标识字段及注意事项

    ·自定义sql，都需自定义，不受全局影响
    
## 3.自动填充
### 3.1 简介

    ·新增时间，修改时间，新增人，修改人等特殊字段自动填充

### 3.2 实现
    
    1.实体类添加注解
    2.创建填充器
    3.优化填充器
    
### 3.3 优化
## 4.乐观锁
    
    ·版本号方式：
        1.取出记录时获取当前version
        2.更新时带上version
        3.版本正确更新成功，错误更新失败
    ·数据库锁机制：
        乐观锁：多读使用
        悲观锁：多写使用，例如主键冲突，使用数据库锁机制
    ·实现：
        1.添加配置bean
        2.实体类添加注解@Version
        
## 5.性能分析
    
    ·实现：
        1.添加插件配置（一般生产环境不开启）
    ·执行sql分析打印：https://mybatis.plus/guide/p6spy.html
        1.p6spy依赖，打印sql及执行时长， 3.1.0以上版本支持
        2.引入依赖
        3.yml添加配置：application-p6spy.yml
            driver-class-name: com.p6spy.engine.spy.P6SpyDriver
            url: jdbc:p6spy:mysql://localhost:3306/mp?useSSL=false&serverTimezone=GMT%2B8&allowMultiQueries=true&autoReconnect=true&characterEncoding=utf-8
        4.新增配置文件：spy.properties
        5.注意！
          driver-class-name 为 p6spy 提供的驱动类
          url 前缀为 jdbc:p6spy 跟着冒号为对应数据库连接地址
          打印出sql为null,在excludecategories增加commit
          批量操作不打印sql,去除excludecategories中的batch
          批量操作打印重复的问题请使用MybatisPlusLogFactory (3.2.1新增）
          该插件有性能损耗，不建议生产环境使用。
            
## 6.多租户
    
    ·概念：多企业使用同一套程序，数据需要进行隔离
    ·方案：
        1.每人一个库，隔离性好，但是会增加购置成本
        2.共用库，数据故障恢复难
        3.共用库共用表，通过id标识区分，安全性最低，故障恢复最困难
    ·实现：此处实现第三种方式的多租户，共享数据库，共享schema
        1.配置插件
        2.测试使用：crud时都会把这个租户配置的id信息，作为条件添加进去 表级
    
## 7.动态表
## 8.sql注入器
### 8.1 SQL注入器简介及自定义方法实现 
###  8.2 选装件InsertBatchSomeColumn 
###  8.3 选装件LogicDeleteByIdWithFill 
###  8.4 选装件alwaysUpdateSomeColumnById 


