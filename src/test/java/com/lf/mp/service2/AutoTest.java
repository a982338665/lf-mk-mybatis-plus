package com.lf.mp.service2;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lf.mp.dao.UsersMapper;
import com.lf.mp.entity.User;
import com.lf.mp.entity.Users;
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
 * @Description : 自动填充
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class AutoTest {

    @Autowired
    UsersMapper usersMapper;


    @Test
    public void insert() {
        Users users = new Users();
        users.setAge(31);
        users.setName("jjj");
        int insert = usersMapper.insert(users);
        System.err.println(insert);
    }

    @Test
    public void update() {
        Users users = new Users();
        users.setAge(32);
        users.setName("jjj");
        users.setId(1281405541339430914L);
        int insert = usersMapper.updateById(users);
        System.err.println(insert);
    }

}
