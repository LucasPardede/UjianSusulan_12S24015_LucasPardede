package fintech.model;

import fintech.driver.Account;

public class DepositTransaction extends Transaction {

    public DepositTransaction(String username, double amount, String timestamp, String description) {
        super(username, amount, timestamp, description);
    }

    @Override
    public void process(Account sender, Account receiver) {
        fee = 0.0;
        netAmount = amount;
        sender.addBalance(netAmount);
        sender.addTransaction(this);
    }

    @Override
    public String getType() {
        return "deposit";
    }
}