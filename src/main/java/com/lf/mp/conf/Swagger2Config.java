package com.lf.mp.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2 // 启用swagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        //=====添加head参数start============================
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("Authorization").description("AccessToken令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        // =========添加head参数end==================
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lf.mp.web")) //扫描路径
                .paths(PathSelectors.any()) //定义哪些路径的接口需要生成文档
                .build()
                .globalOperationParameters(pars) //配置每一个接口的 token
                .securitySchemes(security());         //配置公共的 token

    }

    private List<ApiKey> security() {
        List<ApiKey> apiKeyList = new ArrayList<ApiKey>();
        apiKeyList.add(new ApiKey("token", "token", "header"));
        return apiKeyList;
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger2 接口文档") //文档首页标题
                .description("接口文档") //文档描述信息
                .contact(new Contact("luofeng", "http://pingpingpang.cn", "982338665@qq.com")) //创建者信息
                .version("1.0") //文档版本
                .build();
    }
}
