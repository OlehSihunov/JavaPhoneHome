package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserInterface {
    public static User currentuser;
    //public static  DataSerivce ds = new MySqlDataService();//for mysql
    public static  DataSerivce ds = new FileDataService();
    public static  Scanner scanner = new Scanner(System. in);
    public void Start() {
        while (true) {
            if(currentuser == null){
                this.PrintLoginMenu();
            }else {
                this.PrintMainMenu();

            }
        }
    }
    public void PrintLoginMenu() {
        System.out.println("1 : register");
        System.out.println("2 : login");
        String key = scanner. nextLine();
        switch (key) {
            case "1": this.Register(); break;
            case "2": this.Login(); break;
            default:
                System.out.println("You entered wrong number");
        }
    }
    public void PrintMainMenu() {
        System.out.println("********************************************************");
        System.out.println("1 : show all contacts");
        System.out.println("2 : add a new contact");
        System.out.println("3 : delete all user contacts");
        System.out.println("4 : log out");
        System.out.println("0 : stop program");
        String key = scanner. nextLine();
        switch (key) {
            case "1": this.ShowContacts(); break;
            case "2": this.AddNewContact(); break;
            case "3": this.DeleteUserContacts(); break;
            case "4": this.LogOut(); break;
            case "0": System.exit(0);
            default:
                System.out.println("You entered wrong number");
        }
    }

    private void ShowContacts() {
        ArrayList<Contact> contacts = new ArrayList<>(ds.getContacts().stream().filter(contact -> contact.getUserId()==currentuser.getId()).collect(Collectors.toList()));
        contacts.forEach(contact -> System.out.println(contact));
        if(contacts.size()==0){
            System.out.println("No contacts");
        }

    }

    private void DeleteUserContacts() {
        ArrayList<Contact> contacts = new ArrayList<>(ds.getContacts().stream().filter(contact -> contact.getUserId()!=currentuser.getId()).collect(Collectors.toList()));
        ds.SaveContacts(contacts);
    }

    private void AddNewContact() {
        ArrayList<Contact> contacts = ds.getContacts();
        System.out.print("Enter name: ");
        String name = scanner. nextLine();
        System.out.print("Enter number: ");
        String number = scanner. nextLine();
        contacts.add(new Contact(name,number,currentuser.getId()));
        ds.SaveContacts(contacts);
    }

    public void Login() {
        System.out.print("Enter login: ");
        String login = scanner. nextLine();
        System.out.print("Enter password: ");
        String password =scanner. nextLine();
        User tUser = ds.findUser(login,password);
        if(tUser != null){
            currentuser = tUser;
        }else  {
            System.out.println("No user with such logiiin and password");
        }

    }
    public void Register() {
        System.out.print("Enter login: ");
        String login = scanner. nextLine();
        System.out.print("Enter password: ");
        String password = scanner. nextLine();
        User tUser = ds.findUser(login,password);
        if(tUser == null){
            ArrayList <User> newUsers = ds.GetUsers();
            tUser = new User(login,password,newUsers.size()+1);
            currentuser = tUser;
            newUsers.add(tUser);
            ds.SaveUsers(newUsers);
        }else  {
            System.out.println("Login is'nt free");
        }

    }
    public  void LogOut(){
        currentuser = null;
    }
}
