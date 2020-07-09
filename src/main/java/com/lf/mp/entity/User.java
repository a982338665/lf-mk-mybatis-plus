package com.lf.mp.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 * @Table (user)
 * @author lishengbo
 * @since 2020-07-09 11:27:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 * 指定表名-与类名相同时，可省略
 */
@TableName("user")
public class User extends Model<User> implements Serializable {
    private static final long serialVersionUID = 493083738120393040L;

    /**
     * @KNFieldDesc("主键")
     * 当id 与数据库主键id对不上的时候，需要指定主键id
     */
//    @TableId(type = IdType.AUTO)
    @TableId
    private Long id;

    /**
     * @KNFieldDesc("姓名")
     * 当Name与 数据库字段对不上的时候，需要指定映射关系
     * condition = SqlCondition.LIKE，当去匹配的时候采用 like匹配方式
     * condition也可以写自定义字符串：
     * condition = "%s LIKE CONCAT(#{%s},'%%"
     */
    @TableField(value = "name",condition = SqlCondition.LIKE)
    private String name;
//    @KNFieldDesc("年龄")
    private Integer age;
//    @KNFieldDesc("邮箱")
    private String email;
//    @KNFieldDesc("直属上级id")
    private Long managerId;
//    @KNFieldDesc("创建时间")
    private Date createTime;

    /**
     * 当前字段非数据库中的映射字段，若不加关键字，则会报错
     * transient修饰的变量不参与序列化过程
     * 缺点，若项目中有序列化需求，则此字段不能再使用
     */
    private transient String remark1;
    /**
     * 使用static 在插表时不加入
     * 缺点：lombok不会为静态变量生成get，set
     * 本该要求是每个对象都有一个这样的属性，但是使用静态变量，则变成类的属性，不合要求
     */
    private static String remark2;
    /**
     * 使用注解标识 该字段不在数据库表中 在插表时不加入
     */
    @TableField(exist = false)
    private String remark3;

    public static String getRemark2() {
        return remark2;
    }

    public static void setRemark2(String remark2) {
        User.remark2 = remark2;
    }
}
