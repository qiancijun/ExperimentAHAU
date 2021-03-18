package com.qiancijun.application.common;

import com.qiancijun.application.controls.LabelView;
import javafx.scene.control.Label;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Xu Chun
 * @Desc 全局数据类，计算所需要的数据从这里取
 * @Time 2021/3/17 8:44
 */

public class Data {
    private StringBuilder text;
    private static volatile Data instance;
    private Label labelView = LabelView.getInstance().getResultLabel();
    private boolean isDouble = false;
    private boolean isFirsetOperate = false; // 是否第一次输入操作符
    private String operator;
    private Deque<Double> stack; // 记录前一次输入的数据
    private Queue<Double> store;
    public StringBuilder getText() {
        return text;
    }

    public Queue<Double> getStore() {
        return store;
    }

    private Data() {
        text = new StringBuilder();
        stack = new LinkedList<>();
        store = new LinkedList<>();
    };
    // 当使用到该方法时，才会创建instance
    public static Data getInstance() {
        if (instance == null) {
            synchronized (Data.class) {
                if (instance == null) {
                    instance = new Data();
                }
            }
        }
        return instance;
    }

    public void reset() {
        text = new StringBuilder();
        isDouble = false;
    }

    public void clean() {
        isDouble = false;
        text = new StringBuilder();
        LabelView.getInstance().getResultLabel().setText("0.");
    }

    public void renew() {
        text = new StringBuilder();
        isDouble = false;
        LabelView.getInstance().getResultLabel().setText("0.");
        stack.clear();
    }

    public void deleteText() {
        if (text.length() == 0) {
            return;
        }
        text.deleteCharAt(text.length() - 1);
        LabelView.getInstance().getResultLabel().setText(text.toString());
    }

    public void changeText(String val) {
        this.text.append(val);
    }

    public boolean isDouble() {
        return this.isDouble;
    }

    public void setDouble(boolean aDouble) {
        isDouble = aDouble;
    }

    public Deque<Double> getStack() {
        return stack;
    }

    public boolean isFirsetOperate() {
        return isFirsetOperate;
    }

    public void setFirsetOperate(boolean firsetOperate) {
        isFirsetOperate = firsetOperate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}
