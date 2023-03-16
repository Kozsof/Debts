package com.example.debts.database.room;

import android.view.LayoutInflater;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.debts.database.model.Debt;

import java.util.List;

@Dao
public interface DebtDAO {
    @Query("SELECT * FROM DebtEntity")
    List<DebtEntity> getAll();

    @Insert
    void save(DebtEntity debt);

    @Query("SELECT * FROM DebtEntity WHERE id = :id")
    Debt getId(int id);



}
