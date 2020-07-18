package com.lf.mp.vo;

import com.lf.mp.entity.Users;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/14 11:06
 * @Description :
 */
@lombok.Data
public class TestVo {

    private int age;
    private Date time;
    private Double money;
    private String[] array;
    private Users[] usersArray;
    private Users users;
    private List<TestVo> testVos;
    private TestVo testVo;
    private List<Users> usersList;
}
