package com.lf.mp.service2;

import com.lf.mp.conf.MybatisPlusConf;
import com.lf.mp.dao.UsersMapper;
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
 * @createTime : 2020/7/10 13:39
 * @Description : 动态sql注入
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class DynamicTest {

    @Autowired
    UsersMapper usersMapper;


    /**
     * 动态表名操作
     * SELECT  id,name,age,email,manager_id,create_time,update_time,version  FROM user_2019   WHERE deleted=0
     * ### Cause: java.sql.SQLSyntaxErrorException: Table 'mp.user_2019' doesn't exist
     */
    @Test
    public void select() {
        MybatisPlusConf.myTableName.set("user_2019");
        List<Users> list = usersMapper.selectList(null);
        list.forEach(System.err::println);
    }


}
