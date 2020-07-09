package com.lf.mp.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by lishengbo on 2017-12-18
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    /**
     * 基本访问（单路径）
     * @return
     */
    @RequestMapping(value = "/1",method = RequestMethod.GET)
    public String say(){
        System.out.print("hello");
        return "Hello world!";
    }

}
