package com.sadzbr.utils;

import com.sadzbr.controller.SceneController;
import com.sadzbr.controller.ServerController;

import java.util.logging.Logger;

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
}
