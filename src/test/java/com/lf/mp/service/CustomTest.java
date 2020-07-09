package com.lf.mp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lf.mp.dao.UserMapper;
import com.lf.mp.entity.User;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/9 11:47
 * @Description : 自定义sql测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class CustomTest {

    @Autowired
    UserMapper userMapper;

    /**
     * 全查:按条件
     */
    @Test
    public void select() {
        LambdaQueryWrapper<User> lambda = new LambdaQueryWrapper<>();
        lambda.like(User::getName, "雨")
                .lt(User::getAge, "50");
        //注解
        List<User> users = userMapper.selectAll(lambda);
        users.forEach(System.err::println);

        //XML
        List<User> users2 = userMapper.selectAll2(lambda);
        users2.forEach(System.err::println);
    }



}
