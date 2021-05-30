package com.sadzbr.service;

import com.sadzbr.ClientHandler;
import com.sadzbr.controller.SceneController;
import com.sadzbr.utils.Messages;
import com.sadzbr.utils.Settings;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Service serwera będący singletonem
 */
public class ServerService {
    /**
     * Socket serwera
     */
    private ServerSocket server;
    /**
     * Pole określa czy serwer jest uruchomiony
     */
    private boolean working = false;
    /**
     * Instancja klasy
     */
    private static final ServerService INSTANCE = new ServerService();

    private ServerService() {}

    /**
     * Pobiera instancję serwera
     * @return Zwraca instancję serwera
     */
    public static ServerService getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Uruchamia serwera. Tworzy nowy wątek który będzie odpowiadał za nasłuchiwanie żądania od klienta.
     * Jeżeli otrzyma on żądanie stworzy on nowy wątek w postaci klasy com.sadzbr.ClientHandler
     */
    public void run() {
        SceneController sceneController = SceneController.getINSTANCE();
        new Thread(() -> {
            Logger logger = Logger.getLogger("com.sadzbr");
            logger.info(sceneController.getServerController().toString() + " loaded");
            try {
                server = new ServerSocket(Settings.PORT);
                working = true;
                server.setReuseAddress(true);
                Messages.logMessage("Server starts working", false);
                InetAddress inetAddress = InetAddress.getLocalHost();
                Messages.logMessage("Hostname: " + inetAddress.getHostName(), false);
                Messages.logMessage("IP: " + inetAddress, false);
                Messages.logMessage("Local port: " + server.getLocalPort(), false);
                server.setSoTimeout(500);
                while (working) {
                    try {
                        Socket client = server.accept();
                        Messages.logMessage("New client connected, address:" + client.getInetAddress().getHostAddress(), false);
                        ClientHandler clientHandler = new ClientHandler(client);
                        new Thread(clientHandler).start();
                    } catch (IOException ignored) {

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                Messages.logMessage("Server error: " + e.getMessage(), true);
                working = false;
            } finally {
                if(server != null) {
                    try {
                        server.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * Zatrzymuje działanie serwera
     * @throws IOException Rzuca wyjątek IOException
     */
    public void stop() throws IOException {
        Messages.logMessage("Server stop working", false);
        working = false;
    }
}
