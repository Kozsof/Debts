package com.example.debts.database.room;

import androidx.room.RoomDatabase;

import com.example.debts.database.model.Debt;

@androidx.room.Database(entities = {DebtEntity.class}, version = 1)
abstract public class Database extends RoomDatabase {
    public abstract DebtDAO debtDAO();
}
