package com.company;

import java.io.Serializable;

public class Contact implements Serializable {
    private  String name;
    private  String number;
    private  int userId;
    public Contact(String name, String number, int userId){
        this.name = name;
        this.number = number;
        this.userId = userId;
    }
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Contact name: " + name  +", number='" + number;
    }
}
