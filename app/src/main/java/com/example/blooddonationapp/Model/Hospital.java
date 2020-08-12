package com.example.blooddonationapp.Model;
/*
public class Hospital {
    private String Hospital_name;
    private String Type;
    private int latitude;
    private String location;
    private int longitude;
    private int no_units_needed;
    private String workDays;
    private String workHours;
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
*/

public class Hospital {
    private String Hospital_name;
    private String Type;
    private String createdAt;
    private String endDate;
    private String endtime;
    private String id;
    private long latitude;
    private String location;
    private long longitude;
    private String startDate;
    private String starttime;
    private int no_units_needed;
    private String uid;
    private String workDays;
    private String workHours;



    public Hospital() {
    }

    public Hospital(String hospital_name, String type, String createdAt, String endDate, String endtime, String id, long latitude, String location, long longitude, String startDate, String starttime, int no_units_needed, String uid, String workDays, String workHours) {
        Hospital_name = hospital_name;
        Type = type;
        this.createdAt = createdAt;
        this.endDate = endDate;
        this.endtime = endtime;
        this.id = id;
        this.latitude = latitude;
        this.location = location;
        this.longitude = longitude;
        this.startDate = startDate;
        this.starttime = starttime;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
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
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
