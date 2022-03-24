package com.lf.mp.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DemoPublisher {
    @Autowired
    private ApplicationContext applicationContext;

    public void publisher(String msg) {
        List<String> list = new ArrayList<>();
        list.add("ddddddddddddddd");
        list.add("fffffffffffffff");
        //使用ApplicationContext中publishEvent方法来发布
        applicationContext.publishEvent(new DemoEvent(this, msg, list));
    }
}
