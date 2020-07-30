package com.example.blooddonationapp;

public class Campaigns {
    private double latitude,longitude;
    private String Hospital_name,Type;
    private int id,no_units_needed;
    private String location;
    private String workHours,workdays;
    public Campaigns( String Hospital_name,String Type,double latitude,double longitude){
    setLatitude(latitude);
    setLongitude(longitude);
    setHospital_name(Hospital_name);
    setType(Type);
    }
    public Campaigns( String Hospital_name,String Type,int no_units_needed,String location,double latitude,double longitude, String workHours,String workdays){
        setLatitude(latitude);
        setLongitude(longitude);
        setHospital_name(Hospital_name);
        setType(Type);
        setNo_units_needed(no_units_needed);
        setLocation(location);
        setWorkdays(workdays);
        setWorkHours(workHours);

    }
    public Campaigns( String Hospital_name,String Type,int no_units_needed,String location){
        setNo_units_needed(no_units_needed);
        setLocation(location);
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    public String getWorkdays() {
        return workdays;
    }

    public void setWorkdays(String workdays) {
        this.workdays = workdays;
    }


}
