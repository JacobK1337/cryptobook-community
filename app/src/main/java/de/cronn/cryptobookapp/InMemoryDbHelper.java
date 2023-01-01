package de.cronn.cryptobookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InMemoryDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "crypto_book_community_db";
    public static final int DATABASE_VERSION = 1;

    public InMemoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT)";
        db.execSQL(createTableSql);

        ContentValues values = new ContentValues();
        values.put("id", 1);
        values.put("name", "Alice");
        db.insert("users", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // handle database upgrades here
    }

}
