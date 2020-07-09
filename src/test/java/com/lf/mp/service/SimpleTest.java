package com.lf.mp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lf.mp.dao.UserMapper;
import com.lf.mp.entity.User;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/9 11:47
 * @Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class SimpleTest {

    @Autowired
    UserMapper userMapper;

    /**
     * 全查
     */
    @Test
    public void select() {
        List<User> users = userMapper.selectList(null);
        Assert.assertEquals(5, users.size());
        users.forEach(System.err::println);
    }

    /**
     * 新增：
     * 1.此处没有设置id ，默认采用雪花算法
     * 2.默认驼峰式命名
     * 3.表名由 User 改为 mp_user 时，需要在实体中指定所使用表名,表名和实体相同时则不需要指定
     * 4.字段修改后，还需要修改字段映射
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
        int insert = userMapper.insert(user);
        log.error("影响记录数：{}", insert);
    }

    /**
     * 根据id查询
     */
    @Test
    public void selectById() {
        User user = userMapper.selectById(1087982257332887553L);
        log.error(user);
    }

    /**
     * 根据ids查询
     */
    @Test
    public void selectBatchIds() {
        List<User> user = userMapper.selectBatchIds(Arrays.asList(1087982257332887553L, 1088248166370832385L));
        log.error(user.toString());
    }

    /**
     * 根据map查询
     * map中的key为数据库中的字段
     * == where age=? and manager_id = ?
     */
    @Test
    public void selectByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("manager_id", 12);
        map.put("age", 15);
        List<User> user = userMapper.selectByMap(map);
        log.error(user.toString());
    }

    /**
     * 实体作为条件构造器构造方法的参数
     * DEBUG==>  Preparing: SELECT id,name,age,email,manager_id,create_time FROM user WHERE name like ? AND age=? AND name LIKE ? AND age < ?
     * 两者互不相关
     */
    @Test
    public void selectByWrapperEntity() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("manager_id", 12);
        map.put("age", 15);
        map.put("id", null);
        //false 表示参数中若有值为 null ，则忽略查询 WHERE manager_id = ? AND age = ?
//        userQueryWrapper.allEq(map,false);
        // WHERE manager_id = ? AND age = ? and id is null
