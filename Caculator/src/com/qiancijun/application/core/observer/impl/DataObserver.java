package com.qiancijun.application.core.observer.impl;

import com.qiancijun.application.common.Data;
import com.qiancijun.application.controls.LabelView;
import com.qiancijun.application.core.observer.Observer;
import javafx.scene.control.Label;

import java.util.Deque;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/3/17 9:09
 */

public class DataObserver implements Observer {

    public static volatile Observer instance;
    private Data data = Data.getInstance();
    private Label labelView = LabelView.getInstance().getResultLabel();

    private DataObserver() {}

    @Override
    public void change(String val) {
        // 小数点输入的特殊处理
        if (".".equals(val) || val.indexOf('.') != -1) {
            if (data.getText().toString().equals("")) {
                data.getText().append("0");
            }
            data.setDouble(true);
        }
        data.changeText(val);
        labelView.setText(data.getText().toString());
    }

    // 往队列中添加数据
    @Override
    public void update() {
        Label labelView = LabelView.getInstance().getResultLabel();
        labelView.setText("0.");
        String val = data.getText().toString();
        if ("".equals(val)) return;
        Deque<Double> stack = data.getStack();
//        if (data.isDouble()) {
//            Double num = Double.parseDouble(val);
//            stack.addLast(num);
//        } else {
//            Integer num = Integer.parseInt(val);
//            stack.addLast(num);
//        }
        Double num = Double.parseDouble(val);
        stack.addLast(num);
        // 添加完之后，重置显示的文本和 isDouble 状态
        data.reset();
    }

    public static Observer getInstance() {
        if (instance == null) {
            synchronized (Data.class) {
                if (instance == null) {
                    instance = new DataObserver();
                }
            }
        }
        return instance;
    }

}
