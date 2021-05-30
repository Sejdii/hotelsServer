package com.sadzbr.controller;

import com.sadzbr.model.*;
import com.sadzbr.model.Package;
import com.sadzbr.utils.model.*;

import java.util.List;

/**
 * Tablica decyzyjna serwera. Zarządza jaką decyzje podjąć na podstawie odebranej wiadomości
 */
public class DecisionArray {

    /**
     * Metoda, która podejmuje co zrobić dalej, na podstawie wiadomości otrzymanej od klienta.
     * @param message Wiadomość
     * @return Zwraca listę tabel.
     */
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
            case "getReservationsList":
                return ReservationsUtils.getReservationsList();
            case "insertReservationsList":
                Reservations r = (Reservations) table;
                return ReservationsUtils.insertReservationList(r);
            case "getClientList":
                return ClientUtils.getClientList();
            case "insertClientList":
                Client c = (Client) table;
                return ClientUtils.insertClientList(c);
            case "insertPaymentsList":
                Payments payments = (Payments) table;
                return PaymentsUtils.insertPaymentsList(payments);
            case "insertNewsletterList":
                Newsletter newsletter = (Newsletter) table;
                return NewsletterUtils.insertNewsletterList(newsletter);
            case "getAvailableRoomsList":
                r = (Reservations) table;
                return ReservationsUtils.getAvailableRoomsList(r);
            default:
                break;
        }
        return null;
    }
}
