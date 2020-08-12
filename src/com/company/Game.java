package com.company;

public class Game {
    private int GID;
    private String date;
    private String location;
    private String sport = "Basketball";
    private String distribution;
    private int fancount;

    public Game(int GID, String date, String location, String distribution, int fancount) {
        this.GID = GID;
        this.date = date;
        this.location = location;
        this.distribution = distribution;
        this.fancount = fancount;
    }

    public int getGID() {
        return GID;
    }

    public void setGID(int GID) {
        this.GID = GID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public int getFancount() {
        return fancount;
    }

    public void setFancount(int fancount) {
        this.fancount = fancount;
    }
}
