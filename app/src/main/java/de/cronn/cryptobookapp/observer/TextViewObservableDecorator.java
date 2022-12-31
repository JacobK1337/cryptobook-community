package de.cronn.cryptobookapp.observer;

import static de.cronn.cryptobookapp.price.Currency.BTC;
import static de.cronn.cryptobookapp.price.Currency.DOGE;
import static de.cronn.cryptobookapp.price.Currency.ETH;
import static de.cronn.cryptobookapp.price.Currency.USD;

import android.widget.TextView;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.http.Currencies;
import de.cronn.cryptobookapp.price.Currency;
import de.cronn.cryptobookapp.price.Price;
import de.cronn.cryptobookapp.price.PriceExpression;

public class TextViewObservableDecorator implements Observable {
    private final TextView textView;
    public TextViewObservableDecorator(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void update() {
        textView.post(() -> {
            Price ETH3 = new Price(ETH, new BigDecimal(3));
            Price DOGE12 = new Price(DOGE, new BigDecimal(12));
            Price BTC3 = new Price(BTC, new BigDecimal(3));
            PriceExpression priceExpression = ETH3.plus(DOGE12);
            String ethPlusDoge = priceExpression.getAs(USD).getValue().toString();

            PriceExpression btcEthExpression = BTC3.plus(ETH3);
            String btcPlusEth = btcEthExpression.getAs(USD).getValue().toString();


            String newText = DOGE.name() + ": " + Currencies.getUsdPrice(DOGE).getValue() + "\n" +
                    BTC.name() + ": " + Currencies.getUsdPrice(BTC).getValue() + "\n" +
                    ETH.name() + ": " + Currencies.getUsdPrice(ETH).getValue() + "\n" +
                    "ETH TO DOGE: " + Currencies.convert(Currencies.getUsdPrice(ETH), DOGE).getValue() + "\n" +
                    "BTC TO ETH: " + Currencies.convert(new Price(BTC, new BigDecimal(1)), ETH).getValue() + "\n" +
                    "3 ETH + 12 DOGE: " + ethPlusDoge + "\n" +
                    "3 BTC + 3 ETH: " + btcPlusEth;
            textView.setText(newText);
        });
    }
}
