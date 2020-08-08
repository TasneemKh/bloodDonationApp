package com.example.blooddonationapp.Model;

public class Drug {
    String drugsName;
    int drugsId;
    int drugsTime;

    public Drug() {
    }

    public Drug(String drugsName, int drugsId, int drugsTime) {
        this.drugsName = drugsName;
        this.drugsId = drugsId;
        this.drugsTime = drugsTime;
    }

    public String getDrugsName() {
        return drugsName;
    }

    public void setDrugsName(String drugsName) {
        this.drugsName = drugsName;
    }

    public int getDrugsId() {
        return drugsId;
    }

    public void setDrugsId(int drugsId) {
        this.drugsId = drugsId;
    }

    public int getDrugsTime() {
        return drugsTime;
    }

    public void setDrugsTime(int drugsTime) {
        this.drugsTime = drugsTime;
    }
}
