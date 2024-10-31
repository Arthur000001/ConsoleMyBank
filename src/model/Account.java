package model;

public class Account {
    private String accountNumber;
    private double balance;
    private boolean isOpen;
    private String bic;
    private String currency;

    public Account(String accountNumber, String bic, String currency) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.isOpen = true;
        this.bic = bic;
        this.currency = currency;
    }

    // Геттеры и сеттеры
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public String getCurrency() {
        return currency;
    }

    public void addAmount(double amount) {
        balance += amount;
    }

    public void reduceAmount(double amount) {
        balance -= amount;
    }

    public void isOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", isOpen=" + isOpen +
                ", bic='" + bic + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}