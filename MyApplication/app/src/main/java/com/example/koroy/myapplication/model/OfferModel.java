package com.example.koroy.myapplication.model;

import java.io.Serializable;

/**
 * Created by koroy on 11/27/2015.
 */
public class OfferModel {
    private String offerName;
    private String offerDescription;

    public int getOfferLogo() {
        return offerLogo;
    }

    public void setOfferLogo(int offerLogo) {
        this.offerLogo = offerLogo;
    }

    private int offerLogo;

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }
}
