package com.sadzbr.controller;

import javafx.fxml.FXMLLoader;

public class SceneController {
    private ServerController serverController;
    private final static SceneController INSTANCE = new SceneController();

    private SceneController() {}

    public static SceneController getINSTANCE() {
        return INSTANCE;
    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    public ServerController getServerController() {
        return serverController;
    }
}
