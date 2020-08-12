package com.example.blooddonationapp.Model;

public class PublicRequest extends Request{

    public PublicRequest() {
    }

    public PublicRequest(String date, String time, String bloodType, String hospital, String type, String userId, String id,String userName) {
        super(date, time, bloodType, hospital, type, userId, id,userName);
    }
}
