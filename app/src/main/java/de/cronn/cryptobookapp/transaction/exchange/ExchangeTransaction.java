package de.cronn.cryptobookapp.transaction.exchange;

import android.util.Log;


import de.cronn.cryptobookapp.model.User;
import de.cronn.cryptobookapp.model.Wallet;
import de.cronn.cryptobookapp.price.Price;
import de.cronn.cryptobookapp.transaction.Transaction;

public class ExchangeTransaction extends Transaction<ExchangeContext> {
    public ExchangeTransaction(ExchangeContext exchangeContext) {
        super(exchangeContext);
    }

    @Override
    protected void execute(User user) {
        Wallet exchangeFrom = user.getWallet(getContext().getFrom());
        Wallet exchangeTo = user.getWallet(getContext().getTo());

        Log.i("EXCHANGE: ", "Exchanging " + getContext().getAmount() + " " + getContext().getFrom() +
                " to: " + getContext().getTo());

        Price converted = new Price(getContext().getFrom(), getContext().getAmount())
                .convertTo(getContext().getTo());

        exchangeFrom.setBalance(exchangeFrom.getBalance().subtract(getContext().getAmount()));
        exchangeTo.setBalance(exchangeTo.getBalance().add(converted.getValue()));
    }
}
