package de.cronn.cryptobookapp.transaction;

import de.cronn.cryptobookapp.db.model.UserWithWallets;

public abstract class Transaction<T extends Context> {
    private final T context;

    public Transaction(T context) {
        this.context = context;
    }
    
    protected abstract TransactionResult execute(UserWithWallets user);

    public T getContext() {
        return context;
    }
}
