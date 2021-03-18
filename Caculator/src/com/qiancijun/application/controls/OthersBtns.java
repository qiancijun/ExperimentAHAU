package com.qiancijun.application.controls;

import com.qiancijun.application.common.Data;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * @author Xu Chun
 * @Desc 其余按键
 * @Time 2021/3/15 19:45
 */

public class OthersBtns {
    private Button[] btns;

    private Data data = Data.getInstance();

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
            btns[i].getStyleClass().add("othersBtnStyle1");
        }
        for (int i = 0; i <= 2; i++) {
            btns[i].getStyleClass().add("othersBtnStyle2");
        }
        setClick();
    }

    private void setClick() {
        // 清空
        btns[1].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            data.renew();
        });
        btns[0].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            data.deleteText();
        });
    }

    public Button[] getBtns() {
        return btns;
    }
}
