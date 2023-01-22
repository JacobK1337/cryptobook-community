package de.cronn.cryptobookapp.db;

import android.content.Context;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import de.cronn.cryptobookapp.db.model.TransactionEntry;
import de.cronn.cryptobookapp.db.model.User;
import de.cronn.cryptobookapp.db.model.UserWithWallets;
import de.cronn.cryptobookapp.db.model.Wallet;

public class DatabaseFacade {
    private static AppDatabase database;

    public void initializeDatabase(Context context) {
        if (database == null) {
            AppDatabase.initialize(context);
            database = AppDatabase.getInstance();
        }
    }

    public long insertUser(User user) {
        try {
            return CompletableFuture.supplyAsync(
                    () -> database.userDao().insertUser(user)).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Error inserting User");
    }

    public long insertWallet(Wallet wallet) {
        try {
            return CompletableFuture.supplyAsync(
                    () -> database.walletDao().insert(wallet)).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Error inserting Wallet");
    }

    public long insertTransactionEntry(TransactionEntry transactionEntry) {
        try {
            return CompletableFuture.supplyAsync(
                    () -> database.transactionEntryDao().insertEntry(transactionEntry)).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Error inserting TransactionEntry");
    }

    public UserWithWallets findByUsername(String username) {
        try {
            return CompletableFuture.supplyAsync(
                    () -> database.userDao().findByUsername(username)).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Error finding User");
    }

    public UserWithWallets findByUserId(long userId) {
        try {
            return CompletableFuture.supplyAsync(
                    () -> database.userDao().findById(userId)).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Error finding User");
    }

    public void updateWallet(long walletId, BigDecimal balance) {
        try {
            CompletableFuture.runAsync(
                    () -> database.walletDao().updateWallet(walletId, balance.toString())).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
