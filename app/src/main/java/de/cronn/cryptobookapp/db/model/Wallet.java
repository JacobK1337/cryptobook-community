package de.cronn.cryptobookapp.db.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.price.Currency;
import de.cronn.cryptobookapp.price.Price;

@Entity(tableName = "wallet", foreignKeys = {
        @ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "userId",
        onDelete = CASCADE)
})
public class Wallet {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "walletId")
    private long id;

    @ColumnInfo(name = "userId")
    private long userId;

    @ColumnInfo(name = "currency")
    private Currency currency;

    @ColumnInfo(name = "balance")
    private BigDecimal balance;

    public Wallet(long userId, Currency currency, BigDecimal balance) {
        this.userId = userId;
        this.currency = currency;
        this.balance = balance;
    }

    public Wallet(long userId, Price price){
        this.userId = userId;
        this.currency = price.getCurrency();
        this.balance = price.getValue();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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

    public Price getAsPrice(){
        return new Price(getCurrency(), getBalance());
    }

    public void add(BigDecimal amount){
        this.balance = this.balance.add(amount);
    }

    public void subtract(BigDecimal amount){
        this.balance = this.balance.subtract(amount);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", userId=" + userId +
                ", currency=" + currency +
                ", balance=" + balance +
                '}';
    }
}
