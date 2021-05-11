package com.sadzbr.service;

import com.sadzbr.utils.Messages;
import com.sadzbr.utils.Settings;

import java.sql.*;

public class Database {
    private static final Database database = new Database();
    private Connection connection;
    private Statement statement;

    private Database() {
        try {
            Class.forName(Settings.DB_DRIVER);
            connection = DriverManager.getConnection(Settings.DB_URL);
            statement = connection.createStatement();
            Messages.logMessage("Connected to database " + Settings.DB_URL, false);
            initialDatabase();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Messages.logMessage("Error of database driver: " + e.getMessage(), true);
        } catch (SQLException e) {
            e.printStackTrace();
            Messages.logMessage("Connect with database error" + e.getMessage(), true);
        }
    }

    public static Database getDatabase() {
        return database;
    }

    public boolean executeQuery(String query) {
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            Messages.logMessage("Query error: " + e.getMessage(), true);
            return false;
        }
        return true;
    }

    public ResultSet executeSelectQuery(String query) {
        ResultSet ret;
        try {
            ret = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            Messages.logMessage("Query error: " + e.getMessage(), true);
            return null;
        }
        return ret;
    }

    public void exit() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean initialDatabase() {
        String users = "CREATE TABLE IF NOT EXISTS users (" +
                "id integer primary key autoincrement," +
                "id_hotel integer," +
                "login varchar(50) not null," +
                "password varchar(255) not null," +
                "user_type varchar(20) not null)";
        String hotel = "CREATE TABLE IF NOT EXISTS hotel (" +
                "id integer primary key autoincrement," +
                "address varchar(255) not null," +
                "name varchar(100) not null)";
        String rooms = "CREATE TABLE IF NOT EXISTS rooms (" +
                "id integer primary key autoincrement," +
                "id_hotel integer not null," +
                "nr_of_places integer not null," +
                "price float not null," +
                "room_nr integer not null)";
        String packageT = "CREATE TABLE IF NOT EXISTS package (" +
                "id integer primary key autoincrement," +
                "id_hotel integer not null," +
                "price float not null," +
                "name varchar(100) not null," +
                "description varchar(255) not null)";
        String payments = "CREATE TABLE IF NOT EXISTS payments (" +
                "id integer primary key autoincrement," +
                "form varchar(100) not null," +
                "amount float not null)";
        String reservations = "CREATE TABLE IF NOT EXISTS reservations (" +
                "id integer primary key autoincrement," +
                "id_room integer not null," +
                "id_package integer not null," +
                "id_payment integer not null," +
                "id_client integer not null)";
        String client = "CREATE TABLE IF NOT EXISTS client (" +
                "id integer primary key autoincrement," +
                "name varchar(100) not null," +
                "surname varchar(100) not null," +
                "street varchar(15) not null," +
                "home_nr integer not null," +
                "flat_nr integer null," +
                "zip_code varchar(7) not null," +
                "city varchar(100) not null)";
        String newsletter = "CREATE TABLE IF NOT EXISTS newsletter (" +
                "id integer primary key autoincrement," +
                "email varchar(255) not null," +
                "id_client integer not null)";

        try {
            statement.execute(users);
            statement.execute(hotel);
            statement.execute(rooms);
            statement.execute(packageT);
            statement.execute(payments);
            statement.execute(reservations);
            statement.execute(client);
            statement.execute(newsletter);
        } catch (SQLException e) {
            e.printStackTrace();
            Messages.logMessage("Initial database error: " + e.getMessage(), true);
            return false;
        }
        return true;
    }
}
