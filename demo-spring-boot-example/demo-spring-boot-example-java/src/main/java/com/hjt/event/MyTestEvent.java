package com.hjt.event;

import org.springframework.context.ApplicationEvent;

public class MyTestEvent extends ApplicationEvent {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String msg ;

    public MyTestEvent(Object source,String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

