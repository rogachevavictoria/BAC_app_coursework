package com.example.android.bactrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/*public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DRINKS_TABLE = "DRINKS";

    public static final String COL_ID = "ID";
    public static final String COL_DRINK_NAME = "NAME";
    public static final String COL_AMOUNT = "AMOUNT";
    public static final String COL_ALC_CONTENT = "ALCOHOL CONTENT";
    public static final String COL_COST = "COST";
    public static final String COL_ENERGY = "ENERGY";

    private static final String DATABASE_NAME = "BAC_DATABASE";

    static final int DB_VERSION = 1;

    private static final String DRINKS_TABLE_CREATE =
            "CREATE TABLE " + DRINKS_TABLE +
                    " (" + COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COL_DRINK_NAME + ", " +
                    COL_AMOUNT + ", " +
                    COL_ALC_CONTENT + ", " +
                    COL_COST+ ", " +
                    COL_ENERGY + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DRINKS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DRINKS_TABLE);
        onCreate(db);
    }
}
package info.androidhive.sqlite.database;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import java.util.ArrayList;
        import java.util.List;

        import info.androidhive.sqlite.database.model.Note;

*
 * Created by ravi on 15/03/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "BAC_DATABASE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(DrinkItem.DRINKS_TABLE_CREATE);
        loadDrinks();
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DrinkItem.DRINKS_TABLE);

        // Create tables again
        onCreate(db);
    }
    public long insertDrink(String drink_name, double drink_amount, double alc_content,double cost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DrinkItem.COL_DRINK_NAME, drink_name);
        values.put(DrinkItem.COL_AMOUNT, drink_amount);
        values.put(DrinkItem.COL_ALC_CONTENT, alc_content);
        values.put(DrinkItem.COL_COST, cost);
        long id = db.insert(DrinkItem.DRINKS_TABLE, null, values);
        db.close();
        return id;
    }
    private void loadDrinks(){
        insertDrink("Small Beer",330,5,20);
        insertDrink("Large Beer",500,5,25);
        insertDrink("Glass of Wine",200,11,20);
        insertDrink("Single Shot",20,40,20);
        insertDrink("Double Shot",40,40,30);
    }

    public DrinkItem getDrink(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DrinkItem.DRINKS_TABLE,
                new String[]{DrinkItem.COL_ID,DrinkItem.COL_ID,DrinkItem.COL_DRINK_NAME ,
                        DrinkItem.COL_AMOUNT,DrinkItem.COL_ALC_CONTENT,DrinkItem.COL_COST},
                DrinkItem.COL_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        DrinkItem drink = new DrinkItem(
                cursor.getInt(cursor.getColumnIndex(DrinkItem.COL_ID)),
                cursor.getString(cursor.getColumnIndex(DrinkItem.COL_DRINK_NAME)),
                cursor.getDouble(cursor.getColumnIndex(DrinkItem.COL_AMOUNT)),
                cursor.getDouble(cursor.getColumnIndex(DrinkItem.COL_ALC_CONTENT)),
                cursor.getDouble(cursor.getColumnIndex(DrinkItem.COL_COST)));
        // close the db connection
        cursor.close();
        return drink;
    }

    public List<DrinkItem> getAllNotes() {
        List<DrinkItem> drinks = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DrinkItem.DRINKS_TABLE + " ORDER BY " +
                DrinkItem.COL_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DrinkItem drink = new DrinkItem();
                drink.setId(cursor.getInt(cursor.getColumnIndex(DrinkItem.COL_ID)));
                drink.setName(cursor.getString(cursor.getColumnIndex(DrinkItem.COL_DRINK_NAME)));
                drink.setAmount(cursor.getDouble(cursor.getColumnIndex(DrinkItem.COL_AMOUNT)));
                drink.setAmount(cursor.getDouble(cursor.getColumnIndex(DrinkItem.COL_ALC_CONTENT)));
                drink.setAmount(cursor.getDouble(cursor.getColumnIndex(DrinkItem.COL_COST)));
                drinks.add(drink);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return drinks;
    }

    public int getDrinksCount() {
        String countQuery = "SELECT  * FROM " + DrinkItem.DRINKS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    public int updateDrink(DrinkItem drink) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DrinkItem.COL_DRINK_NAME, drink.getName());
        values.put(DrinkItem.COL_AMOUNT, drink.getAmount());
        values.put(DrinkItem.COL_ALC_CONTENT, drink.getPercent());
        values.put(DrinkItem.COL_COST, drink.getCost());

        // updating row
        return db.update(DrinkItem.DRINKS_TABLE, values, DrinkItem.COL_ID + " = ?",
                new String[]{String.valueOf(drink.getId())});
    }

    public void deleteDrink(DrinkItem drink) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DrinkItem.DRINKS_TABLE, DrinkItem.COL_ID + " = ?",
                new String[]{String.valueOf(drink.getId())});
        db.close();
    }
}

