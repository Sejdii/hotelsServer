package com.sadzbr.utils;

import com.sadzbr.controller.SceneController;
import javafx.application.Platform;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Messages {
    /**
     * Loguje przesłaną wiadomość zarówno na ekranie serwera jak i w pliku .log.
     * @param message Wiadomość
     * @param isError Określa czy wiadomość wskazuję na wystąpienie błędu
     */
    static public void logMessage(String message, boolean isError) {
        SceneController sceneController = SceneController.getINSTANCE();
        Platform.runLater(() -> sceneController.getServerController().addMessage(message));
        Logger logger = Logger.getLogger("com.sadzbr");
        if(isError) {
            logger.warning(message);
        } else {
            logger.info(message);
        }
    }

    /**
     * Ustawia logger serwera. Obecna ustawienia zapisują logi w folderze domowym użytkownika. Konfiguracja pobierana
     * przez metodę znajduje się w pliku app.properties.
     * @return Zwraca true przy powodzeniu lub false w przeciwnym razie.
     */
    static public boolean setLogger() {
        Logger logger = Logger.getLogger("com.sadzbr");
        try {
            FileInputStream properties = new FileInputStream("app.properties");
            LogManager.getLogManager().readConfiguration(properties);


            properties.close();
        } catch (IOException e) {
            e.printStackTrace();
            logMessage("Set logger error " + e.getMessage(), true);
            return false;
        }
        return true;
    }
}
