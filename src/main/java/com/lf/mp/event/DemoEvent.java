package com.lf.mp.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * @author th
 * @date 2021/7/11 21:26
 * @Description DemoEvent 定义事件
 */
@Data
public class DemoEvent extends ApplicationEvent {
    private String msg;
    private List<String> list;

    public DemoEvent(Object source, String setMsg) {
        super(source);
        this.msg = setMsg;
    }

    public DemoEvent(Object source, String setMsg, List<String> list) {
        super(source);
        this.msg = setMsg;
        this.list = list;
    }

}
