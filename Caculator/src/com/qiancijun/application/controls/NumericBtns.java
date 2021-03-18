package com.qiancijun.application.controls;

import com.qiancijun.application.common.Data;
import com.qiancijun.application.core.observer.Subject;
import com.qiancijun.application.core.observer.impl.DataObserver;
import com.qiancijun.application.core.observer.impl.NumericBtnSubject;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;


/**
 * @author Xu Chun
 * @Desc 数字按钮键
 * @Time 2021/3/15 19:51
 */
public class NumericBtns {
    private Button[] btns;
    private Data data = Data.getInstance();
    private Subject subject;
    public NumericBtns() {
        subject = new NumericBtnSubject(DataObserver.getInstance()); // 获取观察者
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
        setClick();
    }

    public Button[] getBtns() {
        return btns;
    }

    private void setClick() {
        // 点击事件
        btns[11].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!data.isDouble()) {
                subject.notify("."); // 通知观察者改变 Data 里的数据，下同
            }
        });
        for (int i = 0; i <= 9; i++) {
            int finalI = i;
            btns[i].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                subject.notify(finalI + "");
            });
        }
    }

}
