package com.sadzbr.controller;

import com.sadzbr.model.Message;
import com.sadzbr.model.Table;
import com.sadzbr.model.User;
import com.sadzbr.service.Database;
import com.sadzbr.utils.HotelUtils;
import com.sadzbr.utils.Messages;
import com.sadzbr.utils.UserUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        switch (message.getOperationType()) {
            case "getUserList":
                return UserUtils.getUserList();
            case "updateUserList":
                User user = (User) table;
                UserUtils.updateUserList(user);
                return null;
            case "deleteUserList":
                user = (User) table;
                UserUtils.deleteUserList(user);
                return null;
            case "insertUserList":
                user = (User) table;
                return UserUtils.insertUserList(user);
            case "getHotelList":
                return HotelUtils.getHotelList();
            default:
                break;
        }
        return null;
    }
}
