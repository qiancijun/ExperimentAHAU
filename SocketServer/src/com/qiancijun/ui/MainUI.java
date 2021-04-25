package com.qiancijun.ui;

import com.qiancijun.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author Xu Chun
 * @Desc UI
 * @Time 2021/4/25 20:06
 */

public class MainUI extends Application {

    private AnchorPane root;
    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader fx = new FXMLLoader();
        URL url = fx.getClassLoader().getResource("com/qiancijun/ui/main.fxml");
        fx.setLocation(url);
        root = fx.load();

        Controller controller = fx.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
