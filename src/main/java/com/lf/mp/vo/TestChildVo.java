package com.lf.mp.vo;

import com.lf.mp.entity.Users;
import lombok.Data;

import java.util.List;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/14 11:07
 * @Description :
 */
@Data
public class TestChildVo {

    private int id;
    private int age;
    private String name;
    private String[] names;
    private List<Users> usersList;
}
