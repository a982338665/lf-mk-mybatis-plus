package com.lf.mp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
 * @Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class LambdaTest {

    @Autowired
    UserMapper userMapper;

    /**
     * 使用此种方式减少魔法值
     * 且编译前有校验
     */
    @Test
    public void select() {
//        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
//        LambdaQueryWrapper<User> lambda = Wrappers.lambdaQuery();
        LambdaQueryWrapper<User> lambda = new LambdaQueryWrapper<>();
        lambda.like(User::getName, "雨")
                .lt(User::getAge, "50");
        List<User> users = userMapper.selectList(lambda);
        users.forEach(System.err::println);
    }





}
