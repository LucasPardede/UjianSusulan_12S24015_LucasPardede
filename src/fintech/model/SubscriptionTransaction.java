package fintech.model;

import fintech.driver.Account;

public class SubscriptionTransaction extends Transaction {

    public SubscriptionTransaction(String username, double amount, String timestamp, String description) {
        super(username, amount, timestamp, description);
    }

    @Override
    public void process(Account sender, Account receiver) throws Exception {
        if (sender.getBalance() < amount) {
            throw new InsufficientBalanceException();
        }

        fee = 0.0;
        netAmount = amount;

        sender.deductBalance(amount);
        sender.addTransaction(this);
    }

    @Override
    public String getType() {
        return "subscription";
    }
}