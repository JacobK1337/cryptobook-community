package de.cronn.cryptobookapp.transaction.purchase;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.price.Currency;
import de.cronn.cryptobookapp.transaction.Context;

public class PurchaseContext implements Context {
    private final Currency toPurchase;
    private final BigDecimal amount;
    private final Currency payWith;

    public PurchaseContext(Currency toPurchase, BigDecimal amount, Currency payWith) {
        this.toPurchase = toPurchase;
        this.amount = amount;
        this.payWith = payWith;
    }

    public Currency getToPurchase() {
        return toPurchase;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getPayWith() {
        return payWith;
    }
}
