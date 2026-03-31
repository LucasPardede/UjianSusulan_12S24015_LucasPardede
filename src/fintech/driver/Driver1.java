package fintech.driver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Driver1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Account> accounts = new LinkedHashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("---")) {
                break;
            }
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("#", 3);
            if (parts.length == 3 && parts[0].equals("create-account")) {
                String name = parts[1];
                String username = parts[2];
                accounts.putIfAbsent(username, new Account(name, username, "STANDARD"));
            }
        }

        for (Account account : accounts.values()) {
            System.out.printf("%s|%s|%.1f%n", account.getUsername(), account.getName(), account.getBalance());
        }
        scanner.close();
    }
}
