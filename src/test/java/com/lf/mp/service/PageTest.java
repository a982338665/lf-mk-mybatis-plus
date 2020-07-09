package com.lf.mp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lf.mp.dao.UserMapper;
import com.lf.mp.entity.User;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/9 11:47
 * @Description : 分页测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class PageTest {

    @Autowired
    UserMapper userMapper;

    /**
     * 全查:按条件
     */
    @Test
    public void select() {
        LambdaQueryWrapper<User> lambda = new LambdaQueryWrapper<>();
        lambda.lt(User::getAge, "50");
        //不要总数，只要记录 ,第三个参数为false
//        Page<User> userPage = new Page<>(1, 2, false);
        Page<User> userPage = new Page<>(1, 2);
        IPage<User> userIPage = userMapper.selectPage(userPage, lambda);
        System.err.println(userIPage.getCurrent());
        System.err.println(userIPage.getPages());
        System.err.println(userIPage.getSize());
        System.err.println(userIPage.getTotal());
        System.err.println(userIPage.getRecords().toString());

        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(userPage, lambda);
        System.err.println(mapIPage.getCurrent());
        System.err.println(mapIPage.getPages());
        System.err.println(mapIPage.getSize());
        System.err.println(mapIPage.getTotal());
        System.err.println(mapIPage.getRecords().toString());

        //自定义分页
        IPage<User> mapIPage2 = userMapper.selectUsersPages(userPage, lambda);
        System.err.println(mapIPage2.getCurrent());
        System.err.println(mapIPage2.getPages());
        System.err.println(mapIPage2.getSize());
        System.err.println(mapIPage2.getTotal());
        System.err.println(mapIPage2.getRecords().toString());
    }


}
