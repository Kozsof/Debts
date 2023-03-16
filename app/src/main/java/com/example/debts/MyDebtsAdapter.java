package com.example.debts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.debts.database.model.Debt;
import com.example.debts.database.room.DebtEntity;
import com.example.debts.databinding.ItemDebtBinding;

import java.util.List;

public class MyDebtsAdapter extends RecyclerView.Adapter<MyDebtsAdapter.MyHolder> {

    List<DebtEntity> data;

    public MyDebtsAdapter(List<DebtEntity> data) {
        this.data = data;
    }

    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDebtBinding binding = ItemDebtBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.name.setText(data.get(position).name);
        holder.money.setText(String.valueOf(data.get(position).money));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView money;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            money = itemView.findViewById(R.id.money);
        }
    }
}
