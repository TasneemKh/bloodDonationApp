package com.example.blooddonationapp.Model;

public class PrivateRequest extends Request {
    private String fileNuM;

    public PrivateRequest() {
    }

    public PrivateRequest(String date, String time, String bloodType, String hospital, String type, String userId, String id,String userName, String fileNuM) {
        super(date, time, bloodType, hospital, type, userId, id, userName);
        this.fileNuM = fileNuM;
    }

    public String getUserId() {
        return fileNuM;
    }

    public void setUserId(String fileNuM) {
        this.fileNuM = fileNuM;
    }
}
