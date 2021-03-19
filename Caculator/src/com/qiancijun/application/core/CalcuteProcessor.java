package com.qiancijun.application.core;

import com.qiancijun.application.common.Data;
import com.qiancijun.application.core.chain.CalcuteRequest;
import com.qiancijun.application.core.chain.Handler;
import com.qiancijun.application.core.chain.impl.*;
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
        // 职责链
        handler = new AddHandler();
        SubtractHandler subtractHandler = new SubtractHandler();
        handler.setHandler(subtractHandler);
        MultiplyHandler multiplyHandler = new MultiplyHandler();
        subtractHandler.setHandler(multiplyHandler);
        DivideHandler divideHandler = new DivideHandler();
        multiplyHandler.setHandler(divideHandler);
        ModHandler modHandler = new ModHandler();
        divideHandler.setHandler(modHandler);
        SqrtHandler sqrtHandler = new SqrtHandler();
        modHandler.setHandler(sqrtHandler);
        ReciHandler reciHandler = new ReciHandler();
        sqrtHandler.setHandler(reciHandler);
        DefaultHandler defaultHandler = new DefaultHandler();
        reciHandler.setHandler(defaultHandler);
    }

    public void process(CalcuteRequest request) {
        handler.handlerRequest(request);
    }

    public static CalcuteProcessor getInstance() {
        if (instance == null) {
            synchronized (CalcuteProcessor.class) {
                if (instance == null) {
                    instance = new CalcuteProcessor();
                }
            }
        }
        return instance;
    }

}
