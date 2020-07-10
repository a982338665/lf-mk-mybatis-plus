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
 * @Description : 自定义sql测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class DeleteTest {

    @Autowired
    UsersMapper usersMapper;

    /**
     * 逻辑删除：
     * UPDATE users SET deleted=1 WHERE id=? AND deleted=0
     * 1087982257332887553(Long)
     */
    @Test
    public void deleteById() {
        int i = usersMapper.deleteById(1087982257332887553L);
        System.err.println(i);
    }

    /**
     * 被逻辑删除的 ，将无法再次被查询出来
     */
    @Test
    public void selectList() {
        List<Users> list = usersMapper.selectList(null);
        list.forEach(System.err::println);
    }
    /**
     * 被逻辑删除的 ，将无法被更新
     */
    @Test
    public void update() {
        Users users = new Users();
        users.setId(1087982257332887553L);
        users.setName("jjj");
        usersMapper.updateById(users);
    }

    /**
     * 自定义查询：会查出所有的数据，包含delete == 1的数据
     */
    @Test
    public void upselectCustom() {
        LambdaQueryWrapper<User> lambda = new LambdaQueryWrapper<>();
        lambda.like(User::getName, "雨")
                .lt(User::getAge, "50");
        //注解
        List<Users> users = usersMapper.selectAll(lambda);
        users.forEach(System.err::println);

    }



}
