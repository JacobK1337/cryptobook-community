package de.cronn.cryptobookapp.transaction;

public class TransactionContext {
    private final Transaction transaction;

    public TransactionContext(Transaction transaction) {
        this.transaction = transaction;
    }

    public void execute(){
        transaction.execute();
    }
}
