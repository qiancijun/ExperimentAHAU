package com.qiancijun.notebook;

import com.qiancijun.notebook.controller.OperationController;
import com.qiancijun.notebook.core.FileUtil;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/3/18 16:41
 */

public class NoteBookApplication extends Application {

    private BorderPane root;
    private Button close, minimize, maximize;
    private ToolBar window;
    private static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
//        BorderPane borderPane = new BorderPane(); // 主界面布局
        // 文本框的布局
        this.stage = stage;
        FXMLLoader fx = new FXMLLoader();
        URL url = fx.getClassLoader().getResource("com/qiancijun/notebook/main.fxml");
        fx.setLocation(url);
        root = fx.load();
        OperationController controller = fx.getController();

        initView();
        setClick();

        Scene scene = new Scene(root);


        // 快捷键
        KeyCodeCombination kccb = new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN);
        scene.getAccelerators().put(kccb, () -> {
            try {
                controller.sf();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        KeyCodeCombination kccb2 = new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN);
        scene.getAccelerators().put(kccb2, () -> {
            try {
                controller.of();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        stage.initStyle(StageStyle.TRANSPARENT);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        stage.setScene(scene);
        stage.show();


    }

    private void initView() {
        window = (ToolBar) root.lookup("#window");
    }

    private void setClick() {
        DragWindowHandler dragWindowHandler = new DragWindowHandler(stage);

        window.setOnMousePressed(dragWindowHandler);
        window.setOnMouseDragged(dragWindowHandler);
    }

    private class DragWindowHandler implements EventHandler<MouseEvent> {
        private Stage primaryStage; //primaryStage为start方法头中的Stage
        private double oldStageX;
        private double oldStageY;
        private double oldScreenX;
        private double oldScreenY;

        public DragWindowHandler(Stage primaryStage) { //构造器
            this.primaryStage = primaryStage;
        }
        @Override
        public void handle(MouseEvent e) {
            if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {    //鼠标按下的事件
                this.oldStageX = this.primaryStage.getX();
                this.oldStageY = this.primaryStage.getY();
                this.oldScreenX = e.getScreenX();
                this.oldScreenY = e.getScreenY();

            } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {  //鼠标拖动的事件
                this.primaryStage.setX(e.getScreenX() - this.oldScreenX + this.oldStageX);
                this.primaryStage.setY(e.getScreenY() - this.oldScreenY + this.oldStageY);
            }
        }
    }

    public static Stage getStage() {
        return stage;
    }
}
