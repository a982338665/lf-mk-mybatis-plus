package com.lf.mp.mutlitables;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lf.mp.dao.UserMapper;
import com.lf.mp.dao.UsersMapper;
import com.lf.mp.entity.User;
import com.lf.mp.vo.MutilTestVo;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/9 11:47
 * @Description : 多表sql测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class MutliTest {

    @Autowired
    UserMapper userMapper;

    /**
     * 全查:按条件
     */
    @Test
    public void select() {
        //不要总数，只要记录 ,第三个参数为false
//        Page<User> userPage = new Page<>(1, 2, false);
        //自定义分页
        Page<User> userPage = new Page<>(1, 2);
        IPage<MutilTestVo> mapIPage2 = userMapper.selectUsersPagesTest(userPage);
        System.err.println(mapIPage2.getCurrent());
        System.err.println(mapIPage2.getPages());
        System.err.println(mapIPage2.getSize());
        System.err.println(mapIPage2.getTotal());
        System.err.println(mapIPage2.getRecords().toString());
    }

    @Test
    public void select2() {
        //不要总数，只要记录 ,第三个参数为false
//        Page<User> userPage = new Page<>(1, 2, false);
        //自定义分页
        Page<User> userPage = new Page<>(1, 2);
        IPage<MutilTestVo> mapIPage2 = userMapper.selectUsersPagesTest2(userPage);
        System.err.println(mapIPage2.getCurrent());
        System.err.println(mapIPage2.getPages());
        System.err.println(mapIPage2.getSize());
        System.err.println(mapIPage2.getTotal());
        System.err.println(mapIPage2.getRecords().toString());
    }

    @Test
    public void select3() {
        //不要总数，只要记录 ,第三个参数为false
//        Page<User> userPage = new Page<>(1, 2, false);
        //自定义分页
        Page<User> userPage = new Page<>(1, 2);
        IPage<MutilTestVo> mapIPage2 = userMapper.selectUsersPagesTest3(userPage,"1281184543193223169");
        System.err.println(mapIPage2.getCurrent());
        System.err.println(mapIPage2.getPages());
        System.err.println(mapIPage2.getSize());
        System.err.println(mapIPage2.getTotal());
        System.err.println(mapIPage2.getRecords().toString());
    }



}
