package com.lf.mp.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/10 9:43
 * @Description : 自动填充器实现
 */
@Component
public class MyMateObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //若有set方法则自动填充
        boolean hasSetter = metaObject.hasSetter("createTime");
        if (hasSetter){
            System.err.println("insert ...");
            //实体类属性名
            setInsertFieldValByName("createTime", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //取出当前对象 updateTime的值，若没有被赋值则填充，若有则使用已有的
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if(null == updateTime){
            System.err.println("update ...");
            setUpdateFieldValByName("updateTime", new Date(), metaObject);
        }
    }
}
