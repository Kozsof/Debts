package com.example.debts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Toast;

import com.example.debts.database.DebtsDatabase;
import com.example.debts.database.model.Debt;
import com.example.debts.database.room.Database;
import com.example.debts.database.room.DebtDAO;
import com.example.debts.database.room.DebtEntity;
import com.example.debts.databinding.ActivityMainBinding;
import com.example.debts.databinding.ItemDebtBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        ActivityMainBinding binding;
        ItemDebtBinding binding2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            binding2 = ItemDebtBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            binding2.getRoot();
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
            binding2.delete2.setOnClickListener(view -> {

                if (data.size() >=1){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Пора покормить кота!", Toast.LENGTH_SHORT);
                    toast.show();
                    data.remove(data.size() - 1);
                new Thread(() -> {
                    debtDAO.delete();
                }).start();
                binding.recyclerView.getAdapter().notifyItemChanged(data.size());}
            });

        }

    }