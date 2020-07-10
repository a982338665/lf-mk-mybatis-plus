package com.lf.mp.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.additional.InsertBatchSomeColumn;
import com.lf.mp.method.DeleteAllMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/10 14:06
 * @Description :
 * 继承DefaultSqlInjector 和继承  LogicSqlInjector的区别：
 * 第一个不包含 逻辑删除的配置
 * 第二个 兼容逻辑删除的配置
 */
@Component
public class MySqlInjector extends LogicSqlInjector {
//public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        //全部删除装载
        methodList.add(new DeleteAllMethod());
        //批量插入所有字段
//        methodList.add(new InsertBatchSomeColumn());
        //批量插入所有字段，排除逻辑删除字段不插入,
//        methodList.add(new InsertBatchSomeColumn(t->!t.isLogicDelete()));
        //排除逻辑删除字段  和 年龄字段不插入
        methodList.add(new InsertBatchSomeColumn(t->!t.isLogicDelete()&&!t.getColumn().equals("age")));
        return methodList;
    }
}
