package com.sadzbr.utils.model;

import com.sadzbr.model.Hotel;
import com.sadzbr.model.Table;
import com.sadzbr.service.Database;
import com.sadzbr.utils.Messages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Metody dla tabeli hotel
 */
public class HotelUtils {
    /**
     * Pobiera listę hotel
     * @return Zwraca listę hoteli
     */
    static public List<Table> getHotelList() {
        Database database = Database.getDatabase();
        ResultSet resultSet = database.executeSelectQuery("select * from hotel");
        if(resultSet == null) return null;

        List<Table> tableList = new ArrayList<>();
        try {
            while(resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(resultSet.getInt("id"));
                hotel.setAddress(resultSet.getString("address"));
                hotel.setName(resultSet.getString("name"));
                tableList.add(hotel);
            }
            return tableList;
        } catch (SQLException e) {
            e.printStackTrace();
            Messages.logMessage("Getting data from query error " + e.getMessage(), true);
            return null;
        }
    }
}
