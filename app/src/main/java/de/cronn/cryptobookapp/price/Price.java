package de.cronn.cryptobookapp.price;

import static de.cronn.cryptobookapp.price.PriceExpression.Type.DIVIDE;
import static de.cronn.cryptobookapp.price.PriceExpression.Type.MULTIPLY;
import static de.cronn.cryptobookapp.price.PriceExpression.Type.SUBTRACT;
import static de.cronn.cryptobookapp.price.PriceExpression.Type.SUM;

import java.math.BigDecimal;


public class Price {
    private final Currency currency;
    private final BigDecimal value;

    public Price(Currency currency, BigDecimal value) {
        this.currency = currency;
        this.value = value;
    }

    public PriceExpression plus(Price price){
        return new PriceExpression(SUM, this, price);
    }

    public PriceExpression subtract(Price price){
        return new PriceExpression(SUBTRACT, this, price);
    }

    public PriceExpression multiply(Price price){
        return new PriceExpression(MULTIPLY, this, price);
    }

    public PriceExpression divide(Price price){
        return new PriceExpression(DIVIDE, this, price);
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getValue() {
        return value;
    }
}
