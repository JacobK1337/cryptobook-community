package de.cronn.cryptobookapp.transaction;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.price.Currency;
import de.cronn.cryptobookapp.transaction.exchange.ExchangeContext;
import de.cronn.cryptobookapp.transaction.exchange.ExchangeTransaction;
import de.cronn.cryptobookapp.transaction.purchase.PurchaseContext;
import de.cronn.cryptobookapp.transaction.purchase.PurchaseTransaction;
import de.cronn.cryptobookapp.transaction.sell.SaleContext;
import de.cronn.cryptobookapp.transaction.sell.SaleTransaction;

public final class Transactions {
    private Transactions(){}

    public static TransactionExecutor sell(Currency toSell, BigDecimal amount, Currency sellFor){
        SaleTransaction saleTransaction = new SaleTransaction(new SaleContext(toSell, amount, sellFor));
        return new TransactionExecutor(saleTransaction);
    }

    public static TransactionExecutor purchase(Currency toPurchase, BigDecimal amount, Currency purchaseWith){
        PurchaseTransaction purchaseTransaction = new PurchaseTransaction(new PurchaseContext(toPurchase, amount, purchaseWith));
        return new TransactionExecutor(purchaseTransaction);
    }
}
