package de.cronn.cryptobookapp.transaction.sell;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.price.Currency;
import de.cronn.cryptobookapp.transaction.Context;

public class SaleContext implements Context {
    private final Currency toSell;
    private final BigDecimal amount;
    private final Currency receiveAfter;

    public SaleContext(Currency toSell, BigDecimal amount, Currency receiveAfter) {
        this.toSell = toSell;
        this.amount = amount;
        this.receiveAfter = receiveAfter;
    }

    public Currency getToSell() {
        return toSell;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getReceiveAfter() {
        return receiveAfter;
    }
}
