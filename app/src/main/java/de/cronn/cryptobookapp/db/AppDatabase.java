package de.cronn.cryptobookapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import de.cronn.cryptobookapp.db.converter.Converters;
import de.cronn.cryptobookapp.db.dao.TransactionEntryDao;
import de.cronn.cryptobookapp.db.dao.UserDao;
import de.cronn.cryptobookapp.db.dao.WalletDao;
import de.cronn.cryptobookapp.db.model.TransactionEntry;
import de.cronn.cryptobookapp.db.model.User;
import de.cronn.cryptobookapp.db.model.Wallet;

@Database(entities = {User.class, Wallet.class, TransactionEntry.class}, exportSchema = false, version = 4)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "crypto_app_db";
    private static AppDatabase instance;

    static synchronized void initialize(Context context) {
        instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    static synchronized AppDatabase getInstance() {
        return instance;
    }

    protected abstract UserDao userDao();

    protected abstract WalletDao walletDao();

    protected abstract TransactionEntryDao transactionEntryDao();
}
