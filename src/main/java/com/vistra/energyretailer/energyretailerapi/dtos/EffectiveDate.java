package com.vistra.energyretailer.energyretailerapi.dtos;

public class EffectiveDate {

    private String date;
    private String time;

    public String getFullDate() {
        return date + " " + time;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
