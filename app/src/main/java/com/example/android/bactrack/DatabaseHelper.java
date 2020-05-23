package com.example.android.bactrack;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

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
