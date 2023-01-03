package de.cronn.cryptobookapp.transaction;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.price.Currency;
import de.cronn.cryptobookapp.transaction.exchange.ExchangeContext;
import de.cronn.cryptobookapp.transaction.exchange.ExchangeTransaction;

public final class Transactions {
    private Transactions(){}

    public static TransactionExecutor exchangeCurrencies(Currency from, BigDecimal amount, Currency to){
        ExchangeTransaction exchangeTransaction = new ExchangeTransaction(new ExchangeContext(from, amount, to));
        return new TransactionExecutor(exchangeTransaction);
    }
}
