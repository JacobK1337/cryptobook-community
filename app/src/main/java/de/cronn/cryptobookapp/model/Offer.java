package de.cronn.cryptobookapp.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.price.Currency;

@Entity(tableName = "offer", foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "userId",
                onDelete = CASCADE)
})
public class Offer {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(index = true)
    private int userId;

    @ColumnInfo(name = "currency")
    private Currency currency;

    @ColumnInfo(name = "amount")
    private BigDecimal amount;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
