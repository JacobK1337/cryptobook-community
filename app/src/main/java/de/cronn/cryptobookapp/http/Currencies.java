package de.cronn.cryptobookapp.http;


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

    public static void listenForChanges(Observable observable){
        currenciesDataFetcher.addObserved(observable);
    }

}
