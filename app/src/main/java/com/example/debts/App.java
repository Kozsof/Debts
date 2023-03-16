package com.example.debts;

import android.app.Application;

import androidx.room.Room;

import com.example.debts.database.room.Database;

public class App extends Application {
    private static App instance;
    private static Database database;

    public static App getInstance() {
        return instance;
    }
    public static Database getDatabase() {
        return database;
    }

    @Override
    public void onCreate() {
        instance = this;
        database = Room.databaseBuilder(this,Database.class,"database").build();
        super.onCreate();
    }
}
