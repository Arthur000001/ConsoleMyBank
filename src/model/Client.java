package model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private String phoneNumber;
    private String inn;
    private String address;
    private List<Account> accounts = new ArrayList<>();

    public Client(String name, String phoneNumber, String inn, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.inn = inn;
        this.address = address;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getInn() {
        return inn;
    }

    public String getAddress() {
        return address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", inn='" + inn + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}