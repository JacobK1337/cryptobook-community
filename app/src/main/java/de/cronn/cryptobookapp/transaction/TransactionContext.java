package de.cronn.cryptobookapp.transaction;

public class TransactionContext {
    private final Transaction transaction;

    TransactionContext(Transaction transaction) {
        this.transaction = transaction;
    }

    public void performOn(User user) {
        transaction.execute(user);
    }
}
