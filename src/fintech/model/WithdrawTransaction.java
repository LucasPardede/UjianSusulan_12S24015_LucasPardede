package fintech.model;

import fintech.driver.Account;

public class WithdrawTransaction extends Transaction {

    public WithdrawTransaction(String username, double amount, String timestamp, String description) {
        super(username, amount, timestamp, description);
    }

    @Override
    public void process(Account sender, Account receiver) throws NegativeBalanceException {
        fee = amount * 0.05;
        double total = amount + fee;

        if (sender.getBalance() < total) {
            throw new NegativeBalanceException("Insufficient balance for withdraw");
        }

        sender.deductBalance(total);
        netAmount = amount;
        sender.addTransaction(this);
    }

    @Override
    public String getType() {
        return "withdraw";
    }
}