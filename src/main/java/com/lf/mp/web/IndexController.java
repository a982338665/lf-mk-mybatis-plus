package com.lf.mp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * create by lishengbo on 2017-12-18
 * 首页
 */
@Controller
@RequestMapping("/test")
public class IndexController {

    /**
     * 基本访问（单路径）
     *
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String say() {
        System.err.println(11);
        return "index-template";
    }

}
