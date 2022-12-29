package de.cronn.cryptobookapp.http;

import java.math.BigDecimal;

public class Price {
    private final Currency currency;
    private final BigDecimal price;

    public Price(Currency currency, BigDecimal price) {
        this.currency = currency;
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
