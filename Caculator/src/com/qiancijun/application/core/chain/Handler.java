package com.qiancijun.application.core.chain;

/**
 * @author Xu Chun
 * @Desc 职责链
 * @Time 2021/3/17 15:48
 */

public abstract class Handler {
    private Handler handler; // 下一个处理者
    private String name;

    public abstract void handlerRequest(CalcuteRequest request);

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
