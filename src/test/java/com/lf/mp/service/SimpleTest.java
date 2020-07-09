package com.lf.mp.service;

import com.lf.mp.dao.UserMapper;
import com.lf.mp.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.font.ScriptRun;

import java.util.List;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/9 11:47
 * @Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {

    @Autowired
    UserMapper userMapper;

    /**
     *
     */
    @Test
    public void select(){
        List<User> users = userMapper.selectList(null);
        Assert.assertEquals(5, users.size());
        users.forEach(System.err::println);
    }


}
