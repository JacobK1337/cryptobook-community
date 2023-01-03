package de.cronn.cryptobookapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.google.common.collect.MoreCollectors;

import java.util.List;

import de.cronn.cryptobookapp.price.Currency;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @Relation(
            parentColumn = "id",
            entityColumn = "walletId"
    )
    private List<Wallet> wallets;


    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }

    public Wallet getWallet(Currency currency){
        return wallets.stream()
                .filter(wallet -> wallet.getCurrency() == currency)
                .collect(MoreCollectors.toOptional())
                .orElse(null);
    }
}
