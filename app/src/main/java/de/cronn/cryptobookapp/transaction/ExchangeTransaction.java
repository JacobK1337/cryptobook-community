package de.cronn.cryptobookapp.transaction;

import android.util.Log;

public class ExchangeTransaction extends Transaction{
    private final User user;

    public ExchangeTransaction(User user) {
        this.user = user;
    }

    @Override
    protected void execute() {
        Log.i("TRANSACTION", "Executing exchange transaction on User");
    }
}
