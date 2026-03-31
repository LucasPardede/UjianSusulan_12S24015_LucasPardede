package fintech.model;

public class NegativeBalanceException extends Exception {
    public NegativeBalanceException() {
        super("Negative balance is not allowed");
    }

    public NegativeBalanceException(String message) {
        super(message);
    }
}
