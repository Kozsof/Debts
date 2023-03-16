package com.example.debts.database;

import com.example.debts.database.model.Debt;

import java.util.List;

public interface DebtsDatabase {
    void save(Debt dept);
    Debt get(int id);
    public List<Debt> getAll();
}
