package com.sadzbr.model;

public class Newsletter extends Table {
    private String email;
    private int id_client;

    {
        tableName = "newsletter";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public int insert() {
        return 0;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean select() {
        return false;
    }
}