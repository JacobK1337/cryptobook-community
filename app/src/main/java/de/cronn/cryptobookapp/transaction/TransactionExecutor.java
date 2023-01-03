package de.cronn.cryptobookapp.transaction;

import de.cronn.cryptobookapp.model.User;

public class TransactionExecutor {
    private final Transaction<?> transaction;

    TransactionExecutor(Transaction<?> transaction) {
        this.transaction = transaction;
    }

    public void performOn(User user) {
        //set important data about Transaction from transaction.getContext()
        //execute Transaction
        //save User to repo
        transaction.execute(user);
    }
}
