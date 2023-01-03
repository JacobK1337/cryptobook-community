package de.cronn.cryptobookapp.transaction.exchange;

import android.util.Log;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.price.Price;
import de.cronn.cryptobookapp.transaction.Transaction;
import de.cronn.cryptobookapp.transaction.User;
import de.cronn.cryptobookapp.transaction.Wallet;

public class ExchangeTransaction extends Transaction<ExchangeContext> {
    public ExchangeTransaction(ExchangeContext exchangeContext) {
        super(exchangeContext);
    }

    @Override
    protected void execute(User user) {
        Wallet exchangeFrom = user.getWallet(getContext().getFrom());
        Wallet exchangeTo = user.getWallet(getContext().getTo());

        assertHasEnoughCredits(exchangeFrom, getContext().getAmount());
        Log.i("EXCHANGE: ", "Exchanging " + getContext().getAmount() + " " + getContext().getFrom() +
                " to: " + getContext().getTo());

        Price converted = new Price(getContext().getFrom(), getContext().getAmount())
                .convertTo(getContext().getTo());

        exchangeFrom.setValue(exchangeFrom.getValue().subtract(getContext().getAmount()));
        exchangeTo.setValue(exchangeTo.getValue().add(converted.getValue()));
    }

    private void assertHasEnoughCredits(Wallet wallet, BigDecimal amount){
        if(wallet.getValue().compareTo(amount) < 0){
            throw new IllegalStateException("Not enough credits");
        }
    }
}
