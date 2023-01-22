package de.cronn.cryptobookapp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import de.cronn.cryptobookapp.db.model.TransactionEntry;

@Dao
public interface TransactionEntryDao {

    @Insert
    long insertEntry(TransactionEntry transactionEntry);

    @Query("SELECT * FROM transactionEntry where userId = :userId")
    List<TransactionEntry> findByUserId(long userId);
}
