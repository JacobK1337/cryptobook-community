package de.cronn.cryptobookapp.observer;

import android.widget.TextView;

import de.cronn.cryptobookapp.http.CurrenciesDataFetcher;
import de.cronn.cryptobookapp.http.Currency;

public class TextViewObservableDecorator implements Observable {
    private final TextView textView;
    private final CurrenciesDataFetcher currenciesDataFetcher = CurrenciesDataFetcher.getInstance();

    public TextViewObservableDecorator(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void update() {
        textView.post(() -> {
            String newText = Currency.DOGE.name() + ": " + currenciesDataFetcher.getPrice(Currency.DOGE).getValue() + "\n" +
                    Currency.BTC.name() + ": " + currenciesDataFetcher.getPrice(Currency.BTC).getValue() + "\n" +
                    Currency.ETH.name() + ": " + currenciesDataFetcher.getPrice(Currency.ETH).getValue();
            textView.setText(newText);
        });
    }
}
