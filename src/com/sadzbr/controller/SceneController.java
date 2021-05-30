package com.sadzbr.controller;

import javafx.fxml.FXMLLoader;

/**
 * Kontroler sceny.
 */
public class SceneController {
    /**
     * Kontroler serwera
     */
    private ServerController serverController;
    /**
     * Instancja klasy
     */
    private final static SceneController INSTANCE = new SceneController();

    private SceneController() {}

    /**
     * Pobiera instancje klasy
     * @return Zwraca instancje
     */
    public static SceneController getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Ustawia kontroler serwera
     * @param serverController Kontroler serwera
     */
    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    /**
     * Pobiera kontroler serwera
     * @return Zwraca kontroler serwera
     */
    public ServerController getServerController() {
        return serverController;
    }
}
