package com.example.room_datbase.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.room_datbase.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("Select * from user")
    List<User> getAllUser();

    @Query("Select * from user where id = :id")
    User getUserById(int id);

    @Query("Select * from user where firstName like  :firstName or lastName like :lastName")
    List<User> getUserBYfirstNameorLastName(String firstName, String lastName);

    @Insert
    void addUser(User... user);

    @Delete
    void deleteuser(User... user);

    @Update
    void updateUser(User... user);
}
