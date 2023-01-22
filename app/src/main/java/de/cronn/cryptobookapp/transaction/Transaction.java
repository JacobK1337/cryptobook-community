package de.cronn.cryptobookapp.transaction;

import java.util.List;

import de.cronn.cryptobookapp.db.model.UserWithWallets;
import de.cronn.cryptobookapp.db.model.Wallet;

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
