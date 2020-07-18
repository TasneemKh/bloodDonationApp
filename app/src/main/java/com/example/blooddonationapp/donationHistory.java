package com.example.blooddonationapp;
import java.util.Date;

public class donationHistory {
    private String donationType,placeOfDonation;
    //private String dateOfDonation;
    private Date dateOfDonation;
    private int id;
/*public donationHistory( String donationType,String placeOfDonation,String dateOfDonation){
    setDonationType(donationType);
    setPlaceOfDonation(placeOfDonation);
   // setDateOfDonation(dateOfDonation);

}*/
public donationHistory( String donationType,String placeOfDonation,Date dateOfDonation){
    setDonationType(donationType);
    setPlaceOfDonation(placeOfDonation);
    setDateOfDonation(dateOfDonation);

}
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

   /* public String getDateOfDonation() {
        return dateOfDonation;
    }

    public void setDateOfDonation(String dateOfDonation) {
        this.dateOfDonation = dateOfDonation;
    }

*/
   public Date getDateOfDonation() {
       return dateOfDonation;
   }

    public void setDateOfDonation(Date dateOfDonation) {
        this.dateOfDonation = dateOfDonation;
    }

}