package com.example.liran.takeogo.models.entities;

/**
 * Created by liran on 08/11/2017.
 */

public class Branch {
    private long idBranch;
    private int numParking;
    private String city;
    private String street;
    private int numApart;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumApart() {
        return numApart;
    }

    public void setNumApart(int numApart) {
        this.numApart = numApart;
    }

    public int getNumParking() {return numParking;}

    public void setNumParking(int numParking) {this.numParking = numParking;}

    public long getIdBranch() {return idBranch;}

    public void setIdBranch(long idBranch) {this.idBranch = idBranch;}
}
