package com.qiancijun;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/4/13 22:57
 */

public class GraphApplication extends Application {

    double posX, posY;
    double curX, curY;
    Integer flag = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane fp = new FlowPane();
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "矩形", "直线", "椭圆", "圆形")
        );
        cb.getSelectionModel().selectedIndexProperty().addListener(((observable, oldValue, newValue) -> {
//            System.out.println(oldValue);
//            System.out.println(newValue);
            flag = (Integer) newValue;
        }));

        Canvas canvas = new Canvas(1000, 1000);
        GraphicsContext context2D = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed((event -> {
            posX = event.getSceneX();
            posY = event.getSceneY();
            System.out.println(posX + " " + posY);
            event.consume();
        }));
        canvas.setOnMouseDragged(event -> {
            curX = event.getSceneX();
            curY = event.getSceneY();
        });
        canvas.setOnMouseReleased(event -> {
            if (flag == 0) {
//                context2D.setFill(new Color(0, 0, 0, 0.1));
                context2D.setStroke(Color.BLUE);
                context2D.setLineWidth(1);
                context2D.strokeRect(posX, posY, event.getSceneX() - posX, event.getSceneY() - posY);
            } else if (flag == 1){
                context2D.setFill(Color.RED);
                context2D.setStroke(Color.RED);
                context2D.setLineWidth(1);
                context2D.strokeLine(posX, posY, event.getSceneX(), event.getSceneY());
            } else if (flag == 2) {
                context2D.setStroke(Color.ORANGE);
                context2D.setLineWidth(1);
                context2D.strokeOval(posX, posY, event.getSceneX() - posX, event.getSceneY() - posY);
            } else if (flag == 3) {
                context2D.setStroke(Color.GREEN);
                context2D.setLineWidth(1);
                context2D.strokeOval(posX, posY, event.getSceneX() - posX, event.getSceneX() - posX);
            }

        });

        fp.getChildren().add(cb);
        fp.getChildren().add(canvas);
        Scene s = new Scene(fp, 1000, 1000);
        primaryStage.setScene(s);
        primaryStage.show();
    }
}
