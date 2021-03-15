package com.qiancijun.application.controls;

import com.qiancijun.application.config.Style;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * @author Xu Chun
 * @Desc 操作符键
 * @Time 2021/3/15 19:43
 */

public class OperationBtns {
    private Button[] btns;

    public OperationBtns() {
        btns = new Button[8];
        for (int i = 0; i < 8; i++) {
            Button btn = new Button();
//            btn.setStyle(Style.numericBtnStyle);
            btn.getStyleClass().add("numericBtnStyle");
            btns[i] = btn;
        }
        btns[0].setText("/");
        btns[1].setText("*");
        btns[2].setText("-");
        btns[3].setText("+");
        btns[4].setText("sqrt");
        btns[5].setText("%");
        btns[6].setText("1/x");
        btns[7].setText("=");

        btns[0].setTextFill(Color.RED);
        btns[1].setTextFill(Color.RED);
        btns[2].setTextFill(Color.RED);
        btns[3].setTextFill(Color.RED);
        btns[7].setTextFill(Color.RED);
    }

    public Button[] getBtns() {
        return btns;
    }
}
