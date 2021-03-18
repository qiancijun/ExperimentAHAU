package com.qiancijun.application.core.chain;

import com.qiancijun.application.common.Data;

/**
 * @author Xu Chun
 * @Desc 职责链
 * @Time 2021/3/17 15:48
 */

public abstract class Handler {
    private Handler handler; // 下一个处理者
    private String name;
    private Data data = Data.getInstance();
    public abstract void handlerRequest(CalcuteRequest request);

    public Data getData() {
        return data;
    }

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
