package de.cronn.cryptobookapp.transaction.sell;

import android.util.Log;


import de.cronn.cryptobookapp.model.User;
import de.cronn.cryptobookapp.model.Wallet;
import de.cronn.cryptobookapp.transaction.Transaction;

public class SaleTransaction extends Transaction<SaleContext> {

    public SaleTransaction(SaleContext saleContext) {
        super(saleContext);
    }

    @Override
    protected void execute(User user) {
        Wallet wallet = user.getWallet(getContext().getCurrency());

        Log.i("TRANSACTION",
                String.format("Putting %s %s to sale", getContext().getAmount(), getContext().getCurrency()));
    }
}
