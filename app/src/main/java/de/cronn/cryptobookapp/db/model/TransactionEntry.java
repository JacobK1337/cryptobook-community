package de.cronn.cryptobookapp.db.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import de.cronn.cryptobookapp.price.Currency;
import de.cronn.cryptobookapp.transaction.TransactionType;

@Entity(tableName = "transactionEntry", foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "userId",
                onDelete = CASCADE)
})
public class TransactionEntry {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transactionId")
    private long id;

    @ColumnInfo(name = "userId")
    private long userId;

    @ColumnInfo(name = "timestamp")
    private LocalDateTime timestamp;

    private TransactionType transactionType;

    @ColumnInfo(name = "currencyFrom")
    private Currency currencyFrom;

    @ColumnInfo(name = "amount")
    private BigDecimal amount;

    @ColumnInfo(name = "currencyTo")
    private Currency currencyTo;

    public TransactionEntry(long userId,
                            LocalDateTime timestamp,
                            TransactionType transactionType,
                            Currency currencyFrom,
                            BigDecimal amount,
                            Currency currencyTo) {
        this.userId = userId;
        this.timestamp = timestamp;
        this.transactionType = transactionType;
        this.currencyFrom = currencyFrom;
        this.amount = amount;
        this.currencyTo = currencyTo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Currency getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(Currency currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(Currency currencyTo) {
        this.currencyTo = currencyTo;
    }
}
