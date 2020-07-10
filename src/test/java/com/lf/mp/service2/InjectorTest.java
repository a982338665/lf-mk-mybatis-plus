package com.lf.mp.service2;

import com.lf.mp.dao.UsersMapper;
import com.lf.mp.entity.Users;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/9 11:47
 * @Description : 自定义sql测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class InjectorTest {

    @Autowired
    UsersMapper usersMapper;

    /**
     * 逻辑删除：
     * Consume Time：42 ms 2020-07-10 14:18:43
     * Execute SQL：delete from users
     * org.springframework.dao.DataIntegrityViolationException:
     * ### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException:
     * Cannot delete or update a parent row: a foreign key constraint fails (`mp`.`users`, CONSTRAINT `manager_fks` FOREIGN KEY (`manager_id`) REFERENCES `users` (`id`)
     * 当因为外键发生报错时：
     * 问题主要是删除记录的时候删不了，最后发现是中间表的外键默认删除时是RESTRICT，
     *   当取值为No Action或者Restrict时，则当在父表（即外键的来源表）中删除对应记录时，首先检查该记录是否有对应外键，如果有则不允许删除。
     *   当取值为Cascade时，则当在父表（即外键的来源表）中删除对应记录时，首先检查该记录是否有对应外键，如果有则也删除外键在子表（即包含外键的表）中的记录。
     *   当取值为Set Null时，则当在父表（即外键的来源表）中删除对应记录时，首先检查该记录是否有对应外键，如果有则设置子表中该外键值为null（不过这就要求该外键允许取null）。
     *   解决:  把外键字段原来默认的Restrict改成CASCADE即可
     */
    @Test
    public void deleteAll() {
        int i = usersMapper.deleteAll();
        System.err.println(i);
    }

    /**
     * 批量插入
     */
    @Test
    public void insertBatch() {
        ArrayList<Users> objects = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Users users = new Users();
            users.setName("luofengddd"+i);
            users.setEmail("ssss@qq.com."+i);
            objects.add(users);
        }
        usersMapper.insertBatchSomeColumn(objects);
    }
}
