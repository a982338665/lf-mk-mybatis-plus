package com.lf.mp.conf;

import com.github.lfopenjavaswagger2word.util.GenerateDocxUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 项目初始化后：调用Swagger2 接口获取JSON文档
 */
@Component
@Slf4j
public class SpringInit implements ApplicationRunner {

    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    @Value("${server.port}")
    private String port;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String uri = "http://127.0.0.1:" + port + "/v2/api-docs";
        log.info("访问：{}", uri);
        HttpGet httpget = new HttpGet(uri);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
                log.info("结果：{}", result != null ? "success" : "fail!");
//                TextUtil.write("./swagger/file.json", result);
                boolean b = GenerateDocxUtils.generateFileByJSON(result,null);
                System.err.println(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
