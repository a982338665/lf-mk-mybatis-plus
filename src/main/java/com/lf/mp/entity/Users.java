package com.lf.mp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Users)实体类
 *
 * @author lishengbo
 * @since 2020-07-10 08:49:02
 */
@Data
@ApiModel(value = "用户实体类")
public class Users implements Serializable {
    private static final long serialVersionUID = 570410554725112284L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键ID", example = "1")
    private Long id;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", example = "张三")
    @TableField(fill = FieldFill.UPDATE)
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 直属上级id
     */
    private Long managerId;
    /**
     * 创建时间 - 新增时，自动填充
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间 - 修改时自动填充
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    /**
     * 版本
     */
    @Version
    private Integer version;
    /**
     * 逻辑删除标识(0.未删除,1.已删除)
     */
    // 局部有效，delval 默认逻辑删除值， value默认逻辑未删除
//            一般只设置全局即可
//    @TableLogic(delval = "1",value = "0")
    @TableLogic
    //表示查询时不显示
    @TableField(select = false)
    private Integer deleted;

}
