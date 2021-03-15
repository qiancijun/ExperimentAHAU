package com.qiancijun.application.controls;

import com.qiancijun.application.config.Style;
import javafx.scene.control.Button;

/**
 * @author Xu Chun
 * @Desc 其余按键
 * @Time 2021/3/15 19:45
 */

public class OthersBtns {
    private Button[] btns;

    public OthersBtns() {
        btns = new Button[7];
        for (int i = 0; i < 7; i++) {
            btns[i] = new Button();
        }
        btns[0].setText("退格");
        btns[1].setText("CE");
        btns[2].setText("C");
        btns[3].setText("MC");
        btns[4].setText("MR");
        btns[5].setText("MS");
        btns[6].setText("M+");
        for (int i = 3; i <= 6; i++) {
//            btns[i].setStyle(Style.othersBtnStyle1);
            btns[i].getStyleClass().add("othersBtnStyle1");
        }
        for (int i = 0; i <= 2; i++) {
//            btns[i].setStyle(Style.othersBtnStyle1);
            btns[i].getStyleClass().add("othersBtnStyle2");
        }
    }

    public Button[] getBtns() {
        return btns;
    }
}
