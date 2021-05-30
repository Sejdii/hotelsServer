package com.sadzbr.utils.model;

import com.sadzbr.model.Rooms;
import com.sadzbr.model.Table;
import com.sadzbr.service.Database;
import com.sadzbr.utils.Messages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Metody dla tabeli room
 */
public class RoomUtils {
    /**
     * Pobiera listę pokoi
     * @return Zwraca listę pokoi
     */
    static public List<Table> getRoomsList() {
        Database database = Database.getDatabase();
        ResultSet resultSet = database.executeSelectQuery("select * from rooms");
        if(resultSet == null) return null;

        List<Table> tableList = new ArrayList<>();
        try {
            while(resultSet.next()) {
                Rooms x = new Rooms();
                x.setId(resultSet.getInt("id"));
                x.setId_hotel(resultSet.getInt("id_hotel"));
                x.setNr_of_places(resultSet.getInt("nr_of_places"));
                x.setPrice(resultSet.getDouble("price"));
                x.setRoom_nr(resultSet.getInt("room_nr"));
                tableList.add(x);
            }
            return tableList;
        } catch (SQLException e) {
            e.printStackTrace();
            Messages.logMessage("Getting data from query error " + e.getMessage(), true);
            return null;
        }
    }

    /**
     * Wykonuje polecenie update na tabeli
     * @param r Pokój
     */
    static public void updateRoomsList(Rooms r) {
        Database database = Database.getDatabase();
        String query = String.format("update rooms set id_hotel = %d, nr_of_places = %d, price = %s, room_nr = %d where id = %d",
                r.getId_hotel(), r.getNr_of_places(), String.valueOf(r.getPrice()), r.getRoom_nr(), r.getId());
        database.executeQuery(query);
    }
}
