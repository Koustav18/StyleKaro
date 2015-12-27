package com.example.koroy.myapplication.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by koroy on 11/27/2015.
 */
public class SalonModel implements Serializable{
    private int salonLogo;
    private String salonName;
    private String salonAddress;
    private double rating;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getSalonAddress() {
        return salonAddress;
    }

    public void setSalonAddress(String salonAddress) {
        this.salonAddress = salonAddress;
    }

    public int getSalonLogo() {
        return salonLogo;
    }

    public void setSalonLogo(int salonLogo) {
        this.salonLogo = salonLogo;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }
}
