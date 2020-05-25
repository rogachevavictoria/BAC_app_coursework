package com.example.android.bactrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.MyViewHolder> {

    private Context context;
    private List<DrinkItem> drinksList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView drink_name;
        public TextView drink_amount;
        public TextView drink_percent;
        public TextView drink_cost;
        public MyViewHolder(View view) {
            super(view);
            drink_name = view.findViewById(R.id.drink_name);
            drink_amount = view.findViewById(R.id.drink_amount);
            drink_percent = view.findViewById(R.id.drink_percent);
            drink_cost = view.findViewById(R.id.drink_cost);
        }
    }


    public DrinksAdapter(Context context, List<DrinkItem> notesList) {
        this.context = context;
        this.drinksList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drink_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DrinkItem drink = drinksList.get(position);

        holder.drink_name.setText(drink.getName());
        holder.drink_amount.setText((int) drink.getAmount());
        holder.drink_percent.setText((int) drink.getPercent());
        holder.drink_cost.setText((int) drink.getCost());

    }

    @Override
    public int getItemCount() {
        return drinksList.size();
    }

}
