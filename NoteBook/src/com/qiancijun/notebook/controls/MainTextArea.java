package com.qiancijun.notebook.controls;

import javafx.scene.control.TextArea;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/3/18 17:02
 */

public class MainTextArea {
    private TextArea textArea;
    public static volatile MainTextArea instance;

    private MainTextArea() {};

    public static MainTextArea getInstance() {
        if (instance == null) {
            synchronized (MainTextArea.class) {
                if (instance == null) {
                    instance = new MainTextArea();
                }
            }
        }
        return instance;
    }

    public TextArea getTextArea() {
        return textArea;
    }
}
