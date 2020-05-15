package com.example.android.bactrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.DrinkViewHolder>{

    private ArrayList<Drink> drinks;
    private onItemClickListener mListener;

    public RVAdapter( ArrayList<Drink> pdrinks){
        drinks = pdrinks;
    }

    public interface onItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        mListener = listener;
    }

    public static class DrinkViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public TextView drinkName;
        public TextView drinkPercent;
        public TextView drinkMl;
        public RelativeLayout parentLayout;

        public DrinkViewHolder(View itemView, final onItemClickListener listener) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            drinkName = itemView.findViewById(R.id.drinkName);
            drinkPercent = itemView.findViewById(R.id.drinkPercent);
            drinkMl = itemView.findViewById(R.id.drinkMl);
            parentLayout = itemView.findViewById(R.id.main_add);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_drink_item, viewGroup, false);
        DrinkViewHolder dvh = new DrinkViewHolder(v, mListener);
        return dvh;
    }

    @Override
    public void onBindViewHolder(DrinkViewHolder drinkViewHolder, int i) {
        Drink currentDrink = drinks.get(i);
        drinkViewHolder.drinkName.setText(currentDrink.getName());
        drinkViewHolder.drinkPercent.setText(String.valueOf(currentDrink.getPercent()));
        drinkViewHolder.drinkMl.setText(String.valueOf(currentDrink.getMl()));
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public int getItemCount() {
        return drinks.size();
    }

}