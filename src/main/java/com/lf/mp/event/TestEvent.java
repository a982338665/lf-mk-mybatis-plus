package com.lf.mp.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestEvent {
    public static void main(String[] args) {
        //初始化配置参数
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        // 获得DemoPublisher的Bean
        DemoPublisher demoPublisher = context.getBean(DemoPublisher.class);
        // 发布消息
        demoPublisher.publisher("Hello Demo Event");
        context.close();
    }
}
