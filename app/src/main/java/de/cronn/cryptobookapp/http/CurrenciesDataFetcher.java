package de.cronn.cryptobookapp.http;


import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import de.cronn.cryptobookapp.observer.Observable;
import de.cronn.cryptobookapp.observer.Observer;

public class CurrenciesDataFetcher implements Observer {
    private static final String API_URL = "https://api.coingecko.com/api/v3/";
    private static CurrenciesDataFetcher instance;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    private final List<Observable> observables = new ArrayList<>();
    private final static Map<Currency, Price> CURRENCIES_IN_USD = new ConcurrentHashMap<>();

    private CurrenciesDataFetcher() {
    }


    public static CurrenciesDataFetcher getInstance() {
        if (instance == null) {
            instance = new CurrenciesDataFetcher();
        }
        return instance;
    }

    public Price getPrice(Currency currency){
        return CURRENCIES_IN_USD.get(currency);
    }

    public void scheduleFetching()  {
        scheduledExecutor.scheduleAtFixedRate(this::fetchAndNotify,0, 20, TimeUnit.SECONDS);
    }

    private void fetchAndNotify(){
        for(Currency currency : Currency.values()){
            Price priceInUsd = CompletableFuture.supplyAsync(() -> fetchCoinPrice(currency, Currency.USD)).join();
            CURRENCIES_IN_USD.put(currency, priceInUsd);
        }
        notifyObserved();
    }


    public Price fetchCoinPrice(Currency currency, Currency getAs) {
        Log.i("API_CALL", "Fetching currency " + currency.name());
        try {
            URL url = new URL(API_URL + "simple/price?ids=" + currency.getApiName() + "&vs_currencies=" + getAs.getApiName());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(conn.getInputStream());

            String responseBody = new BufferedReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8)
            ).lines().collect(Collectors.joining());

            TypeReference<HashMap<String, HashMap<String, BigDecimal>>> typeReference = new TypeReference<>() {};
            Map<String, Map<String, BigDecimal>> prices = objectMapper.readValue(responseBody, typeReference);
            in.close();
            conn.disconnect();
            return new Price(getAs, prices.get(currency.getApiName()).get(getAs.getApiName()));

        } catch (IOException ioException) {
            return null;
        }
    }

    @Override
    public void addObserved(Observable observable) {
        observables.add(observable);
    }

    @Override
    public void notifyObserved() {
        observables.forEach(Observable::update);
    }
}
