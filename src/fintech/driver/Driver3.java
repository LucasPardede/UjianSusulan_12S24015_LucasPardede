package fintech.driver;

import fintech.model.DepositTransaction;
import fintech.model.TransferTransaction;
import fintech.model.NegativeBalanceException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Driver3 {
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

            String[] parts = line.split("#", 6);
            String command = parts[0];

            switch (command) {
                case "create-account":
                    if (parts.length == 3) {
                        accounts.putIfAbsent(parts[2], new Account(parts[1], parts[2], "STANDARD"));
                    }
                    break;
                case "deposit":
                    if (parts.length == 5) {
                        Account account = accounts.get(parts[1]);
                        if (account != null) {
                            double amount = Double.parseDouble(parts[2]);
                            DepositTransaction deposit = new DepositTransaction(parts[1], amount, parts[3], parts[4]);
                            deposit.process(account, null);
                        }
                    }
                    break;
                case "transfer":
                    if (parts.length == 6) {
                        Account sender = accounts.get(parts[1]);
                        Account receiver = accounts.get(parts[2]);
                        if (sender != null && receiver != null) {
                            try {
                                double amount = Double.parseDouble(parts[3]);
                                TransferTransaction transfer = new TransferTransaction(parts[1], amount, parts[4], parts[5]);
                                transfer.process(sender, receiver);
                            } catch (NegativeBalanceException ignored) {
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        for (Account account : accounts.values()) {
            System.out.printf("%s|%s|%.1f%n", account.getUsername(), account.getName(), account.getBalance());
        }
        scanner.close();
    }
}
