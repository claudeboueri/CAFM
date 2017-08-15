package com.example.owner.cafm.Models;


public class TokenRequest {

    private long number;
    private String username;
    private String password;
    private int type;


    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

}
