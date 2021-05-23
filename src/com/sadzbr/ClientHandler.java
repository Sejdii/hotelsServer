package com.sadzbr;

import com.sadzbr.controller.DecisionArray;
import com.sadzbr.model.Message;
import com.sadzbr.model.Table;
import com.sadzbr.utils.Messages;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Set;

public class ClientHandler extends Thread implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            // get message from client
            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Message message = (Message) objectInputStream.readObject();
            String messageLog = "Received message from " + clientSocket + " operation type: " + message.getOperationType();
            Messages.logMessage(messageLog, false);

            // decide what to do next with data
            List<Table> response = DecisionArray.makeDecision(message);

            // send response to client
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutputStream.writeObject(response);
            messageLog = "Response send to client " + clientSocket + " with " + response.size() + " rows";
            Messages.logMessage(messageLog, false);

            objectInputStream.close();
            objectOutputStream.close();
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
