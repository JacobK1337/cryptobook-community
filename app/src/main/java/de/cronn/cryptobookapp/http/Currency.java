package de.cronn.cryptobookapp.http;

public enum Currency {
    BTC("bitcoin"),
    DOGE("dogecoin"),
    ETH("ethereum"),
    USD("usd");

    private final String apiName;

    Currency(String apiName) {
        this.apiName = apiName;
    }

    public String getApiName() {
        return apiName;
    }
}
