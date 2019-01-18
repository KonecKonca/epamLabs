package com.kozitski.xml.entity;

public class LimitTariff extends Tariff {
    private double internalCallPrice;
    private double externalCallPrice;
    private double mbPrice;


    public double getInternalCallPrice() {
        return internalCallPrice;
    }
    public void setInternalCallPrice(double internalCallPrice) {
        this.internalCallPrice = internalCallPrice;
    }
    public double getExternalCallPrice() {
        return externalCallPrice;
    }
    public void setExternalCallPrice(double externalCallPrice) {
        this.externalCallPrice = externalCallPrice;
    }
    public double getMbPrice() {
        return mbPrice;
    }
    public void setMbPrice(double mbPrice) {
        this.mbPrice = mbPrice;
    }

    @Override
    public String toString() {
        return  "LIMIT TARIFF\n" +
                super.toString() +
                "InternalCallPrice: " + internalCallPrice + "\n" +
                "ExternalCallPrice: " + externalCallPrice + "\n" +
                "MbPrice: " + mbPrice + "\n";

    }

}
