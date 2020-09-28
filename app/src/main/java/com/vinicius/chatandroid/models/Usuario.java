package com.vinicius.chatandroid.models;

public class Usuario {
    private static final Usuario instance = new Usuario();

    private String userId;

    public static Usuario getInstance() {
        return instance;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }
}
