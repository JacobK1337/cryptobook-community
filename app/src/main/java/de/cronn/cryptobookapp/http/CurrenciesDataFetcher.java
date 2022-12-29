package de.cronn.cryptobookapp.http;


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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CurrenciesDataFetcher {
    private static final String API_URL = "https://api.coingecko.com/api/v3/";
    private static CurrenciesDataFetcher instance;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private CurrenciesDataFetcher() {
    }


    public static CurrenciesDataFetcher getInstance() {
        if (instance == null) {
            instance = new CurrenciesDataFetcher();
        }
        return instance;
    }

    public Price fetchCoinPrice(Currency currency, Currency getAs) {
        try {
            URL url = new URL(API_URL + "simple/price?ids=" + currency.getApiName() + "&vs_currencies=" + getAs.getApiName());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(conn.getInputStream());

            String responseBody = new BufferedReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8)
            ).lines().collect(Collectors.joining());

            TypeReference<HashMap<String, HashMap<String, BigDecimal>>> typeReference = new TypeReference<>() {};
            Map<String, Map<String, BigDecimal>> prices = objectMapper.readValue(responseBody, typeReference);
            return new Price(getAs, prices.get(currency.getApiName()).get(getAs.getApiName()));

        } catch (IOException ioException) {
            return null;
        }
    }
}
