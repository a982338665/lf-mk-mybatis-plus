package com.lf.mp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.lf.mp.dao.UserMapper;
import com.lf.mp.entity.User;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
public class UpdateTest {

    @Autowired
    UserMapper userMapper;

    /**
     * 全查:按条件
     */
    @Test
    public void udpate() {
        User user = new User();
        user.setId(1281108939232022530L);
        user.setName("luofeng");
        user.setAge(25);
        int i = userMapper.updateById(user);
        System.err.println(i);
    }

    @Test
    public void udpateByWrapper() {
        //更新的内容
        User user = new User();
        user.setName("luofeng333");
        user.setAge(25);
        //条件
        LambdaUpdateWrapper<User> userUpdateWrapper = new LambdaUpdateWrapper<>();
        userUpdateWrapper.eq(User::getName, "luofeng");
        int i = userMapper.update(user,userUpdateWrapper);
        System.err.println(i);
    }

    @Test
    public void udpateByWrapper2() {
        //条件 - 通过set修改
        LambdaUpdateWrapper<User> userUpdateWrapper = new LambdaUpdateWrapper<>();
        userUpdateWrapper.eq(User::getName, "luofeng333").set(User::getName, "luofeng22");
        int i = userMapper.update(null,userUpdateWrapper);
        System.err.println(i);
    }
    @Test
    public void udpateByWrapper3() {
        //条件 - 通过set修改 大于0 true
        LambdaUpdateChainWrapper<User> userUpdateWrapper = new LambdaUpdateChainWrapper<>(userMapper);
        boolean i = userUpdateWrapper.eq(User::getName, "luofeng333").set(User::getName, "luofeng22").update();
        System.err.println(i);
    }




}
