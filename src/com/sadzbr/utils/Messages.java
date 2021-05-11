package com.sadzbr.utils;

import com.sadzbr.controller.SceneController;
import com.sadzbr.controller.ServerController;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Messages {
    static public void logMessage(String message, boolean isError) {
        SceneController sceneController = SceneController.getINSTANCE();
        sceneController.getServerController().addMessage(message);
        Logger logger = Logger.getLogger("com.sadzbr");
        logger.info(sceneController.getServerController().toString());
        if(isError) {
            logger.warning(message);
        } else {
            logger.info(message);
        }
    }

    static public boolean setLogger() {
        Logger logger = Logger.getLogger("com.sadzbr");
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler("server.log");
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
