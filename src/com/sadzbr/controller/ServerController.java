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


/**
 * Kontroler serwera
 */
public class ServerController implements Initializable {
    /**
     * Przycisk wyłączenia serwera
     */
    @FXML private Button switchOfButton;
    /**
     * Pole tekstowe na logi serwera
     */
    @FXML private TextArea logArea;
    /**
     * Określa czy serwer działa
     */
    private boolean isRunning = false;

    /**
     * Dodaje wiadomość do logArea
     * @param message Wiadomość
     */
    public void addMessage(String message) {
        logArea.setText(logArea.getText() + "\n [" + new Date() + "] " + message);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logArea.setEditable(false);
        logArea.setText("");
    }

    /**
     * Pobiera informacje czy serwera działa
     * @return True jeżeli działa, false w przeciwnym wypadku
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Handler dla przycisku wyłączenia serwea
     * @param actionEvent Event
     * @throws IOException Wyjątek
     */
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

    /**
     * Wyłącza serwer
     * @throws IOException Wyjątek
     */
    public void serverShutdown() throws IOException {
        ServerService serverService = ServerService.getINSTANCE();
        serverService.stop();
    }
}
