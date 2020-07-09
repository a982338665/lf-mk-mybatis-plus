package com.lf.mp.conf;

import lombok.Getter;

/**
 * 生成id类型枚举类
 * @author : Mr huangye
 * @createTime : 2020/7/9 19:23
 */
@Getter
public enum IdType {

    /**
     * 数据库ID自增:
     * 使用：实体类 主键上加注解  @TableId(type = IdType.AUTO)
     */
    AUTO(0),

    /**
     * 未设置主键类型：跟随全局-默认雪花算法
     * 使用：实体类 主键上加注解  @TableId(type = IdType.NONE)
     */
    NONE(1),

    /**
     * 用户输入id，该类型可以通过自己注册自动填充插件进行填充
     * 使用：设置id 直接对象.setId()
     */
    INPUT(2),

    //以下三种类型，只有当插入对象ID为空，才自动填充，即 setId未设置
    /**
     * 全局唯一ID idwork - 就是雪花算法
     */
    ID_WORKER(3),

    /**
     * 全局唯一id UUID
     * 使用：String 类型
     */
    UUID(4),

    /**
     * 字符串全局唯一ID，idWork的字符串表示
     */
    ID_WORKER_STR(5);

    private final int key;

    IdType(int key){
        this.key = key;
    }
}
