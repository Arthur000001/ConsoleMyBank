package service;

import model.Account;
import model.Client;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static repository.RepositoryClients.*;

public class ClientService {

    private static Scanner scanner = new Scanner(System.in);

    public static Client selectClientWithOpenAccounts() {
        List<Client> clientsWithOpenAccounts = getClients().stream()
                .filter(client -> client.getAccounts().stream().anyMatch(Account::isOpen))
                .collect(Collectors.toList());
        if (clientsWithOpenAccounts.isEmpty()) {
            System.out.println("Клиентов с открытыми счетами не найдено.");
            return null;
        }

        System.out.println("Выберите клиента:");
        for (int i = 0; i < clientsWithOpenAccounts.size(); i++) {
            System.out.println((i + 1) + ". " + clientsWithOpenAccounts.get(i).getName());
        }

        int clientIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (clientIndex < 0 || clientIndex >= clientsWithOpenAccounts.size()) {
            System.out.println("Неверный выбор клиента.");
            return null;
        }

        return clientsWithOpenAccounts.get(clientIndex);
    }

    public static void createClient() {
        System.out.println("Введите ФИО:");
        String name = scanner.nextLine();
        System.out.println("Введите номер телефона:");
        String phoneNumber = scanner.nextLine();
        if (checkPhoneNumber(phoneNumber)) {
            System.out.println("Клиент с таким номером телефона уже существует.");
            return;
        }
        System.out.println("Введите ИНН:");
        String inn = scanner.nextLine();
        System.out.println("Введите адрес:");
        String address = scanner.nextLine();

        Client client = new Client(name, phoneNumber, inn, address);
        addClient(client);
        addPhoneNumber(phoneNumber);
        System.out.println("Клиент успешно создан.");
    }

}
