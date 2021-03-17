package com.qiancijun.application.controls;

import com.qiancijun.application.common.Data;
import com.qiancijun.application.core.CalcuteProcessor;
import com.qiancijun.application.core.chain.CalcuteRequest;
import com.qiancijun.application.core.observer.Subject;
import com.qiancijun.application.core.observer.impl.DataObserver;
import com.qiancijun.application.core.observer.impl.OperationBtnSubject;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * @author Xu Chun
 * @Desc 操作符键
 * @Time 2021/3/15 19:43
 */

public class OperationBtns {
    private Button[] btns;
    private Subject subject;
    private Data data = Data.getInstance();

    public OperationBtns() {
        subject = new OperationBtnSubject(DataObserver.getInstance()); // 获取观察者
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

        setClick();
    }

    private void setClick() {
        // 加号的功能
        btns[3].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            // 第一次按下操作符
            subject.addVal(); // 存入之前输入的值
            // 记录下操作符
            data.setOperator("+");
            LabelView.getInstance().getResultLabel().setText("0.");
            // 如果之前已经输入过操作符了，先做计算
            if (data.isFirsetOperate()) {
                // 从 Data 中取出上一次的操作符，交给职责链去操作
                CalcuteRequest request = new CalcuteRequest(data.getOperator());
                CalcuteProcessor.getInstance().process(request);
            }
            data.setFirsetOperate(true);
            System.out.println(data.getStack());
        });
    }

    public Button[] getBtns() {
        return btns;
    }
}
