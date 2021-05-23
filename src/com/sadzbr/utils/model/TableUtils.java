package com.sadzbr.utils.model;

import com.sadzbr.model.Table;
import com.sadzbr.service.Database;
import com.sadzbr.utils.Messages;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableUtils {
    static public Table setInsertedId(Table t) {
        Database database = Database.getDatabase();
        ResultSet resultSet = database.executeSelectQuery("select last_insert_rowid() as id");

        if(resultSet == null) return null;
        try {
            t.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
            Messages.logMessage("Error to getting inserted row id", true);
            return null;
        }
        return t;
    }
}
