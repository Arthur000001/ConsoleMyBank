package repository;

import model.Client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepositoryClients {
    private static final List<Client> clients = new ArrayList<>();
    private static final Set<String> phoneNumbers = new HashSet<>();

    public static void addClient(Client client) {
        clients.add(client);
    }

    public static void addPhoneNumber(String phoneNumber) {
        phoneNumbers.add(phoneNumber);
    }

    public static List<Client> getClients() {
        return clients;
    }

    public static Client getClient(int clientIndex) {
        return clients.get(clientIndex);
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        return phoneNumbers.contains(phoneNumber);
    }
}
