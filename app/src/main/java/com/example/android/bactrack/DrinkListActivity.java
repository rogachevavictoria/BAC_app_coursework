package com.example.android.bactrack;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;



public class DrinkListActivity extends AppCompatActivity  {

        private DrinksAdapter mAdapter;
        private List<DrinkItem> drinksList = new ArrayList<>();
        private CoordinatorLayout coordinatorLayout;
        private RecyclerView recyclerView;

        private DatabaseHelper db;

        @SuppressLint("WrongViewCast")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list);

            coordinatorLayout = findViewById(R.id.coordinator_layout);
            recyclerView = findViewById(R.id.recycler_view);

            db = new DatabaseHelper(this);

            drinksList.addAll(db.getAllNotes());

            mAdapter = new DrinksAdapter(this, drinksList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
            recyclerView.setAdapter(mAdapter);

            LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
            View view = layoutInflaterAndroid.inflate(R.layout.activity_list, null);

            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                    recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, final int position) {
                }

                @Override
                public void onLongClick(View view, int position) {

                }


            }));
        }

}