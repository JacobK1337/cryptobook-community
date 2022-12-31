package de.cronn.cryptobookapp.transaction;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.price.Currency;

public final class Transactions {
    private Transactions(){}

    public static TransactionContext exchangeCurrencies(Currency from, BigDecimal amount, Currency to){
        ExchangeTransaction exchangeTransaction = new ExchangeTransaction(new ExchangeContext(from, amount, to));
        return new TransactionContext(exchangeTransaction);
    }
}
