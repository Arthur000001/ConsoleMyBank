
import java.util.*;

import static service.AccountService.*;
import static service.ClientService.createClient;

public class Application {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Создать клиента");
            System.out.println("2. Создать счет клиенту");
            System.out.println("3. Закрыть счет клиенту");
            System.out.println("4. Информация по счету");
            System.out.println("5. Перевести денежные средства");
            System.out.println("6. Зачислить денежные средства");

            System.out.println("0. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Очистка после ввода числа
            switch (choice) {
                case 1 -> createClient();
                case 2 -> createAccount();
                case 3 -> closeAccount();
                case 4 -> getAccountInfo();
                case 5 -> transferFunds();
                case 6 -> depositFunds();
                case 0 -> running = false;
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
