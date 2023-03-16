package com.example.debts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.debts.database.DebtsDatabase;
import com.example.debts.database.model.Debt;
import com.example.debts.database.room.Database;
import com.example.debts.database.room.DebtDAO;
import com.example.debts.database.room.DebtEntity;
import com.example.debts.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        ActivityMainBinding binding;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            DebtDAO debtDAO = App.getDatabase().debtDAO();


            List<DebtEntity> data = new ArrayList<>();
            new Thread(() -> {
                List<DebtEntity> debts = debtDAO.getAll();
                runOnUiThread(() -> {
                    data.addAll(debts);
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                    binding.recyclerView.setAdapter(new MyDebtsAdapter(data));
                });
            }).start();

            binding.add.setOnClickListener(view -> {
                DebtEntity debt = new DebtEntity("Name", (int) (Math.random() * 1000));
                data.add(debt);
                new Thread(() -> {
                    debtDAO.save(debt);
                }).start();
                binding.recyclerView.getAdapter().notifyItemChanged(data.size() - 1);
            });
            binding.delete.setOnClickListener(view -> {
                if (data.size() >=1){
                data.remove(data.size() - 1);
                new Thread(() -> {
                    debtDAO.delete();
                }).start();
                binding.recyclerView.getAdapter().notifyItemChanged(data.size());}
            });

        }

    }