package com.qiancijun.notebook;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private boolean isRight;// 是否处于右边界调整窗口状态
    private boolean isBottomRight;// 是否处于右下角调整窗口状态
    private boolean isBottom;// 是否处于下边界调整窗口状态
    private double RESIZE_WIDTH = 3.00;
    private double MIN_WIDTH = 400.00;
    private double MIN_HEIGHT = 300.00;
    private double xOffset = 0, yOffset = 0;//自定义dialog移动横纵坐标
    private double x = 0.00;
    private double y = 0.00;
    private double width = 0.00;
    private double height = 0.00;
    private boolean isMax = false;
    @Override
    public void start(Stage stage) throws Exception {
//        BorderPane borderPane = new BorderPane(); // 主界面布局
        // 文本框的布局
        this.stage = stage;
        FXMLLoader fx = new FXMLLoader();
        URL url = fx.getClassLoader().getResource("com/qiancijun/notebook/main.fxml");
        fx.setLocation(url);
        root = fx.load();



        initView();
        setClick();

        Scene scene = new Scene(root);
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
//        minimize.setOnAction(event -> mainStage.setIconified(true));
//        maximize.setOnAction(event -> {
//            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//            mainStage.setX(primaryScreenBounds.getMinX());
//            mainStage.setY(primaryScreenBounds.getMinY());
//            mainStage.setWidth(primaryScreenBounds.getWidth());
//            mainStage.setHeight(primaryScreenBounds.getHeight());
//        });
//        close.setOnAction((event)->System.exit(0));
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
