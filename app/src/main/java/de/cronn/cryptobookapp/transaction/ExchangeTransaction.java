package de.cronn.cryptobookapp.transaction;

import android.util.Log;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.http.Currencies;
import de.cronn.cryptobookapp.price.Price;

public class ExchangeTransaction extends Transaction{
    private final ExchangeContext exchangeContext;

    public ExchangeTransaction(ExchangeContext exchangeContext) {
        this.exchangeContext = exchangeContext;
    }

    @Override
    protected void execute(User user) {
        Wallet exchangeFrom = user.getWallet(exchangeContext.getFrom());
        Wallet exchangeTo = user.getWallet(exchangeContext.getTo());

        assertHasEnoughCredits(exchangeFrom, exchangeContext.getAmount());
        Log.i("EXCHANGE: ", "Exchanging " + exchangeContext.getAmount() + " " + exchangeContext.getFrom() +
                " to: " + exchangeContext.getTo());
        Price converted = Currencies.convert(
                new Price(exchangeContext.getFrom(), exchangeContext.getAmount()), exchangeContext.getTo());

        exchangeFrom.setValue(exchangeFrom.getValue().subtract(exchangeContext.getAmount()));
        exchangeTo.setValue(exchangeTo.getValue().add(converted.getValue()));
    }

    private void assertHasEnoughCredits(Wallet wallet, BigDecimal amount){
        if(wallet.getValue().compareTo(amount) < 0){
            throw new IllegalStateException("Not enough credits");
        }
    }
}
