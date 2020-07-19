package com.example.blooddonationapp;

public class Campaigns {
    private double latitude,longitude;
    private String Hospital_name,Type;
    private int id,no_units_needed;
public Campaigns( String Hospital_name,String Type,double latitude,double longitude){
    setLatitude(latitude);
    setLongitude(longitude);
    setHospital_name(Hospital_name);
    setType(Type);
    }
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getHospital_name() {
        return Hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        Hospital_name = hospital_name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNo_units_needed() {
        return no_units_needed;
    }

    public void setNo_units_needed(int no_units_needed) {
        this.no_units_needed = no_units_needed;
    }
}
