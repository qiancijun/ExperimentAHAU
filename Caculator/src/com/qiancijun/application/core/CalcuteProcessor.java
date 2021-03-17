package com.qiancijun.application.core;

import com.qiancijun.application.common.Data;
import com.qiancijun.application.core.chain.CalcuteRequest;
import com.qiancijun.application.core.chain.Handler;
import com.qiancijun.application.core.chain.impl.AddHandler;
import com.qiancijun.application.core.observer.Observer;
import com.qiancijun.application.core.observer.impl.DataObserver;

/**
 * @author Xu Chun
 * @Desc 初始化职责链
 * @Time 2021/3/17 14:21
 */

public class CalcuteProcessor {
    private Handler handler;
    public static volatile CalcuteProcessor instance;

    private CalcuteProcessor() {
        handler = new AddHandler(); // 职责链的头节点
    }

    public void process(CalcuteRequest request) {
        handler.handlerRequest(request);
    }

    public static CalcuteProcessor getInstance() {
        if (instance == null) {
            synchronized (Data.class) {
                if (instance == null) {
                    instance = new CalcuteProcessor();
                }
            }
        }
        return instance;
    }

}
