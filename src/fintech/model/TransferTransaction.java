package fintech.model;

import fintech.driver.Account;

public class TransferTransaction extends Transaction {

    public TransferTransaction(String username, double amount, String timestamp, String description) {
        super(username, amount, timestamp, description);
    }

    @Override
    public void process(Account sender, Account receiver) throws NegativeBalanceException {
        fee = amount * 0.02;
        double total = amount + fee;

        if (sender.getBalance() < total) {
            throw new NegativeBalanceException("Insufficient balance for transfer");
        }

        sender.deductBalance(total);
        receiver.addBalance(amount);
        netAmount = amount;
        sender.addTransaction(this);

        TransferTransaction incoming = new TransferTransaction(receiver.getUsername(), amount, timestamp, description);
        incoming.fee = 0.0;
        incoming.netAmount = amount;
        incoming.incoming = true;
        receiver.addTransaction(incoming);

        if (sender.getType().equals("PREMIUM")) {
            double cashbackAmount = amount * 0.02;
            CashbackTransaction cb = new CashbackTransaction(
                    sender.getUsername(), cashbackAmount, timestamp, "cashback"
            );
            cb.process(sender, null);
        }
    }

    @Override
    public String getType() {
        return "transfer";
    }
}
