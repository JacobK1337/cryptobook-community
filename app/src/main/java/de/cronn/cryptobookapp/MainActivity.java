package de.cronn.cryptobookapp;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import de.cronn.cryptobookapp.http.Currencies;
import de.cronn.cryptobookapp.http.Currency;
import de.cronn.cryptobookapp.observer.TextViewObservableDecorator;
import de.cronn.cryptobookapp.transaction.ExchangeTransaction;
import de.cronn.cryptobookapp.transaction.TransactionContext;
import de.cronn.cryptobookapp.transaction.User;

public class MainActivity extends AppCompatActivity {
    private TextView helloText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloText = findViewById(R.id.helloText);
        TextViewObservableDecorator textViewObservableDecorator = new TextViewObservableDecorator(helloText);
        Currencies.listenForChanges(textViewObservableDecorator);

        User user = new User();
        user.addWallet(Currency.BTC);
        user.addWallet(Currency.DOGE);
        TransactionContext transactionContext = new TransactionContext(new ExchangeTransaction(user));
        transactionContext.execute();
    }
}