package de.cronn.cryptobookapp;

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

public class MainActivity extends AppCompatActivity {
    private TextView helloText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloText = findViewById(R.id.helloText);
        CurrenciesDataFetcher currenciesDataFetcher = CurrenciesDataFetcher.getInstance();

        Price price = supplyAsync(() -> currenciesDataFetcher.fetchCoinPrice(DOGE, USD));
        helloText.setText("DOGE IN " + price.getCurrency() + ": " + price.getPrice());
    }


    private <T> T supplyAsync(Supplier<T> supplier) {
        try {
            return CompletableFuture.supplyAsync(supplier).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}