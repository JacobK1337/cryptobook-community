package de.cronn.cryptobookapp.price;


import java.util.function.BiFunction;

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
        SUM((a, b) -> sum(a, b)),
        SUBTRACT((a, b) -> subtract(a, b));

        private final BiFunction<Price, Price, Price> strategy;

        Type(BiFunction<Price, Price, Price> strategy) {
            this.strategy = strategy;
        }
    }

    public Price getAs(Currency currency) {
        Price firstInGivenCurrency = Currencies.convert(first, currency);
        Price secondInGivenCurreny = Currencies.convert(second, currency);

        return type.strategy.apply(firstInGivenCurrency, secondInGivenCurreny);
    }

    private static Price sum(Price one, Price two) {
        assertCurrenciesMatch(one, two);
        return new Price(one.getCurrency(), one.getValue().add(two.getValue()));
    }

    private static Price subtract(Price one, Price two) {
        assertCurrenciesMatch(one, two);
        return new Price(one.getCurrency(), one.getValue().subtract(two.getValue()));
    }

    private static void assertCurrenciesMatch(Price one, Price two){
        if(one.getCurrency() != two.getCurrency()){
            throw new IllegalStateException("Currencies don't match");
        }
    }
}
