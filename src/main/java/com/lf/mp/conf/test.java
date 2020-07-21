package com.lf.mp.conf;

import com.github.lfopenjavaswagger2word.util.GenerateDocxUtils;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/18 21:41
 * @Description :
 */
public class test {

    public static void main(String[] args) {
        GenerateDocxUtils.generateFilePDF("./swagger/file.json", null, "fauck");
    }
}
