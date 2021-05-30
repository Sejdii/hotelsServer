package com.sadzbr.utils;

/**
 * Ustawienia serwera
 */
public class Settings {
    /* SERVER */
    /**
     * PORT serwera
     */
    public static final int PORT = 4665;

    /* DATABASE */
    /**
     * Sterownik bazy danych
     */
    public static final String DB_DRIVER = "org.sqlite.JDBC";
    /**
     * URL bazy danych
     */
    public static final String DB_URL = "jdbc:sqlite:hotels.sqlite";
}
