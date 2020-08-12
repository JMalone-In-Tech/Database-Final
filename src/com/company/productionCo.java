package com.company;

public class productionCo {
    private int CID;
    private String companyName;
    private double productionCost;

    public productionCo(int CID, String companyName, double productionCost) {
        this.CID = CID;
        this.companyName = companyName;
        this.productionCost = productionCost;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(double productionCost) {
        this.productionCost = productionCost;
    }
}
