package com.sadzbr;

import com.sadzbr.controller.DecisionArray;
import com.sadzbr.model.Message;
import com.sadzbr.model.Table;
import com.sadzbr.utils.Messages;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 * Handler dla połączenia z klientem
 */
public class ClientHandler extends Thread implements Runnable {
    /**
     * Socket klienta
     */
    private final Socket clientSocket;

    /**
     * Konstruktor
     * @param socket Socket klienta
     */
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    /**
     * Uruchamia wątek. Pobiera wiadomość od użytkownika, dokonuje decyzji co z nią zrobić a następnie odsyła odpowiedź.
     */
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
