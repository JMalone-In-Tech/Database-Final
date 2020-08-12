package com.company;

public class Talent {
    private String firstName;
    private String lastName;
    private int id;
    private double payrate;

    public Talent(String firstName, String lastName, int id, double payrate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.id= id;
        this.payrate = payrate;
    }

    public String toString(){
        return this.id+ " " +firstName + " " + this.lastName +" "+ this.payrate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPayrate() {
        return payrate;
    }

    public void setPayrate(double payrate) {
        this.payrate = payrate;
    }
}
