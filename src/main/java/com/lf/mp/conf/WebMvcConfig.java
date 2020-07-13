package com.lf.mp.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
   
    /**
     * 静态资源文件映射配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 注意 :
        // 1. addResourceHandler 参数可以有多个
        // 2. addResourceLocations 参数可以是多个，可以混合使用 file: 和 classpath : 资源路径
        // 3. addResourceLocations 参数中资源路径必须使用 / 结尾，如果没有此结尾则访问不到

        // 映射到文件系统中的静态文件(应用运行时，这些文件无业务逻辑，但可能被替换或者修改)
//        registry.addResourceHandler("/repo/**").addResourceLocations("file:/tmp/");

        // 映射到jar包内的静态文件(真正的静态文件，应用运行时，这些文件无业务逻辑，也不能被替换或者修改)
        registry.addResourceHandler("/test2/**").addResourceLocations("classpath:/test/");
//        registry.addResourceHandler("/my-static/**").addResourceLocations("classpath:/my-static/");
    }

   // ...

}
