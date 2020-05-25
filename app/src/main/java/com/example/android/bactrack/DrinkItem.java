package com.example.android.bactrack;

public class DrinkItem {
    public static final String DRINKS_TABLE = "DRINKS";

    public static final String COL_ID = "ID";
    public static final String COL_DRINK_NAME = "NAME";
    public static final String COL_AMOUNT = "AMOUNT";
    public static final String COL_ALC_CONTENT = "ALCOHOL CONTENT";
    public static final String COL_COST = "COST";

    private static final String DATABASE_NAME = "BAC_DATABASE";

    private int id;
    private String drink_name;
    private double drink_amount;
    private double drink_percent;
    private double drink_cost;


    // Create table SQL query
     static final String DRINKS_TABLE_CREATE =
            "CREATE TABLE " + DRINKS_TABLE +
                    " (" + COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COL_DRINK_NAME + ", " +
                    COL_AMOUNT + ", " +
                    COL_ALC_CONTENT + ", " +
                    COL_COST+ ", " + ")";
    public DrinkItem() {
    }

    public DrinkItem(int id, String drink_name, double drink_amount, double drink_percent, double drink_cost) {
        this.id = id;
        this.drink_name = drink_name;
        this.drink_amount = drink_amount;
        this.drink_percent = drink_percent;
        this.drink_cost = drink_cost;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }

    public String getName() {
        return drink_name;
    }
    public void setName(String drink_name) {
        this.drink_name = drink_name;
    }

    public double getAmount() {
        return drink_amount;
    }
    public void setAmount(double drink_amount) { this.drink_amount = drink_amount; }

    public double getPercent() {
        return drink_percent;
    }
    public void setPercent(double drink_percent) { this.drink_percent = drink_percent; }

    public double getCost() {
        return drink_cost;
    }
    public void setCost(double drink_cost) { this.drink_cost = drink_cost; }
}
