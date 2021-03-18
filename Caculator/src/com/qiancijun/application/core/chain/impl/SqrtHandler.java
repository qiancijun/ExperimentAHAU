package com.qiancijun.application.core.chain.impl;

import com.qiancijun.application.common.Data;
import com.qiancijun.application.controls.LabelView;
import com.qiancijun.application.core.chain.CalcuteRequest;
import com.qiancijun.application.core.chain.Handler;
import javafx.scene.control.Label;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/3/18 8:35
 */

public class SqrtHandler extends Handler {

    private Label label = LabelView.getInstance().getResultLabel();

    @Override
    public void handlerRequest(CalcuteRequest request) {
        if ("sqrt".equals(request.getType())) {
            Double num = getData().getStack().pollFirst();
            getData().getStack().addLast(Math.sqrt(num));
        } else {
            getHandler().handlerRequest(request);
        }
    }
}
