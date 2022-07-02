package com.lotte_app;

public class User {
    private int idUser, isCollector;
    private String name;

    public User(int idUser, int isCollector, String name) {
        this.idUser = idUser;
        this.isCollector = isCollector;
        this.name = name;
    }

    public User(int idUser){
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIsCollector() {
        return isCollector;
    }

    public void setIsCollector(int isCollector) {
        this.isCollector = isCollector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
