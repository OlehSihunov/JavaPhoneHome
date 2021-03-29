package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MySqlDataService implements DataSerivce{
    @Override
    public ArrayList<Contact> getContacts() {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phonebook?serverTimezone=UTC", "root", "1111")) {
            Statement statement = connection.createStatement();
            // создание таблицы
            ResultSet rs = statement.executeQuery("SELECT * FROM CONTACTS;");
            ArrayList<Contact> contacts  = new ArrayList<Contact>();
            while (rs.next()){
                contacts.add(new Contact(rs.getString(2),rs.getString(3),rs.getInt(1)));
            }
            return contacts;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void SaveContacts(ArrayList<Contact> newContacts) {

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phonebook?serverTimezone=UTC", "root", "1111")) {
            Statement statement = connection.createStatement();
            // создание таблицы
            statement.execute("TRUNCATE CONTACTS");
            for (Contact e : newContacts) {
                statement.executeUpdate(String.format("INSERT  CONTACTS VALUES (%d,\"%s\",\"%s\");",e.getUserId(),e.getName(),e.getNumber()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> GetUsers() {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phonebook?serverTimezone=UTC", "root", "1111")) {
            Statement statement = connection.createStatement();
            // создание таблицы
            ResultSet rs = statement.executeQuery("SELECT * FROM USERS");
            ArrayList<User> users  = new ArrayList<User>();
            while (rs.next()){
                users.add(new User(rs.getString(2),rs.getString(3),rs.getInt(1)));
            }
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override

    public void SaveUsers(ArrayList<User> newUsers) {
        System.out.println("saveUSers");
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phonebook?serverTimezone=UTC", "root", "1111")) {
            Statement statement = connection.createStatement();
            // создание таблицы
            statement.execute("TRUNCATE USERS");
            for (User e : newUsers) {
                statement.executeUpdate(String.format("INSERT  USERS(UserID,Login,Password) VALUES (%d,\"%s\",\"%s\");",e.getId(),e.getName(),e.getPassword()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User findUser(String login, String password) {
        ArrayList<User> users = new ArrayList<>(this.GetUsers().stream().filter(user ->user.getName().equals(login) && user.getPassword().equals(password)).collect(Collectors.toList()));
        if(users.size()==0)
            return null;
        else return users.get(0);
    }
}
