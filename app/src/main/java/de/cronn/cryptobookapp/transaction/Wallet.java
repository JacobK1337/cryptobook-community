package de.cronn.cryptobookapp.transaction;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.price.Currency;

//entity candidate
public class Wallet {
    private final Currency currency;
    private BigDecimal value = new BigDecimal(0);

    public Wallet(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
