package com.lf.mp;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/13 16:28
 * @Description :
 */
public class JApiDocs {

    /**
     * 访问：http://localhost:8080/V1.0/index.html 获取接口文档
     *
     * @param args
     */
    public static void main(String[] args) {
        japi();
    }

    public static void japi() {
        DocsConfig config = new DocsConfig();
        config.setProjectPath("./"); // 项目根目录
//        config.setProjectPath("D:/git-20200518/lf-mk-mybatisPlus"); // 项目根目录
        config.setProjectName("lf-mk-mybatisPlus"); // 项目名称
        config.setApiVersion("V1.0");       // 声明该API的版本
        config.setDocsPath(".\\src\\main\\resources\\META-INF\\resources"); // 生成API 文档所在目录
//        config.setDocsPath("D:\\git-20200518\\lf-mk-mybatisPlus\\src\\main\\resources\\docs"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
    }
}
