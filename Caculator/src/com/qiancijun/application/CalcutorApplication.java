package com.qiancijun.application;

import com.qiancijun.application.config.Config;
import com.qiancijun.application.config.Style;
import com.qiancijun.application.controls.NumericBtns;
import com.qiancijun.application.controls.OperationBtns;
import com.qiancijun.application.controls.OthersBtns;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/3/15 18:46
 */

public class CalcutorApplication extends Application {

    private FlowPane flowPane;
    private FlowPane[] btnsPane;
    private Label resultLabel;
    private Button[] numericBtns, operationBtns, othersBtns;

    @Override
    public void init() throws Exception {
        // 初始化需要的数据
    }

    @Override
    public void start(Stage stage) throws Exception {

        flowPane = new FlowPane();
        flowPane.setStyle("-fx-vgap: 10px");
        Scene scene = new Scene(flowPane);
        scene.getStylesheets().add("com/qiancijun/application/css/main.css");
        defineView();
        initView();

        stage.setHeight(Config.WINDOW_HEIGHT);
        stage.setWidth(Config.WINDOW_WIDTH);
        stage.setTitle(Config.TITLE);
        stage.setScene(scene);
        stage.setResizable(false); // 不可缩放
        stage.show();
    }

    private void defineView() {
        resultLabel = new Label();
        btnsPane = new FlowPane[5];
        numericBtns = new NumericBtns().getBtns();
        operationBtns = new OperationBtns().getBtns();
        othersBtns = new OthersBtns().getBtns();

        for (int i = 0; i < 5; i++) {
            FlowPane fp = new FlowPane();
            if (i != 0) {
//                fp.setStyle(Style.flowPaneStype);
                fp.getStyleClass().add("flowPaneStype");
            }

            btnsPane[i] = fp;
        }

    }

    private void initView() {
        // 显示结果的标签
        resultLabel.setText("0.");
//        resultLabel.setStyle(Style.resultLabelStyle);
        resultLabel.getStyleClass().add("resultLabelStyle");
        flowPane.getChildren().add(resultLabel);

        btnsPane[0].getChildren().addAll(othersBtns[0], othersBtns[1], othersBtns[2]);
        btnsPane[1].getChildren().addAll(othersBtns[3], numericBtns[7], numericBtns[8], numericBtns[9], operationBtns[0], operationBtns[4]);
        btnsPane[2].getChildren().addAll(othersBtns[4], numericBtns[4], numericBtns[5], numericBtns[6], operationBtns[1], operationBtns[5]);
        btnsPane[3].getChildren().addAll(othersBtns[5], numericBtns[1], numericBtns[2], numericBtns[3], operationBtns[2], operationBtns[6]);
        btnsPane[4].getChildren().addAll(othersBtns[6], numericBtns[0], numericBtns[10], numericBtns[11], operationBtns[3], operationBtns[7]);

        for (FlowPane fp : btnsPane) {
            flowPane.getChildren().add(fp);
        }
    }

}
