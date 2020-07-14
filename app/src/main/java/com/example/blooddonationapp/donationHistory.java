package com.example.blooddonationapp;
import java.util.Date;

public class donationHistory {
    private String donationType,placeOfDonation;
    private Date dateOfDonation;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public String getPlaceOfDonation() {
        return placeOfDonation;
    }

    public void setPlaceOfDonation(String placeOfDonation) {
        this.placeOfDonation = placeOfDonation;
    }

    public Date getDateOfDonation() {
        return dateOfDonation;
    }

    public void setDateOfDonation(Date dateOfDonation) {
        this.dateOfDonation = dateOfDonation;
    }



}