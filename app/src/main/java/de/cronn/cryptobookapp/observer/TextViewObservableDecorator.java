package de.cronn.cryptobookapp.observer;

import android.widget.TextView;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.http.Currencies;
import de.cronn.cryptobookapp.http.Currency;
import de.cronn.cryptobookapp.http.Price;

public class TextViewObservableDecorator implements Observable {
    private final TextView textView;
    public TextViewObservableDecorator(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void update() {
        textView.post(() -> {
            String newText = Currency.DOGE.name() + ": " + Currencies.getUsdPrice(Currency.DOGE).getValue() + "\n" +
                    Currency.BTC.name() + ": " + Currencies.getUsdPrice(Currency.BTC).getValue() + "\n" +
                    Currency.ETH.name() + ": " + Currencies.getUsdPrice(Currency.ETH).getValue() + "\n" +
                    "ETH TO DOGE: " + Currencies.convert(Currencies.getUsdPrice(Currency.ETH), Currency.DOGE).getValue() + "\n" +
                    "BTC TO ETH: " + Currencies.convert(new Price(Currency.BTC, new BigDecimal(1)), Currency.ETH).getValue();
            textView.setText(newText);
        });
    }
}
