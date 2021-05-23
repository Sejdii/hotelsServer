package com.sadzbr.utils.model;

import com.sadzbr.model.Rooms;
import com.sadzbr.model.Table;
import com.sadzbr.service.Database;
import com.sadzbr.utils.Messages;
import com.sadzbr.model.Package;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageUtils {
    static public List<Table> getPackageList() {
        Database database = Database.getDatabase();
        ResultSet resultSet = database.executeSelectQuery("select * from package");
        if(resultSet == null) return null;

        List<Table> tableList = new ArrayList<>();
        try {
            while(resultSet.next()) {
                Package x = new Package();
                x.setId(resultSet.getInt("id"));
                x.setId_hotel(resultSet.getInt("id_hotel"));
                x.setPrice(resultSet.getDouble("price"));
                x.setName(resultSet.getString("name"));
                x.setDescription(resultSet.getString("description"));
                tableList.add(x);
            }
            return tableList;
        } catch (SQLException e) {
            e.printStackTrace();
            Messages.logMessage("Getting data from query error " + e.getMessage(), true);
            return null;
        }
    }

    static public void updatePackageList(Package p) {
        Database database = Database.getDatabase();
        String query = String.format("update package set id_hotel = %d, price = %s, name = '%s', description = '%s' where id = %d",
                p.getId_hotel(), String.valueOf(p.getPrice()), p.getName(), p.getDescription(), p.getId());
        database.executeQuery(query);
    }

    static public void deletePackageList(Package p) {
        Database database = Database.getDatabase();
        String query = String.format("delete from package where id = %d", p.getId());
        database.executeQuery(query);
    }

    static public List<Table> insertPackageList(Package p) {
        List<Table> tables = new ArrayList<>();

        Database database = Database.getDatabase();
        String query = String.format("insert into package values (NULL, %d, %s, '%s', '%s')",
                p.getId_hotel(), String.valueOf(p.getPrice()), p.getName(), p.getDescription());
        database.executeQuery(query);

        p = (Package) TableUtils.setInsertedId(p);
        tables.add(p);

        return tables;
    }
}
