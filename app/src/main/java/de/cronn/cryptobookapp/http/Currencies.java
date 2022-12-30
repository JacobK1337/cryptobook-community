package de.cronn.cryptobookapp.http;

import java.math.BigDecimal;
import java.math.RoundingMode;

import de.cronn.cryptobookapp.observer.Observable;

public final class Currencies {
    private static final CurrenciesDataFetcher currenciesDataFetcher =
            CurrenciesDataFetcher.getInstance();

    private Currencies(){}

    public static Price getUsdPrice(Currency currency){
        return currenciesDataFetcher.getUsdPrice(currency);
    }

    public static void listenForChanges(Observable observable){
        currenciesDataFetcher.addObserved(observable);
    }

    public static Price convert(Price convertFrom, Currency currencyTo){
        BigDecimal oldCurrencyUsdPrice = convertFrom.getValue()
                .multiply(currenciesDataFetcher.getUsdPrice(convertFrom.getCurrency()).getValue());
        BigDecimal newCurrencyUsdPrice = currenciesDataFetcher.getUsdPrice(currencyTo)
                .getValue();
        return new Price(currencyTo, oldCurrencyUsdPrice.divide(newCurrencyUsdPrice, RoundingMode.CEILING));
    }
}
