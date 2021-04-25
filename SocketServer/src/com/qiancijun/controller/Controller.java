package com.qiancijun.controller;

import com.qiancijun.core.ThreadPool;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/4/25 20:36
 */

public class Controller implements Initializable {

    @FXML
    private TextField serverPort, clientPort;

    @FXML
    private Button serverBegin, clientBegin, serverSend, clientSend;

    @FXML
    private TextArea serverReceive, serverPush, clientReceive, clientPush;

    private Socket socket;
    private ServerSocket serverSocket;
    private InputStream serverIs, clientIs;
    private OutputStream serverOs, clientOs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serverBegin.setOnMouseReleased((e) -> {
            ThreadPool.getInstance().getPool().execute(
                    () -> {
                        try {
                            serverSocket = new ServerSocket(getServerPort());
                            serverReceive.setText("127.0.0.1:" + getServerPort() + "> 开启监听\n");
                            Socket s = serverSocket.accept();
                            while (true) {
                                serverIs = s.getInputStream();
                                serverOs = s.getOutputStream();
                                byte[] bs = new byte[1024];
                                int len = serverIs.read(bs);
                                serverReceive.setText(getServerReceiveContent() + "127.0.0.1:" + getServerPort() + "> 服务端收到的消息"
                                        + new String(bs, 0, len) + "\n");
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
            );
        });

        clientBegin.setOnMouseReleased((e) -> {
            ThreadPool.getInstance().getPool().execute(
                    () -> {
                        try {
                            socket = new Socket("127.0.0.1", getClientPort());
                            clientReceive.setText("127.0.0.1:" + getServerPort() + "> 开启监听\n");


                            while (true) {
                                clientOs = socket.getOutputStream();
                                clientIs = socket.getInputStream();
                                byte[] bs = new byte[1024];
                                int len = clientIs.read(bs);
                                clientReceive.setText(getClientReceiveContent() + "127.0.0.1:" + getClientPort() + "> 客户端收到的消息"
                                        + new String(bs, 0, len) + "\n");
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
            );
        });

        serverSend.setOnMouseReleased((e) -> {
            try {
                serverOs.write(serverPush.getText().getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        clientSend.setOnMouseReleased((e) -> {
            try {
                clientOs.write(clientPush.getText().getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private Integer getServerPort() {
        String port = serverPort.getText();
        return Integer.parseInt(port);
    }

    private Integer getClientPort() {
        String port = clientPort.getText();
        return Integer.parseInt(port);
    }

    private String getServerReceiveContent() {
        return serverReceive.getText();
    }

    private String getClientReceiveContent() {
        return clientReceive.getText();
    }

}
