package com.qiancijun.notebook.controller;

import com.qiancijun.notebook.NoteBookApplication;
import com.qiancijun.notebook.core.FileUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/3/19 10:35
 */

public class OperationController implements Initializable {

    private Stage stage = NoteBookApplication.getStage();
    private double xOffset = 0;
    private double yOffset = 0;
    private int bit = 0;//left,right,top,bottom
    private static final double RESIZE_WIDTH = 5.00;
    private static final double MIN_WIDTH = 200.00;
    private static final double MIN_HEIGHT = 200.00;

    private double x = 0.00;
    private double y = 0.00;
    private double width = 0.00;
    private double height = 0.00;
    private boolean isMax = false;
    private boolean isRight;// 是否处于右边界调整窗口状态
    private boolean isBottomRight;// 是否处于右下角调整窗口状态
    private boolean isBottom;// 是否处于下边界调整窗口状态

    private File file;
    private String txt;
    @FXML
    private Button minimize, maximize, close, open, save;

    @FXML
    private MenuItem newFile, saveFile;

    @FXML
    private BorderPane root;

    @FXML
    private Menu menu;

    @FXML
    private TextArea textArea;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label easycode;

    @FXML
    private VBox vBox;

    public OperationController() {
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImageView bkg1 = new ImageView("com/qiancijun/notebook/img/关闭.png");
        ImageView bkg2 = new ImageView("com/qiancijun/notebook/img/最大化.png");
        ImageView bkg3 = new ImageView("com/qiancijun/notebook/img/最小化.png");
        ImageView bkg4 = new ImageView("com/qiancijun/notebook/img/书本.png");
        ImageView bkg5 = new ImageView("com/qiancijun/notebook/img/新建.png");
        ImageView bkg6 = new ImageView("com/qiancijun/notebook/img/保存.png");
        close.setGraphic(bkg1);
        maximize.setGraphic(bkg2);
        minimize.setGraphic(bkg3);
        open.setGraphic(bkg5);
        save.setGraphic(bkg6);
        textArea.prefHeightProperty().bind(scrollPane.heightProperty());
        textArea.prefWidthProperty().bind(root.widthProperty());

        stage.xProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue != null && !isMax) {
                    x = newValue.doubleValue();
                }
            }
        });
        stage.yProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue != null && !isMax) {
                    y = newValue.doubleValue();
                }
            }
        });
        stage.widthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue != null && !isMax) {
                    width = newValue.doubleValue();
                }
            }
        });
        stage.heightProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue != null && !isMax) {
                    height = newValue.doubleValue();
                }
            }
        });

        root.setOnMouseMoved((MouseEvent event) -> {
            event.consume();
            double x = event.getSceneX();
            double y = event.getSceneY();
            double width = stage.getWidth();
            double height = stage.getHeight();
            Cursor cursorType = Cursor.DEFAULT;// 鼠标光标初始为默认类型，若未进入调整窗口状态，保持默认类型
            // 先将所有调整窗口状态重置
            isRight = isBottomRight = isBottom = false;
            if (y >= height - RESIZE_WIDTH) {
                if (x <= RESIZE_WIDTH) {// 左下角调整窗口状态
                    //不处理

                } else if (x >= width - RESIZE_WIDTH) {// 右下角调整窗口状态
                    isBottomRight = true;
                    cursorType = Cursor.SE_RESIZE;
                } else {// 下边界调整窗口状态
                    isBottom = true;
                    cursorType = Cursor.S_RESIZE;
                }
            } else if (x >= width - RESIZE_WIDTH) {// 右边界调整窗口状态
                isRight = true;
                cursorType = Cursor.E_RESIZE;
            }
            // 最后改变鼠标光标
            root.setCursor(cursorType);
        });

        root.setOnMouseDragged((MouseEvent event) -> {

            //根据鼠标的横纵坐标移动dialog位置
            event.consume();
            if (yOffset != 0 ) {
                stage.setX(event.getScreenX() - xOffset);
                if (event.getScreenY() - yOffset < 0) {
                    stage.setY(0);
                } else {
                    stage.setY(event.getScreenY() - yOffset);
                }
            }

            double x = event.getSceneX();
            double y = event.getSceneY();
            // 保存窗口改变后的x、y坐标和宽度、高度，用于预判是否会小于最小宽度、最小高度
            double nextX = stage.getX();
            double nextY = stage.getY();
            double nextWidth = stage.getWidth();
            double nextHeight = stage.getHeight();
            if (isRight || isBottomRight) {// 所有右边调整窗口状态
                nextWidth = x;
            }
            if (isBottomRight || isBottom) {// 所有下边调整窗口状态
                nextHeight = y;
            }
            if (nextWidth <= MIN_WIDTH) {// 如果窗口改变后的宽度小于最小宽度，则宽度调整到最小宽度
                nextWidth = MIN_WIDTH;
            }
            if (nextHeight <= MIN_HEIGHT) {// 如果窗口改变后的高度小于最小高度，则高度调整到最小高度
                nextHeight = MIN_HEIGHT;
            }
            // 最后统一改变窗口的x、y坐标和宽度、高度，可以防止刷新频繁出现的屏闪情况
            stage.setX(nextX);
            stage.setY(nextY);
            stage.setWidth(nextWidth);
            stage.setHeight(nextHeight);

        });

    }

    private void mouseMoveHandle(MouseEvent event) {

        event.consume();
        double x = event.getSceneX();
        double y = event.getSceneY();
        double width = stage.getWidth();
        double height = stage.getHeight();
        Cursor cursorType = Cursor.DEFAULT;
        bit = 0;
        if (y >= height - RESIZE_WIDTH) {
            if (x <= RESIZE_WIDTH) {
                System.out.println("left");
                bit |= 1 << 3;
            } else if (x >= width - RESIZE_WIDTH) {
                bit |= 1;
                bit |= 1 << 2;
                cursorType = Cursor.SE_RESIZE;
            } else {
                bit |= 1;
                cursorType = Cursor.S_RESIZE;
            }
        } else if (x >= width - RESIZE_WIDTH) {
            bit |= 1 << 2;
            cursorType = Cursor.E_RESIZE;
        }
        root.setCursor(cursorType);
    }

    private void mouseDraggedHandle(MouseEvent event) {
        event.consume();
        double x = event.getSceneX();
        double y = event.getSceneY();
        double nextX = stage.getX();
        double nextY = stage.getY();
        double nextWidth = stage.getWidth();
        double nextHeight = stage.getHeight();
        if ((bit & 1 << 2) != 0) {
            nextWidth = x;
        }
        if ((bit & 1) != 0) {
            nextHeight = y;
        }
        if (nextWidth <= MIN_WIDTH) {
            nextWidth = MIN_WIDTH;
        }
        if (nextHeight <= MIN_HEIGHT) {
            nextHeight = MIN_HEIGHT;
        }
        stage.setX(nextX);
        stage.setY(nextY);
        stage.setWidth(nextWidth);
        stage.setHeight(nextHeight);
    }

    @FXML
    private void minimizeWindow() {
        stage = (Stage) root.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void maximizeWindow() {
        stage = (Stage) root.getScene().getWindow();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
    }

    @FXML
    private void closeWindow() {
        System.exit(0);
    }

    @FXML
    private void openFile() throws IOException {
        of();
    }

    public void of() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("打开文件");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt"),
                new FileChooser.ExtensionFilter("md", "*.md"),
                new FileChooser.ExtensionFilter("c", "*.c"),
                new FileChooser.ExtensionFilter("cpp", "*.cpp"),
                new FileChooser.ExtensionFilter("Java", "*.java")
        );
        file = fileChooser.showOpenDialog(stage);
        if (file == null) return;
        txt = FileUtil.readFile(file);
        easycode.setText(file.getAbsolutePath());
        textArea.setText(txt);
    }

    @FXML
    private void saveFile() throws IOException {
        sf();
    }

    public void sf() throws IOException {
        if (file == null || txt == null) {
            return;
        }
        FileUtil.saveFile(file, textArea.getText());
    }

    @FXML
    private void createFile() {
        textArea.setText("");
        easycode.setText("EasyCode");
    }

    @FXML
    private void save() throws IOException {
        FileChooser fileChooser1 = new FileChooser();
        fileChooser1.setTitle("保存文件");
        File file = fileChooser1.showSaveDialog(stage);
        if (file != null) {
            FileUtil.saveFile(file, textArea.getText());
        }
    }
}
