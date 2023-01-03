package de.cronn.cryptobookapp.transaction.exchange;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.price.Currency;
import de.cronn.cryptobookapp.transaction.Context;

public final class ExchangeContext implements Context {
    private final Currency from;
    private final BigDecimal amount;
    private final Currency to;

    public ExchangeContext(Currency from, BigDecimal amount, Currency to) {
        this.from = from;
        this.amount = amount;
        this.to = to;
    }

    public Currency getFrom() {
        return from;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getTo() {
        return to;
    }
}
