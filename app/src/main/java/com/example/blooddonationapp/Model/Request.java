package com.example.blooddonationapp.Model;

public class Request {
    private String Date;
    private String Time;
    private String donType;
    private String Hospital;
    private String Type;
    private String userId;
    private String id;
    private String userName;



    public Request() {
    }

   /* public Request(String date, String time, String bloodType, String hospital, String type, String userId, String id,String userName) {
        Date = date;
        Time = time;
        this.bloodType = bloodType;
        Hospital = hospital;
        Type = type;
        this.userId = userId;
        this.id = id;
        this.userName=userName;
    }*/
   public Request(String date, String time, String donType, String hospital, String type, String userId) {
       Date = date;
       Time = time;
       this.donType = donType;
       Hospital = hospital;
       Type = type;
       this.userId = userId;
//        this.id = id;
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

    public String getDonType() {
        return donType;
    }

    public void setDonType(String donType) {
        this.donType = donType;
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
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
