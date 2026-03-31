package fintech.model;

import fintech.driver.Account;

public class CashbackTransaction extends Transaction {

    public CashbackTransaction(String username, double amount, String timestamp, String description) {
        super(username, amount, timestamp, description);
    }

    @Override
    public void process(Account sender, Account receiver) {
        fee = 0.0;
        netAmount = amount;
        sender.addBalance(amount);
        sender.addTransaction(this);
    }

    @Override
    public String getType() {
        return "cashback";
    }
}