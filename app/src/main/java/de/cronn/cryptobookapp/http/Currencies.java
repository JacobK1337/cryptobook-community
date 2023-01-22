package de.cronn.cryptobookapp.http;


import android.util.Log;

import java.math.RoundingMode;

import de.cronn.cryptobookapp.observer.Observable;
import de.cronn.cryptobookapp.price.Currency;
import de.cronn.cryptobookapp.price.Price;

public final class Currencies {
    private static final CurrenciesDataFetcher currenciesDataFetcher = CurrenciesDataFetcher.getInstance();

    private Currencies(){}

    public static void init(){
    }

    public static Price getUsdPrice(Currency currency){
        return currenciesDataFetcher.getUsdPrice(currency);
    }

    public static Price convert(Price price, Currency convertTo){
        Price convertToUsdPrice = Currencies.getUsdPrice(convertTo);
        Price priceInUsd = new Price(Currency.USD,
                price.getValue().multiply(Currencies.getUsdPrice(price.getCurrency()).getValue()));

        Log.i("CONVERT: ", price.getCurrency() + " " + price.getValue() + " = " + priceInUsd.getValue() + " USD");
        return new Price(convertTo, priceInUsd.getValue().divide(convertToUsdPrice.getValue(), RoundingMode.CEILING));
    }

    public static void listenForChanges(Observable observable){
        currenciesDataFetcher.addObserved(observable);
    }

}
