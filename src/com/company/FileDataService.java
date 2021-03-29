package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.stream.Collectors;

public  class FileDataService implements  DataSerivce{


    @Override
    public ArrayList<Contact> getContacts() {
        ArrayList<Contact> userContacts = new ArrayList<Contact>();
        try {
            FileInputStream fis = new FileInputStream("contacts.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            userContacts = (ArrayList<Contact>) ois.readObject();
            ois.close();
        }catch (Exception e) {
            return  userContacts;
        }
        return userContacts;
    }

    @Override
    public void SaveContacts(ArrayList<Contact> newContacts) {
        try {
            FileOutputStream fos = new FileOutputStream("contacts.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(newContacts);
            oos.close();
        }catch (Exception e) {
        }
    }

    @Override
    public ArrayList<User> GetUsers() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            FileInputStream fis = new FileInputStream("users.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (ArrayList<User>) ois.readObject();
            ois.close();
        }catch (Exception e) {
            return users;
        }
        return users;
    }

    @Override
    public void SaveUsers(ArrayList<User> newUsers) {
        try {
            FileOutputStream fos = new FileOutputStream("users.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(newUsers);
            oos.close();
        }catch (Exception e) {
        }

    }
    public User findUser(String login, String password) {
        ArrayList<User> users = new ArrayList<>(this.GetUsers().stream().filter(user ->user.getName().equals(login) && user.getPassword().equals(password)).collect(Collectors.toList()));
        if(users.size()==0)
        return null;
        else return users.get(0);
    }
}
