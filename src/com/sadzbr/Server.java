package com.sadzbr;

import com.sadzbr.controller.SceneController;
import com.sadzbr.controller.ServerController;
import com.sadzbr.service.Database;
import com.sadzbr.utils.Messages;
import com.sadzbr.utils.Resource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Klasa main serwera.
 */
public class Server extends Application {
    /**
     * Uruchamia aplikacje serwera wraz z interfejsem graficznym
     * @param stage Scena
     * @throws Exception Wyjątek
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Resource.view("server")));
        Parent root = loader.load();

        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Sieć hoteli - projekt JAVA | SERVER");
        stage.setMinHeight(600);
        stage.setMinWidth(900);
        stage.setScene(scene);
        stage.show();

        Messages.setLogger();

        ServerController serverController = loader.getController();
        SceneController sceneController = SceneController.getINSTANCE();
        sceneController.setServerController(serverController);

        Database database = Database.getDatabase();

        // when exit a program -> shutdown a server
        stage.setOnCloseRequest(e -> {
            try {
                serverController.serverShutdown();
                database.exit();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                Messages.logMessage("Server shutdown error", true);
            }
        });
    }

    /**
     * Metoda main
     * @param args Argumenty wywołania
     */
    public static void main(String[] args) {
        launch(args);
    }
}
