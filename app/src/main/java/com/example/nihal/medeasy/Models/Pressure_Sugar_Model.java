package com.example.nihal.medeasy.Models;

public class Pressure_Sugar_Model {
    String low,high,sugar;

    public Pressure_Sugar_Model(String low, String high, String sugar) {
        this.low = low;
        this.high = high;
        this.sugar = sugar;
    }

    public Pressure_Sugar_Model() {
    }

    public String getLow() {
        return low;
    }

    public String getHigh() {
        return high;
    }

    public String getSugar() {
        return sugar;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }
}
