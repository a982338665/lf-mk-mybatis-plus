package com.lf.mp.event.listener;

import com.lf.mp.event.DemoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
  * @author th
 *
  * @date 2021/7/11 21:28
 *
  * @Description DemoApplicationListen 实现监听事件ApplicationListener接口，并指定监听事件的类型
  */
@Component
public class Demo2ApplicationListen implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        //接收消息
        String msg = demoEvent.getMsg();
        List<String> list = demoEvent.getList();
        System.out.println("【通过接口实现监听】接收到了demoPublisher发布的消息：" + msg);
        list.forEach(e -> System.err.println("【通过接口实现监听】===" + e));
    }
}
