package de.cronn.cryptobookapp.db.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.google.common.collect.MoreCollectors;

import java.util.List;

import de.cronn.cryptobookapp.price.Currency;

public class UserWithWallets {

    @Embedded
    private User user;

    @Relation(parentColumn = "id", entityColumn = "userId", entity = Wallet.class)
    private List<Wallet> wallets;

    @Relation(parentColumn = "id", entityColumn = "userId", entity = TransactionEntry.class)
    private List<TransactionEntry> transactionEntries;

    public User getUser() {
        return user;
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    public Wallet getWallet(Currency currency){
        return wallets.stream()
                .filter(wallet -> wallet.getCurrency() == currency)
                .collect(MoreCollectors.toOptional())
                .orElse(null);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }

    public List<TransactionEntry> getTransactionEntries() {
        return transactionEntries;
    }

    public void setTransactionEntries(List<TransactionEntry> transactionEntries) {
        this.transactionEntries = transactionEntries;
    }
}
