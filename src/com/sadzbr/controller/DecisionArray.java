package com.sadzbr.controller;

import com.sadzbr.model.Message;
import com.sadzbr.model.Rooms;
import com.sadzbr.model.Table;
import com.sadzbr.model.User;
import com.sadzbr.utils.model.HotelUtils;
import com.sadzbr.utils.model.PackageUtils;
import com.sadzbr.utils.model.RoomUtils;
import com.sadzbr.utils.model.UserUtils;
import com.sadzbr.model.Package;

import java.util.List;

/**
 * Tablica decyzyjna serwera. Zarządza jaką decyzje podjąć na podstawie odebranej wiadomości
 */
public class DecisionArray {

    static public List<Table> makeDecision(Message message) {
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
            case "getRoomsList":
                return RoomUtils.getRoomsList();
            case "updateRoomsList":
                Rooms rooms = (Rooms) table;
                RoomUtils.updateRoomsList(rooms);
                return null;
            case "getPackageList":
                return PackageUtils.getPackageList();
            case "updatePackageList":
                Package p = (Package) table;
                PackageUtils.updatePackageList(p);
                return null;
            case "deletePackageList":
                p = (Package) table;
                PackageUtils.deletePackageList(p);
                return null;
            case "insertPackageList":
                p = (Package) table;
                return PackageUtils.insertPackageList(p);
            default:
                break;
        }
        return null;
    }
}
