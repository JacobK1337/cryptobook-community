package de.cronn.cryptobookapp.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.price.Currency;

@Entity(tableName = "wallet", foreignKeys = {
        @ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "userId",
        onDelete = CASCADE)
})
public class Wallet {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "walletId")
    private int id;

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "currency")
    private Currency currency;

    @ColumnInfo(name = "balance")
    private BigDecimal balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
