package com.sadzbr.utils.model;

import com.sadzbr.model.Reservations;
import com.sadzbr.model.Rooms;
import com.sadzbr.model.Table;
import com.sadzbr.service.Database;
import com.sadzbr.utils.Messages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Metody dla tabeli reservations
 */
public class ReservationsUtils {
    /**
     * Pobiera listę rezerwacji
     * @return Zwraca listę rezerwacji
     */
    static public List<Table> getReservationsList() {
        Database database = Database.getDatabase();
        ResultSet resultSet = database.executeSelectQuery("select * from reservations");
        if(resultSet == null) return null;

        List<Table> tableList = new ArrayList<>();
        try {
            while(resultSet.next()) {
                Reservations x = new Reservations();
                x.setId(resultSet.getInt("id"));
                x.setId_room(resultSet.getInt("id_room"));
                x.setId_package(resultSet.getInt("id_package"));
                x.setId_payment(resultSet.getInt("id_payment"));
                x.setId_client(resultSet.getInt("id_client"));
                x.setDate_start(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("date_start")));
                x.setDate_end(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("date_end")));
                tableList.add(x);
            }
            return tableList;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            Messages.logMessage("Getting data from query error " + e.getMessage(), true);
            return null;
        }
    }

    /**
     * Wykonuje polecenie insert na tabeli
     * @param x Rezerwacja
     * @return Zwraca listę rezerwacji z jednym elementem którzy przechowuje w swoim polu ID id nowo utworzonego wiersza.
     */
    static public List<Table> insertReservationList(Reservations x) {
        List<Table> tables = new ArrayList<>();

        Database database = Database.getDatabase();
        String dateStart = new SimpleDateFormat("yyyy-MM-dd").format(x.getDate_start());
        String dateEnd = new SimpleDateFormat("yyyy-MM-dd").format(x.getDate_end());
        String query = String.format("insert into reservations values (NULL, %d, %d, %d, %d, '%s', '%s')",
                x.getId_room(), x.getId_package(), x.getId_payment(), x.getId_client(), dateStart, dateEnd);
        database.executeQuery(query);

        x = (Reservations) TableUtils.setInsertedId(x);
        tables.add(x);

        return tables;
    }

    /**
     * Pobiera listę dostępnym pokoi
     * @param x Rezerwacja
     * @return Zwraca listę dostępnych pokoi
     */
    static public List<Table> getAvailableRoomsList(Reservations x) {
        Database database = Database.getDatabase();
        String dateStart = new SimpleDateFormat("yyyy-MM-dd").format(x.getDate_start());
        String dateEnd = new SimpleDateFormat("yyyy-MM-dd").format(x.getDate_end());
        String query = String.format("select * from rooms " +
                "where id NOT IN (select id_room from reservations where '%s' between date_start and date_end or date_start between '%s' and '%s') " +
                "and id_hotel=%d and nr_of_places=%d;",
                dateStart, dateStart, dateEnd,
                x.getId_room(), x.getId_package());

        // for socket message: id_rooms handle value of id_hotel and id_package handle value of nr_of_places
        ResultSet resultSet = database.executeSelectQuery(query);
        if(resultSet == null) return null;

        List<Table> tableList = new ArrayList<>();
        try {
            while(resultSet.next()) {
                Rooms y = new Rooms();
                y.setId(resultSet.getInt("id"));
                y.setId_hotel(resultSet.getInt("id_hotel"));
                y.setNr_of_places(resultSet.getInt("nr_of_places"));
                y.setPrice(resultSet.getDouble("price"));
                y.setRoom_nr(resultSet.getInt("room_nr"));
                tableList.add(y);
            }
            return tableList;
        } catch (SQLException e) {
            e.printStackTrace();
            Messages.logMessage("Getting data from query error " + e.getMessage(), true);
            return null;
        }
    }
}
