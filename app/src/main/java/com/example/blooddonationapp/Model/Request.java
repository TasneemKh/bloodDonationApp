package com.example.blooddonationapp.Model;

public class Request {
    private String Date;
    private String Time;
    private String bloodType;
    private String Hospital;
    private String Type;
    private String userId;
    private String id;

    public Request() {
    }

    public Request(String date, String time, String bloodType, String hospital, String type, String userId, String id) {
        Date = date;
        Time = time;
        this.bloodType = bloodType;
        Hospital = hospital;
        Type = type;
        this.userId = userId;
        this.id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getHospital() {
        return Hospital;
    }

    public void setHospital(String hospital) {
        Hospital = hospital;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
