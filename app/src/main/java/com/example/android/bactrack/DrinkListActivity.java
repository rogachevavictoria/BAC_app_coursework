package com.example.android.bactrack;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

public class DrinkListActivity extends AppCompatActivity  {

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper.COL_ID,DatabaseHelper.COL_ID,DatabaseHelper.COL_DRINK_NAME ,
            DatabaseHelper.COL_AMOUNT,DatabaseHelper.COL_ALC_CONTENT,DatabaseHelper.COL_COST, DatabaseHelper.COL_ENERGY };

    final int[] to = new int[] { R.id.drinkName, R.id.drinkPercent, R.id.drinkMl };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_emp_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.layout_drink_item, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView nameTextView = (TextView) view.findViewById(R.id.drinkName);
                TextView percentTextView = (TextView) view.findViewById(R.id.drinkPercent);
                TextView amountTextView = (TextView) view.findViewById(R.id.drinkMl);

                String name = nameTextView.getText().toString();
                String percent = percentTextView.getText().toString();
                String amount = amountTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), Dialog.class);
                modify_intent.putExtra("name", name);
                modify_intent.putExtra("percent", percent);
                modify_intent.putExtra("amount", amount);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}