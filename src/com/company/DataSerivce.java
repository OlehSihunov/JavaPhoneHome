package com.company;

import java.util.ArrayList;

public interface DataSerivce {
    ArrayList<Contact> getContacts ();
    void SaveContacts (ArrayList<Contact> newContacts);
    ArrayList<User> GetUsers();
    void SaveUsers(ArrayList<User> newUsers);
    User findUser(String login, String password);
}
