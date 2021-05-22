package com.sadzbr.controller;

import com.sadzbr.model.Message;
import com.sadzbr.model.Table;
import com.sadzbr.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Tablica decyzyjna serwera. Zarządza jaką decyzje podjąć na podstawie odebranej wiadomości
 */
public class DecisionArray {
    private final Message message;

    public DecisionArray(Message message) {
        this.message = message;
    }

    public List<Table> makeDecision() {
        Table table = message.getTable();
        List<Table> returnTables = new ArrayList<>();
        switch (message.getOperationType()) {
            case "select":
                table.select();
                returnTables.add(table);
                return returnTables;
            case "login":
                if(((User) table).login()) {
                    returnTables.add(table);
                }
                return returnTables;
            case "selectAll":
                returnTables = table.selectAll();
                return returnTables;
            case "update":
                if(table.update()) {
                    returnTables.add(table);
                }
                return returnTables;
            case "insert":
                if(table.insert() != -1) {
                    returnTables.add(table);
                }
                return returnTables;
            case "delete":
                if(table.delete()) {
                    returnTables.add(table);
                }
                return returnTables;
            case "query":
                //TODO stworzyć działanie dla operacji QUERY
            default:
                break;
        }
        return null;
    }
}
