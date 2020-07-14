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
        1.配置插件，写在分页插件中
        2.测试使用：crud时都会把这个租户配置的id信息，作为条件添加进去 表级
        3.特定sql过滤：方法级别：
        4.注解过滤
        5.多租户：
            ·表级别租户
            ·方法级别
            ·注解
        
## 7.动态表
    
    1.动态表名sql解析器：
        ·动态表名的应用场景
            分表存储，字段相同，表名不同，因为数据表内容大而做的分表操作，例如 日志
            一月一张表，针对不同机构的，一个机构一张表
        ·动态表名拼接
    2.实现：
        ·也写在分页插件中
        ·与多租户配置相关联：
            例如：dao接口中 @SqlParser(filter = true) filter为true时，该接口中的sql表名不会被动态替换
            同理，表级别及方法级别的也一样，为true时不被替换
        
    
## 8.sql注入器
###  8.1 SQL注入器简介及自定义方法实现 

1.实现步骤： -- 等同于设计sql模板
 * 创建定义方法的类 : com.lf.mp.method.DeleteAllMethod 
 * 创建注入器： com.lf.mp.injector.MySqlInjector 
 * 在Mapper中加入自定义方法 ：com.lf.mp.dao.UsersMapper
 * 测试类 com.lf.mp.service2.InjectorTest 
 * 使用sql注入器需要注意 bean -> ISqlInjector
    bean of type 'com.baomidou.mybatisplus.core.injector.ISqlInjector' available: expected single matching bean but found 2: mySqlInjector,iSqlInjector
    
###  8.2 选装件InsertBatchSomeColumn 
###  8.3 选装件LogicDeleteByIdWithFill 
###  8.4 选装件alwaysUpdateSomeColumnById 

## 9.集成japidocs：

    0.网址：https://japidocs.agilestudio.cn/#/zh-cn/?id=%e5%85%a5%e9%97%a8
    1.添加依赖
    2.执行main函数生成
   
## 10.集成mvc：
    
    1.使用默认方式：查询[ResourceProperties]类下的CLASSPATH_RESOURCE_LOCATIONS变量，里面设置了默认的路径
        0.查找路径默认依次为：classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/
            即，当有重复命名的html，将会依次查询以上路径，取到则返回
            例如：访问-http://localhost:8080/index.html（index.html可不写）  Hello /META-INF/resources/index
        1.classpath:/META-INF/resources/：
            http://localhost:8080/index-info.html
            Hello META-INF/resources/*
        2.classpath:/resources/：
            http://localhost:8080/index-resources.html
            Hello /resources/index-resources
        3.classpath:/static/：一般用于存放静态资源(css,js,image等)
            http://localhost:8080/index-static.html
            Hello /static/
        4.classpath:/public/：一般用于存放页面
            http://localhost:8080/index-public.html
            Hello /public/
        注意：使用默认方式templates：用于存放页面，一般是thyleleaf、FreeMaker、Velocity、JSP等 （注意，这个需要引入thymeleaf相关依赖），否则路径不生效
              springboot访问模板类静态资源：直接放在resources/templates目录下即可访问
    2.使用模板引擎：以thymeleaf为例，集成后仍兼容 默认方式
        1.添加依赖
        2.准备资源文件 /templates/
        3.写接口
        4.测试：访问http://localhost:8080/test1/test1  响应：Hello /template/
    3.使用spring.resources.static-locations自定义资源路径：-兼容默认目录
        ·spring.resources.static-locations=classpath:/static,classpath:/public,classpath:/resources,classpath:/META-INF/resource
        ·如果指定了拦截器，该属性有可能失效
        ·需要在拦截器ResourceHandlerRegistry中通过addLocations()指定对应路径。
        ·具体实现：
            1.添加配置类：WebMvcConfig
            2.准备资源文件： /test/index-test.html
            3.测试：http://localhost:8080/test2/index-test.html   Hello /test/

## 11.
