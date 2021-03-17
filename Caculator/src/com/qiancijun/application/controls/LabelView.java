package com.qiancijun.application.controls;

import javafx.scene.control.Label;

/**
 * @author Xu Chun
 * @Desc 显示用的 Label，单例模式，全局唯一
 * @Time 2021/3/17 8:56
 */

public class LabelView {
    private Label resultLabel;
    private static volatile LabelView instance;
    private LabelView() {
        resultLabel = new Label();
        resultLabel.setText("0.");
        resultLabel.getStyleClass().add("resultLabelStyle");
    }

    public Label getResultLabel() {
        return resultLabel;
    }

    public static LabelView getInstance() {
        if (instance == null) {
            synchronized (LabelView.class) {
                if (instance == null) {
                    instance = new LabelView();
                }
            }
        }
        return instance;
    }

}
