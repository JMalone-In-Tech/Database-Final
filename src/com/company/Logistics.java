package com.company;

public class Logistics {
    private int GID;
    private int TID;
    private double flightCost;
    private double rentalCost;
    private double hotelCost;

    public Logistics(int GID, int TID, double flightCost, double rentalCost, double hotelCost) {
        this.GID = GID;
        this.TID = TID;
        this.flightCost = flightCost;
        this.rentalCost = rentalCost;
        this.hotelCost = hotelCost;
    }

    public int getGID() {
        return GID;
    }

    public void setGID(int GID) {
        this.GID = GID;
    }

    public int getTID() {
        return TID;
    }

    public void setTID(int TID) {
        this.TID = TID;
    }

    public double getFlightCost() {
        return flightCost;
    }

    public void setFlightCost(double flightCost) {
        this.flightCost = flightCost;
    }

    public double getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(double rentalCost) {
        this.rentalCost = rentalCost;
    }

    public double getHotelCost() {
        return hotelCost;
    }

    public void setHotelCost(double hotelCost) {
        this.hotelCost = hotelCost;
    }
}
