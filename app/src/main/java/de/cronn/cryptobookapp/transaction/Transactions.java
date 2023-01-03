package de.cronn.cryptobookapp.transaction;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.model.Offer;
import de.cronn.cryptobookapp.price.Currency;
import de.cronn.cryptobookapp.transaction.exchange.ExchangeContext;
import de.cronn.cryptobookapp.transaction.exchange.ExchangeTransaction;
import de.cronn.cryptobookapp.transaction.purchase.PurchaseContext;
import de.cronn.cryptobookapp.transaction.purchase.PurchaseTransaction;
import de.cronn.cryptobookapp.transaction.sell.SaleContext;
import de.cronn.cryptobookapp.transaction.sell.SaleTransaction;

public final class Transactions {
    private Transactions(){}

    public static TransactionExecutor exchangeCurrencies(Currency from, BigDecimal amount, Currency to){
        ExchangeTransaction exchangeTransaction = new ExchangeTransaction(new ExchangeContext(from, amount, to));
        return new TransactionExecutor(exchangeTransaction);
    }

    public static TransactionExecutor sell(Currency from, BigDecimal amount){
        SaleTransaction saleTransaction = new SaleTransaction(new SaleContext(from, amount));
        return new TransactionExecutor(saleTransaction);
    }

    public static TransactionExecutor purchase(Offer offer){
        PurchaseTransaction purchaseTransaction = new PurchaseTransaction(new PurchaseContext(offer));
        return new TransactionExecutor(purchaseTransaction);
    }
}
