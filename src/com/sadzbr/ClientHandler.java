package com.sadzbr;

import com.sadzbr.model.Message;
import com.sadzbr.utils.Messages;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        Logger logger = Logger.getLogger("com.sadzbr");
        try {
            // output from the server
            OutputStream outputStream = clientSocket.getOutputStream();
            // input to server from client
            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Message message = (Message) objectInputStream.readObject();
            String messageLog = "Received " + message.getTable().getTableName() + " table from " + clientSocket + " operation type: " + message.getOperationType();
            Messages.logMessage(messageLog, false);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            String message = "Error on read the socket send from " + clientSocket + " " + e.getMessage();
            Messages.logMessage(message, true);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
