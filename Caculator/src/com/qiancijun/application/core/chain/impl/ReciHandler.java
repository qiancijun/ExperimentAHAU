package com.qiancijun.application.core.chain.impl;

import com.qiancijun.application.common.Data;
import com.qiancijun.application.controls.LabelView;
import com.qiancijun.application.core.chain.CalcuteRequest;
import com.qiancijun.application.core.chain.Handler;
import javafx.scene.control.Label;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/3/18 16:15
 */

public class ReciHandler extends Handler {

    private Label label = LabelView.getInstance().getResultLabel();

    @Override
    public void handlerRequest(CalcuteRequest request) {
        if ("reci".equals(request.getType())) {
            Double num = getData().getStack().pollFirst();
            getData().getStack().addLast(1 / num);
        } else {
            getHandler().handlerRequest(request);
        }
    }
}
