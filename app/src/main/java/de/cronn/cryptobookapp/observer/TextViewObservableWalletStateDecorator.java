package de.cronn.cryptobookapp.observer;

import android.graphics.Color;
import android.widget.TextView;

import de.cronn.cryptobookapp.db.model.Wallet;
import de.cronn.cryptobookapp.http.Currencies;
import de.cronn.cryptobookapp.price.Currency;
import de.cronn.cryptobookapp.price.Price;

public class TextViewObservableWalletStateDecorator implements Observable{

    private final TextView textView;
    private final Wallet wallet;
    private int notifications = 0;

    public TextViewObservableWalletStateDecorator(TextView textView, Wallet wallet) {
        this.textView = textView;
        this.wallet = wallet;
        Currencies.listenForChanges(this);
        textView.post(this::updateText);
    }

    @Override
    public void update() {
        textView.post(this::updateText);
    }

    public void updateText(){
        Price walletBalanceInUsd = new Price(wallet.getCurrency(), wallet.getBalance())
                .convertTo(Currency.USD);

        textView.setTextColor(Color.GREEN);
        textView.setText("Balance: " + wallet.getBalance() + " (" + walletBalanceInUsd.getValue() + " USD) " + notifications);
        notifications ++;
    }

    public TextView getTextView() {
        return textView;
    }
}
