package com.sadzbr;

import com.sadzbr.controller.SceneController;
import com.sadzbr.controller.ServerController;
import com.sadzbr.service.ServerService;
import com.sadzbr.utils.Messages;
import com.sadzbr.utils.Resource;
import com.sadzbr.utils.Settings;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Logger;

public class Server extends Application {
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

        ServerController serverController = loader.getController();
        SceneController sceneController = SceneController.getINSTANCE();
        sceneController.setServerController(serverController);

        stage.setOnCloseRequest(e -> {
            try {
                serverController.serverShutdown();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                Messages.logMessage("Server shutdown error", true);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
