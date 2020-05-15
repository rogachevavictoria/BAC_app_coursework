package com.example.android.bactrack;

public class Drink {
    private String name;
    private double percent;
    private double ml;

    public Drink(String name,  double percent, double ml) {
        this.setName(name);
        this.setPercent(percent);
        this.setMl(ml);
    }

    public void changeName(String text){
        setName(text);
    }
    public void changePercent(double percent){
        setPercent(percent);
    }
    public void changeMl(double ml){
        setMl(ml);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public double getMl() {
        return ml;
    }

    public void setMl(double ml) {
        this.ml = ml;
    }
}