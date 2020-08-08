package com.example.blooddonationapp.Model;

public class User {
    private String phoneNumber;
    private String weight;
    private String bloodType;
    private String identityNumber;
    private String gender;
    private int drugDurations;

    public User() {
    }

    public User(String phoneNumber, String weight, String bloodType, String identityNumber, String gender, int drugDurations) {
        this.phoneNumber = phoneNumber;
        this.weight = weight;
        this.bloodType = bloodType;
        this.identityNumber = identityNumber;
        this.gender = gender;
        this.drugDurations = drugDurations;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getDrugDurations() {
        return drugDurations;
    }

    public void setDrugDurations(int drugDurations) {
        this.drugDurations = drugDurations;
    }
}
