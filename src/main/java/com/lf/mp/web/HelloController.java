package com.lf.mp.web;

import com.lf.mp.entity.User;
import com.lf.mp.entity.Users;
import com.lf.mp.vo.TestVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试一级接口分组
 * create by lishengbo on 2017-12-18
 * @description 测试分组
 * 当包含 description 的时候，取注解后面的内容，否则取整个文档注释的内容
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    /**
     * 基本访问（单路径）测试类1
     * @param name
     */
    @RequestMapping(value = "/1",method = RequestMethod.GET)
    public String say(String name ){
        System.out.print("hello");
        return "Hello world!";
    }
    /**
     * 基本访问（单路径）测试类2
     * @param users
     */
    @RequestMapping(value = "/2",method = RequestMethod.GET)
    public User say2(Users users){
        System.out.print("hello");
        return new User();
    }
    /**
     * 基本访问（单路径）测试类3
     * @param users
     */
    @RequestMapping(value = "/3",method = RequestMethod.POST)
    public List<String> say3(@RequestBody Users users ){
        System.out.print("hello");
        return new ArrayList<>();
    }
    /**
     * 基本访问（单路径）测试类4
     * @param users
     */
    @RequestMapping(value = "/4",method = RequestMethod.POST)
    public TestVo say4(@RequestBody List<Users> users ){
        System.out.print("hello");
        return new TestVo();
    }

}
