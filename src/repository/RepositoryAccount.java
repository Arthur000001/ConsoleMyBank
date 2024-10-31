package repository;

import java.util.HashSet;
import java.util.Set;

public class RepositoryAccount {
    private static final Set<String> accountNumbers = new HashSet<>();

    public static void addAccountNumber(String accountNumber) {
        accountNumbers.add(accountNumber);
    }

    public static boolean isAccountNumberExists(String accountNumber) {
        return accountNumbers.contains(accountNumber);
    }

    public static Set<String> getAccountNumbers() {
        return accountNumbers;
    }

    public static boolean checkPhoneNumber(String accountNumber) {
        return accountNumbers.contains(accountNumber);
    }

}
