package com.lf.mp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * create by lishengbo on 2017-12-18
 * @description 首页
 */
@Controller
@RequestMapping("/test1")
public class IndexController {

    /**
     * 基本访问（单路径）
     *
     * @return
     */
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String say() {
        System.err.println(11);
        return "index-template";
    }
//    @RequestMapping(value = "/{version}/{path}", method = RequestMethod.GET)
//    public String say(@PathVariable("path") String path, @PathVariable("version") String version) {
//        StringBuilder sb = new StringBuilder(version);
//        sb.append("/");
//        if (StringUtils.isEmpty(path)) {
//            sb.append("index.html");
//        } else {
//            sb.append(path);
//            sb.append(".html");
//        }
//        System.err.println("访问：" + sb.toString());
//        return sb.toString();
//    }

}
