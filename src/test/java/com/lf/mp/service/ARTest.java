package com.lf.mp.service;

import com.lf.mp.dao.UserMapper;
import com.lf.mp.entity.User;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
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
public class ARTest {

    @Autowired
    UserMapper userMapper;

    /**
     * 全查:按条件
     */
    @Test
    public void insert() {
        User user = new User();
        user.setName("luofeng2");
        user.setAge(18);
        user.setEmail("xxx@qq.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(new Date());
        User.setRemark2("beihzhu2");
        boolean insert = user.insert();
        System.err.println(user.getId());
//        boolean insert2 = user.insertOrUpdate();
    }
    @Test
    public void select() {
        List<User> users = new User().selectAll();
    }
    @Test
    public void selectById() {
        User users = new User().selectById(1L);
        User user = new User();
        user.setId(1L);
        User user1 = user.selectById();
    }



}
