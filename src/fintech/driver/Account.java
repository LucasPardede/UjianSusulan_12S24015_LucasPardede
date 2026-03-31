package fintech.driver;

import fintech.model.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    private final String name;
    private final String username;
    private double balance;
    private final String type;
    private final List<Transaction> transactions;

    public Account(String name, String username, String type) {
        this.name = name;
        this.username = username;
        this.type = type;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public String getType() {
        return type;
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public void deductBalance(double amount) {
        this.balance -= amount;
    }

    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            this.transactions.add(transaction);
        }
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
