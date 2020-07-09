package com.lf.mp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lf.mp.entity.User;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/9 20:10
 * @Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class ServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void select() {
        //第二个参数默认为true，若查出来多个则报错，当他为false时，查出来多个则取第一个
        User age = userService.getOne(new QueryWrapper<User>().gt("age", 18), false);
        System.err.println(age);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setName("13");
        user.setAge(11);
        User user2 = new User();
        user2.setName("1322");
        user2.setAge(11);

        List<User> users = Arrays.asList(user, user2);
//        userService.saveBatch(users);
        //批量插入，每次插入1000条
//        userService.saveBatch(users, 1000);
//        userService.saveOrUpdateBatch(users, 1000);

    }

    /**
     * 链式调用查询
     */
    @Test
    public void chain() {
        List<User> age = userService.lambdaQuery().gt(User::getAge, 23).list();

        //将25岁的改为23岁
        boolean update = userService.lambdaUpdate().eq(User::getAge, 25).set(User::getName, 23).update();

        //删除
        boolean remove = userService.lambdaUpdate().eq(User::getAge, 25).set(User::getName, 23).remove();


    }
}
