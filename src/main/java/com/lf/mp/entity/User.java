package com.lf.mp.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 * @Table (user)
 * @author lishengbo
 * @since 2020-07-09 11:27:56
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 493083738120393040L;
//    @KNFieldDesc("主键")
    private Long id;
//    @KNFieldDesc("姓名")
    private String name;
//    @KNFieldDesc("年龄")
    private Integer age;
//    @KNFieldDesc("邮箱")
    private String email;
//    @KNFieldDesc("直属上级id")
    private Long managerId;
//    @KNFieldDesc("创建时间")
    private Date createTime;

}
