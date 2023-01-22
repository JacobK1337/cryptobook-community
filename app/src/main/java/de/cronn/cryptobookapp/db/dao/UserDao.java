package de.cronn.cryptobookapp.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.cronn.cryptobookapp.db.model.User;
import de.cronn.cryptobookapp.db.model.UserWithWallets;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<UserWithWallets> findAll();

    @Query("SELECT * FROM user where id = :id")
    UserWithWallets findById(long id);

    @Query("SELECT * FROM user where name = :username")
    UserWithWallets findByUsername(String username);

    @Insert
    long insertUser(User user);

    @Update
    void updateUser(User user);
}
