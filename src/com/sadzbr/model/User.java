package com.sadzbr.model;

import com.sadzbr.service.Database;
import com.sadzbr.utils.Messages;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class User extends Table {
    private int id_hotel;
    private String login;
    private String password;
    private String user_type;

    {
        tableName = "user";
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }


    @Override
    public int insert() {
        return -1;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    public void set(User user) {
        id = user.getId();
        id_hotel = user.getId_hotel();
        login = user.getLogin();
        password = user.getPassword();
        user_type = user.getUser_type();
    }

    @Override
    public boolean select() {
        Database database = Database.getDatabase();
        ResultSet resultSet = database.executeSelectQuery("SELECT * FROM users WHERE id = " + id);
        if(resultSet == null) return false;
        try {
            id_hotel = resultSet.getInt("id_hotel");
            login = resultSet.getString("login");
            password = resultSet.getString("password");
            user_type = resultSet.getString("user_type");
            Messages.logMessage(login + " " + password, false);
        } catch (SQLException e) {
            e.printStackTrace();
            Messages.logMessage("Getting data from query error " + e.getMessage(), true);
            return false;
        }
        return true;
    }

    @Override
    public List<Table> selectAll() {
        return null;
    }

    public boolean login() {
		Database database = Database.getDatabase();
		String query = String.format("select id from users where login='%s' and password='%s'", login, password);
		ResultSet resultSet = database.executeSelectQuery(query);
		if(resultSet == null) return false;
		try {
		    id = resultSet.getInt("id");
		    select();
        } catch (SQLException e) {
            e.printStackTrace();
            Messages.logMessage("Getting data from query error " + e.getMessage(), true);
            return false;
        }
		return true;
    }
}
