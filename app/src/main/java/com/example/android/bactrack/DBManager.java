package com.example.android.bactrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }
    public long insert(String drink_name, double drink_amount, double alc_content,double cost,double energy) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DatabaseHelper.COL_DRINK_NAME, drink_name);
        initialValues.put(DatabaseHelper.COL_AMOUNT, drink_amount);
        initialValues.put(DatabaseHelper.COL_ALC_CONTENT, alc_content);
        initialValues.put(DatabaseHelper.COL_COST, cost);
        initialValues.put(DatabaseHelper.COL_ENERGY, energy);
        return database.insert(DatabaseHelper.DRINKS_TABLE, null, initialValues);
    }
    private void loadDrinks(){
        try {
            insert("Small Beer",330,5,20,92);
            insert("Large Beer",500,5,25,140);
            insert("Glass of Wine",200,11,20,123);
            insert("Single Shot",20,40,20,45);
            insert("Double Shot",40,40,30,90);
        }catch (Exception e){};
    }
    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper.COL_ID,DatabaseHelper.COL_ID,DatabaseHelper.COL_DRINK_NAME ,
                DatabaseHelper.COL_AMOUNT,DatabaseHelper.COL_ALC_CONTENT,DatabaseHelper.COL_COST, DatabaseHelper.COL_ENERGY };
        Cursor cursor = database.query(DatabaseHelper.DRINKS_TABLE, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public void update(int id, String drink_name, double drink_amount, double alc_content,double cost,double energy) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DatabaseHelper.COL_DRINK_NAME, drink_name);
        initialValues.put(DatabaseHelper.COL_AMOUNT, drink_amount);
        initialValues.put(DatabaseHelper.COL_ALC_CONTENT, alc_content);
        initialValues.put(DatabaseHelper.COL_COST, cost);
        initialValues.put(DatabaseHelper.COL_ENERGY, energy);
        int i = database.update(DatabaseHelper.DRINKS_TABLE, initialValues, DatabaseHelper.COL_ID + " = '" + id+"'", null);
    }
    public void delete(long id) {
        database.delete(DatabaseHelper.DRINKS_TABLE, DatabaseHelper.COL_ID + "=" + id, null);
    }


}