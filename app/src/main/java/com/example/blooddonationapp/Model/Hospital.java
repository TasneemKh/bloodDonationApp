package com.example.blooddonationapp.Model;

public class Hospital {
    private String Hospital_name;
    private String Type;
    private int latitude;
    private String location;
    private int longitude;
    private int no_units_needed;
    private String workDays;
    private String workHours;

    public Hospital() {

    }

    public Hospital(String hospital_name, String type, int latitude, String location, int longitude, int no_units_needed, String workDays, String workHours) {
        Hospital_name = hospital_name;
        Type = type;
        this.latitude = latitude;
        this.location = location;
        this.longitude = longitude;
        this.no_units_needed = no_units_needed;
        this.workDays = workDays;
        this.workHours = workHours;
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

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getNo_units_needed() {
        return no_units_needed;
    }

    public void setNo_units_needed(int no_units_needed) {
        this.no_units_needed = no_units_needed;
    }

    public String getWorkDays() {
        return workDays;
    }

    public void setWorkDays(String workDays) {
        this.workDays = workDays;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }
}
