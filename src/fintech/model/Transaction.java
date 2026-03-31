package fintech.model;

import fintech.driver.Account;

public abstract class Transaction {
    protected static int counter = 0;

    protected int id;
    protected String username;
    protected double amount;
    protected double fee;
    protected double netAmount;
    protected String timestamp;
    protected String description;
    protected boolean incoming;

    public Transaction(String username, double amount, String timestamp, String description) {
        this.id = ++counter;
        this.username = username;
        this.amount = amount;
        this.timestamp = timestamp;
        this.description = description;
    }

    public abstract void process(Account sender, Account receiver) throws Exception;

    public int getId() { return id; }
    public String getUsername() { return username; }
    public double getAmount() { return amount; }
    public double getFee() { return fee; }
    public double getNetAmount() { return netAmount; }
    public String getTimestamp() { return timestamp; }
    public String getDescription() { return description; }
    public boolean isIncoming() { return incoming; }

    public abstract String getType();
}