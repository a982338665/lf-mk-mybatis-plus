package com.lf.mp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lf.mp.dao.UserMapper;
import com.lf.mp.entity.User;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/9 11:47
 * @Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class ConditionTest {

    @Autowired
    UserMapper userMapper;

    /**
     * 全查
     */
    @Test
    public void select() {
        select0("雨");
        select0("");
        //TODO Condition判断
        select1("雨");
    }


    private void select1(String name) {
        QueryWrapper<User> userQueryWrapper;
        userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                //判断条件放condition里，为true则拼接
                .like(StringUtils.isNotEmpty(name),"name", name)
                .between("age", 20, 40)
                .isNotNull("email");
        List<User> users1 = userMapper.selectList(userQueryWrapper);
        users1.forEach(System.err::println);
    }

    private void select0(String name) {
        QueryWrapper<User> userQueryWrapper;
        userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .between("age", 20, 40)
                .isNotNull("email");
        if(StringUtils.isNotEmpty(name)){
            userQueryWrapper
                    .like("name", name);
        }
        List<User> users1 = userMapper.selectList(userQueryWrapper);
        users1.forEach(System.err::println);
    }




}
