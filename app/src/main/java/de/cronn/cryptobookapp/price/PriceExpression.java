package de.cronn.cryptobookapp.price;


import java.io.IOException;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

import de.cronn.cryptobookapp.http.Currencies;

public final class PriceExpression {
    private final Type type;
    private final Price first;
    private final Price second;

    public PriceExpression(Type type, Price first, Price second) {
        this.type = type;
        this.first = first;
        this.second = second;
    }

    public enum Type {
        SUM((a, b) -> new Price(a.getCurrency(), a.getValue().add(b.getValue()))),
        SUBTRACT((a, b) -> new Price(a.getCurrency(), a.getValue().subtract(b.getValue()))),
        MULTIPLY((a, b) -> new Price(a.getCurrency(), a.getValue().multiply(b.getValue()))),
        DIVIDE((a, b) -> new Price(a.getCurrency(), a.getValue().divide(b.getValue(), RoundingMode.CEILING)));

        private final BiFunction<Price, Price, Price> strategy;

        Type(BiFunction<Price, Price, Price> strategy) {
            this.strategy = strategy;
        }
    }

    public Price getAs(Currency currency) {
        return type.strategy.apply(Currencies.convert(first, currency), Currencies.convert(second, currency));
    }
}
