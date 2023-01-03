package de.cronn.cryptobookapp;



import static de.cronn.cryptobookapp.price.Currency.BTC;
import static de.cronn.cryptobookapp.price.Currency.DOGE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.http.Currencies;
import de.cronn.cryptobookapp.observer.TextViewObservableDecorator;
import de.cronn.cryptobookapp.transaction.Transactions;
import de.cronn.cryptobookapp.transaction.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView helloText = findViewById(R.id.helloText);
        TextViewObservableDecorator textViewObservableDecorator = new TextViewObservableDecorator(helloText);
        Currencies.listenForChanges(textViewObservableDecorator);

        User user = new User();
        user.addWallet(BTC);
        user.addWallet(DOGE);

        user.getWallet(BTC).setValue(new BigDecimal(5));
        //5 BTC, 0 DOGE
        Log.i("WALLET", "BTC: " + user.getWallet(BTC).getValue());
        Log.i("WALLET", "DOGE: " + user.getWallet(DOGE).getValue());

        Transactions.exchangeCurrencies(BTC, new BigDecimal(1), DOGE)
                .performOn(user);

        //4 BTC, more DOGE :p
        Log.i("WALLET", "BTC: " + user.getWallet(BTC).getValue());
        Log.i("WALLET", "DOGE: " + user.getWallet(DOGE).getValue());
    }
}