//        userQueryWrapper.allEq(map);
        // 将name条件去掉
        userQueryWrapper.allEq((k,v)->!k.equals("manager_id"),map);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.err::println);

    }

    /**
     * AllEq：
     *
     */
    @Test
    public void selectByWrapperAlleq() {
        User user = new User();
        user.setName("luofeng");
        user.setAge(18);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);
        userQueryWrapper
                .like("name", "雨")
                .lt("age", 40);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.err::println);

    }
    /**
     * 返回统计需求，有别名
     * selectMaps：
     */
    @Test
    public void selectByWrapperMaps() {
        User user = new User();
        user.setName("luofeng");
        user.setAge(18);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);
        userQueryWrapper
                .like("name", "雨")
                .lt("age", 40);
        List<Map<String, Object>> users = userMapper.selectMaps(userQueryWrapper);
        users.forEach(System.err::println);

    }
    /**
     * 只返回第一个字段的值，此处为id
     * selectObjs：
     */
    @Test
    public void selectByWrapperObjs() {
        User user = new User();
        user.setName("luofeng");
        user.setAge(18);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);
        List<Object> users = userMapper.selectObjs(userQueryWrapper);
        users.forEach(System.err::println);

    }
    /**
     * selectCount：
     */
    @Test
    public void selectByWrapperCount() {
        User user = new User();
        user.setName("luofeng");
        user.setAge(18);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);
        Integer users = userMapper.selectCount(userQueryWrapper);
        System.err.println(users);
    }
    /**
     * selectOne：多出来会报错
     */
    @Test
    public void selectByWrapperOne() {
        User user = new User();
        user.setName("luofeng2");
        user.setAge(18);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);
        User users = userMapper.selectOne(userQueryWrapper);
        System.err.println(users);
    }

    /**
     * 条件构造其器查询
     */
    @Test
    public void selectByWrapper() {
//        QueryWrapper<User> query = Wrappers.query();
        QueryWrapper<User> userQueryWrapper;
        //TODO 1. name like "%雨%" and age < 40
        select1();
        //TODO 2.  name like "%雨%" and age between 20 and 40 and email is not null
        select2();
        //TODO 3. name like '王%' or age>=25 order by age desc,id asc
        select3();
        //TODO 4. 创建日期为2019年2月14日并且直属上级为名字为王姓 , 子查询 date_format(create_time,'%Y-%m-%d')='2019-02-14' and manager_id in (select id from user where name like '王%')
        select4();
        //TODO 5. 名字为王姓并且（年龄小于40或邮箱不为空） name like '王%' and (age<40 or email is not null)
        select5();
        //TODO 6.名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）name like '王%' or (age<40 and age>20 and email is not null)
        select6();
        //TODO 7. （年龄小于40或邮箱不为空）并且名字为王姓 (age<40 or email is not null) and name like '王%'
        select7();
        //TODO 8. 年龄为30、31、34、35 age in (30、31、34、35)
        sekect8();
        //TODO 9.只返回满足条件的其中一条语句即可 ，last无视优化规则直接拼接到最后 limit 1
        select9();
        //TODO 10.名字中包含雨并且年龄小于40(需求1加强版),select写前面写后面都可以
        select10();
        //TODO 11.按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄。
        //并且只取年龄总和小于500的组。
        //select avg(age) avg_age,min(age) min_age,max(age) max_age
        //from user
        //group by manager_id
        //having sum(age) <500
        select11();


    }

    private void select11() {
        QueryWrapper<User> userQueryWrapper;
        userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .select("avg(age) avg_age","min(age) min_age","max(age) max_age")
                .groupBy("manager_id")
                .having("sum(age) <{0}", 500);
        List<User> users3 = userMapper.selectList(userQueryWrapper);
        users3.forEach(System.err::println);
    }

    private void select10() {
        QueryWrapper<User> userQueryWrapper;//第一种情况：select id,name
        //	           from user
        //	           where name like '%雨%' and age<40
        //第二种情况-排除列：select id,name,age,email
        //	           from user
        //	           where name like '%雨%' and age<40
        userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .select("id", "name")
                .like("name", "雨")
                .lt("age", 40);
        List<User> users9 = userMapper.selectList(userQueryWrapper);
        users9.forEach(System.err::println);
        userQueryWrapper
                .like("name", "雨")
                .lt("age", 40)
                .select(User.class, info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("manager_id"));
        List<User> users19 = userMapper.selectList(userQueryWrapper);
        users19.forEach(System.err::println);
    }

    private void select9() {
        QueryWrapper<User> userQueryWrapper;
        userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.last("limit 1");
        List<User> users8 = userMapper.selectList(userQueryWrapper);
        users8.forEach(System.err::println);
    }

    private void sekect8() {
        QueryWrapper<User> userQueryWrapper;
        userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .in("age", Arrays.asList(30, 31, 34, 35));
        List<User> users7 = userMapper.selectList(userQueryWrapper);
        users7.forEach(System.err::println);
    }

    private void select7() {
        QueryWrapper<User> userQueryWrapper;
        userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .nested(wq -> wq.lt("age", 40).or().isNotNull("email"))
                .likeRight("name", "王");
        List<User> users6 = userMapper.selectList(userQueryWrapper);
        users6.forEach(System.err::println);
    }

    private void select6() {
        QueryWrapper<User> userQueryWrapper;
        userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .likeRight("name", "王")
                .or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));
        List<User> users5 = userMapper.selectList(userQueryWrapper);
        users5.forEach(System.err::println);
    }

    private void select5() {
        QueryWrapper<User> userQueryWrapper;
        userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .likeRight("name", "王")
                .and(wq -> wq.lt("age", 40).or().isNotNull("email"));
        List<User> users4 = userMapper.selectList(userQueryWrapper);
        users4.forEach(System.err::println);
    }

    private void select4() {
        QueryWrapper<User> userQueryWrapper;
        userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                //applySql里面使用占位符{}，防止sql注入风险
                .apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-02-14")
                .inSql("manager_id", "select id from user where name like '王%'");
        List<User> users3 = userMapper.selectList(userQueryWrapper);
        users3.forEach(System.err::println);
    }

    private void select3() {
        QueryWrapper<User> userQueryWrapper;
        userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .likeRight("name", "王")
                .or()
                .ge("age", 25)
                .orderByDesc("age")
                .orderByAsc("id");
        List<User> users2 = userMapper.selectList(userQueryWrapper);
        users2.forEach(System.err::println);
    }

    private void select2() {
        QueryWrapper<User> userQueryWrapper;
        userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .like("name", "雨")
                .between("age", 20, 40)
                .isNotNull("email");
        List<User> users1 = userMapper.selectList(userQueryWrapper);
        users1.forEach(System.err::println);
    }

    private void select1() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .like("name", "雨")
                .lt("age", 40);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.err::println);
    }


}
