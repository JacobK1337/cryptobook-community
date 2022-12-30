package de.cronn.cryptobookapp.transaction;

import com.google.common.collect.MoreCollectors;

import java.util.ArrayList;
import java.util.List;

import de.cronn.cryptobookapp.http.Currency;

//entity candidate
public class User {
    private List<Wallet> wallets = new ArrayList<>();

    public Wallet getWallet(Currency currency){
        return wallets.stream().filter(wallet -> wallet.getCurrency() == currency)
                .collect(MoreCollectors.onlyElement());
    }

    public void addWallet(Currency currency){
        wallets.add(new Wallet(currency));
    }
}
