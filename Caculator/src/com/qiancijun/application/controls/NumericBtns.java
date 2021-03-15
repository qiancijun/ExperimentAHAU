package com.qiancijun.application.controls;

import com.qiancijun.application.config.Style;
import javafx.scene.control.Button;

/**
 * @author Xu Chun
 * @Desc 数字按钮键
 * @Time 2021/3/15 19:51
 */

public class NumericBtns {
    private Button[] btns;

    public NumericBtns() {
        btns = new Button[12];
        for (int i = 0; i < 12; i++) {
            Button btn = new Button();
            btn.setText(i + "");
//            btn.setStyle(Style.numericBtnStyle);
            btn.getStyleClass().add("numericBtnStyle");
            btns[i] = btn;
        }
        btns[10].setText("+/-");
        btns[11].setText(".");
    }

    public Button[] getBtns() {
        return btns;
    }
}
