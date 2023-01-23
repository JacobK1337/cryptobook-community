package de.cronn.cryptobookapp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import de.cronn.cryptobookapp.db.model.Wallet;

@Dao
public interface WalletDao {

    @Insert
    long[] insertAll(Wallet... wallets);


    @Query("SELECT * from wallet WHERE userId = :userId")
    List<Wallet> findUserWallets(long userId);

    @Insert
    long insert(Wallet wallet);

    @Query("UPDATE wallet SET balance = :balance WHERE walletId = :id")
    void updateWallet(long id, String balance);
}
