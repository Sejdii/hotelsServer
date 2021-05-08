package com.sadzbr.controller;

import com.sadzbr.service.ServerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ServerController implements Initializable {
    @FXML private Button switchOfButton;
    @FXML private TextArea logArea;
    private boolean isRunning = false;

    public void addMessage(String message) {
        logArea.setText(logArea.getText() + "\n [" + new Date() + "] " + message);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logArea.setEditable(false);
        logArea.setText("");
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void handleSwitchOffButton(ActionEvent actionEvent) throws IOException {
        if(!isRunning) {
            isRunning = true;
            switchOfButton.setText("Wyłącz serwer");
            ServerService serverService = ServerService.getINSTANCE();
            serverService.run();
        } else {
            serverShutdown();
            isRunning = false;
            switchOfButton.setText("Uruchom serwer");
        }
    }

    public void serverShutdown() throws IOException {
        ServerService serverService = ServerService.getINSTANCE();
        serverService.stop();
    }
}
