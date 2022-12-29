package de.cronn.cryptobookapp;

import static de.cronn.cryptobookapp.http.Currency.BTC;
import static de.cronn.cryptobookapp.http.Currency.DOGE;
import static de.cronn.cryptobookapp.http.Currency.USD;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

import de.cronn.cryptobookapp.http.CurrenciesDataFetcher;
import de.cronn.cryptobookapp.http.Price;
import de.cronn.cryptobookapp.observer.TextViewObservableDecorator;

public class MainActivity extends AppCompatActivity {
    private TextView helloText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloText = findViewById(R.id.helloText);
        TextViewObservableDecorator textViewObservableDecorator = new TextViewObservableDecorator(helloText);
        CurrenciesDataFetcher currenciesDataFetcher = CurrenciesDataFetcher.getInstance();
        currenciesDataFetcher.addObserved(textViewObservableDecorator);
        currenciesDataFetcher.scheduleFetching();
    }
}