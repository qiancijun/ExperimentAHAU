package com.qiancijun;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.qiancijun.exception.WrongDateException;
import com.qiancijun.util.DateUtil;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.control.*;

public class Exp1 extends Application {
	
	private Label label;
	private TextField tf;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox();
		Scene scene = new Scene(root);
		
		tf = new TextField();
		tf.setMinWidth(400);
		label = new Label();
		
		root.getChildren().add(tf);
		root.getChildren().add(label);
		
		tf.setOnAction(e -> {
			String date = getTextFieldText();
			try {
				Date res = DateUtil.checkDate(date);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
				label.setText(sdf.format(res));
			} catch (WrongDateException e1) {
				label.setText(e1.getMsg());
			} catch (ParseException e2) {
				label.setText("日期格式化异常");
				e2.printStackTrace();
			}
		});
		
		stage.setHeight(500);
		stage.setWidth(500);
		stage.setTitle("异常处理");
		stage.setScene(scene);
		stage.show();
	}

	private String getTextFieldText() {
		return tf.getText();
	}
	
}
