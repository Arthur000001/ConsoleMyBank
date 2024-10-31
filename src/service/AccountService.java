package service;

import model.Account;
import model.Client;

import java.util.*;
import java.util.stream.Collectors;

import static repository.RepositoryAccount.addAccountNumber;
import static repository.RepositoryAccount.checkPhoneNumber;
import static repository.RepositoryClients.getClient;
import static repository.RepositoryClients.getClients;
import static service.ClientService.selectClientWithOpenAccounts;

public class AccountService {

    private static Scanner scanner = new Scanner(System.in);

    public static void createAccount() {
        if (getClients().isEmpty()) {
            System.out.println("Клиентов не найдено.");
            return;
        }

        System.out.println("Выберите клиента:");
        for (int i = 0; i < getClients().size(); i++) {
            System.out.println((i + 1) + ". " + getClients().get(i).getName());
        }

        int clientIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Очистка после ввода числа

        if (clientIndex < 0 || clientIndex >= getClients().size()) {
            System.out.println("Неверный выбор клиента.");
            return;
        }

        System.out.println("Введите номер счета:");
        String accountNumber = scanner.nextLine();
        if (checkPhoneNumber(accountNumber)) {
            System.out.println("Счет с таким номером уже существует.");
            return;
        }

        System.out.println("Введите БИК:");
        String bic = scanner.nextLine();
        System.out.println("Выберите валюту (рубли, евро, доллары):");
        String currency = scanner.nextLine();

        Account newAccount = new Account(accountNumber, bic, currency);
        getClient(clientIndex).addAccount(newAccount);
        addAccountNumber(accountNumber);
        System.out.println("Счет успешно создан.");
    }

    public static void closeAccount() {
        Client client = selectClientWithOpenAccounts();
        if (client == null) return;

        System.out.println("Выберите счет для закрытия:");
        List<Account> openAccounts = client.getAccounts().stream().filter(Account::isOpen).collect(Collectors.toList());
        for (int i = 0; i < openAccounts.size(); i++) {
            System.out.println((i + 1) + ". " + openAccounts.get(i).getAccountNumber());
        }

        int accountIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (accountIndex < 0 || accountIndex >= openAccounts.size()) {
            System.out.println("Неверный выбор счета.");
            return;
        }

        closeAccount(openAccounts.get(accountIndex));
        System.out.println("Счет успешно закрыт.");
    }

    public static void transferFunds() {
        System.out.println("Выберите счет отправителя:");
        Account fromAccount = selectAccount();
        if (fromAccount == null || !fromAccount.isOpen()) return;

        System.out.println("Введите сумму перевода:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Выберите счет получателя:");
        Account toAccount = selectAccount();
        if (toAccount == null || !toAccount.isOpen()) return;

        if (fromAccount.getBalance() >= amount) {
            withdraw(fromAccount, amount);
            deposit(toAccount, amount);
            System.out.println("Перевод успешно выполнен.");
        } else {
            System.out.println("Недостаточно средств для перевода.");
        }
    }

    public static void depositFunds() {
        System.out.println("Выберите счет для зачисления:");
        Account account = selectAccount();
        if (account == null || !account.isOpen()) return;

        System.out.println("Введите сумму:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        deposit(account, amount);
        System.out.println("Средства успешно зачислены.");
    }

    public static Account selectAccount() {
        List<Account> allAccounts = getClients().stream().flatMap(client -> client.getAccounts().stream()).collect(Collectors.toList());
        if (allAccounts.isEmpty()) {
            System.out.println("Счетов не найдено.");
            return null;
        }

        for (int i = 0; i < allAccounts.size(); i++) {
            System.out.println((i + 1) + ". " + allAccounts.get(i).getAccountNumber() + " (" + allAccounts.get(i).getBalance() + ")");
        }

        int accountIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (accountIndex < 0 || accountIndex >= allAccounts.size()) {
            System.out.println("Неверный выбор счета.");
            return null;
        }

        return allAccounts.get(accountIndex);
    }

    public static void deposit(final Account account, final double amount) {
        if (account.isOpen()) {
            account.addAmount(amount);
        } else {
            System.out.println("Счет закрыт, операция невозможна.");
        }
    }

    public static void withdraw(final Account account, final double amount) {
        if (account.isOpen() && account.getBalance() >= amount) {
            account.reduceAmount(amount);
        } else {
            System.out.println("Недостаточно средств или счет закрыт.");
        }
    }

    public static void closeAccount(final Account account) {
        account.isOpen(false);
    }

    public static void getAccountInfo() {
        System.out.println("Выберите счет для просмотра:");
        Account account = selectAccount();
        if (account == null || !account.isOpen()) return;

        System.out.println("Состояние счета: " + account);
    }
}
