package com.qiancijun.application.core.chain.impl;

import com.qiancijun.application.common.Data;
import com.qiancijun.application.controls.LabelView;
import com.qiancijun.application.core.chain.CalcuteRequest;
import com.qiancijun.application.core.chain.Handler;
import com.qiancijun.application.core.observer.impl.DataObserver;

import java.util.Deque;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/3/18 8:09
 */

public class MultiplyHandler extends Handler {

    private Data data = Data.getInstance();

    @Override
    public void handlerRequest(CalcuteRequest request) {
        if ("*".equals(request.getType())) {
            Deque<Double> stack = data.getStack();
            if (stack.size() < 2) return;
            double num1 = (double) stack.pollFirst();
            double num2 = (double) stack.pollFirst();
            stack.addLast(num1 * num2);
            DataObserver.getInstance().change(stack.getLast().toString());
            // 计算完成后清空屏幕上的显示
            LabelView.getInstance().getResultLabel().setText("0.");
            data.clean();
        } else {
            getHandler().handlerRequest(request);
        }
    }
}
