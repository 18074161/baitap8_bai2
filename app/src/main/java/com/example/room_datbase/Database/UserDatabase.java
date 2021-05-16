package com.example.room_datbase.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.room_datbase.User;

@Database(entities = {User.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static final String DATABSE_NAME="user1.db";
    private static UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,DATABSE_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract UserDAO userDao();
}
