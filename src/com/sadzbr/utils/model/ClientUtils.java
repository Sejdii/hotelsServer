package com.sadzbr.utils.model;

import com.sadzbr.model.Client;
import com.sadzbr.model.Table;
import com.sadzbr.service.Database;
import com.sadzbr.utils.Messages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Metody dla tabeli client
 */
public class ClientUtils {
    /**
     * Zwraca listę klientów
     * @return Lista klientów
     */
    static public List<Table> getClientList() {
        Database database = Database.getDatabase();
        ResultSet resultSet = database.executeSelectQuery("select * from client");
        if(resultSet == null) return null;

        List<Table> tableList = new ArrayList<>();
        try {
            while(resultSet.next()) {
                Client x = new Client();
                x.setId(resultSet.getInt("id"));
                x.setName(resultSet.getString("name"));
                x.setSurname(resultSet.getString("surname"));
                x.setStreet(resultSet.getString("street"));
                x.setHome_nr(resultSet.getInt("home_nr"));
                x.setFlat_nr(resultSet.getInt("flat_nr"));
                x.setZip_code(resultSet.getString("zip_code"));
                x.setCity(resultSet.getString("city"));
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
     * Wykonuje polecenie insert do tabeli klientów
     * @param x Klient
     * @return Zwraca listę klientów
     */
    static public List<Table> insertClientList(Client x) {
        List<Table> tables = new ArrayList<>();

        Database database = Database.getDatabase();
        String query = String.format("insert into client values (NULL, '%s', '%s', '%s', %d, %d, '%s', '%s')",
                x.getName(), x.getSurname(), x.getStreet(), x.getHome_nr(), x.getFlat_nr(), x.getZip_code(), x.getCity());
        database.executeQuery(query);

        x = (Client) TableUtils.setInsertedId(x);
        tables.add(x);

        return tables;
    }
}
