package de.cronn.cryptobookapp.transaction.sell;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.price.Currency;
import de.cronn.cryptobookapp.transaction.Context;

public class SaleContext implements Context {
    private final Currency currency;
    private final BigDecimal amount;

    public SaleContext(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
