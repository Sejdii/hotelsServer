package com.sadzbr.utils;

import com.sadzbr.model.Table;
import com.sadzbr.model.User;
import com.sadzbr.service.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserUtils {
    static public List<Table> getUserList() {
        Database database = Database.getDatabase();
        ResultSet resultSet = database.executeSelectQuery("select * from users");
        if(resultSet == null) return null;

        List<Table> tableList = new ArrayList<>();
        try {
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setId_hotel(resultSet.getInt("id_hotel"));
                user.setPassword(resultSet.getString("password"));
                user.setLogin(resultSet.getString("login"));
                user.setUser_type(resultSet.getString("user_type"));
                tableList.add(user);
            }
            return tableList;
        } catch (SQLException e) {
            e.printStackTrace();
            Messages.logMessage("Getting data from query error " + e.getMessage(), true);
            return null;
        }
    }

    static public void updateUserList(User u) {
        Database database = Database.getDatabase();
        String query = String.format("update users set id_hotel = %d, login = '%s', password = '%s', user_type = '%s' where id = %d",
                u.getId_hotel(), u.getLogin(), u.getPassword(), u.getUser_type(), u.getId());
        database.executeQuery(query);
    }

    static public void deleteUserList(User u) {
        Database database = Database.getDatabase();
        String query = String.format("delete from users where id = %d", u.getId());
        database.executeQuery(query);
    }

    static public List<Table> insertUserList(User u) {
        List<Table> tables = new ArrayList<>();

        Database database = Database.getDatabase();
        String query = String.format("insert into users values (NULL, %d, '%s', '%s', '%s')",
                                    u.getId_hotel(), u.getLogin(), u.getPassword(), u.getUser_type());
        database.executeQuery(query);

        ResultSet resultSet = database.executeSelectQuery("select last_insert_rowid() as id");

        if(resultSet == null) return null;
        try {
            u.setId(resultSet.getInt("id"));
            tables.add(u);
        } catch (SQLException e) {
            e.printStackTrace();
            Messages.logMessage("Error to getting inserted row id", true);
            return null;
        }


        return tables;
    }
}
