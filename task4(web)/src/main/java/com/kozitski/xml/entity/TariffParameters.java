package com.kozitski.xml.entity;

public class TariffParameters {
    private TarifficationType tarifficationType;
    private double payForConnection;
    private String favouriteNumber;

    public TarifficationType getTarifficationType() {
        return tarifficationType;
    }

    public void setTarifficationType(TarifficationType tarifficationType) {
        this.tarifficationType = tarifficationType;
    }

    public double getPayForConnection() {
        return payForConnection;
    }
    public void setPayForConnection(double payForConnection) {
        this.payForConnection = payForConnection;
    }
    public String getFavouriteNumber() {
        return favouriteNumber;
    }
    public void setFavouriteNumber(String favouriteNumber) {
        this.favouriteNumber = favouriteNumber;
    }

    @Override
    public String toString() {
        return
                "\n\tTarificationType: " + tarifficationType + "\n" +
                "\tPayForConnection: " + payForConnection + "\n" +
                "\tFavouriteNumber: " + favouriteNumber;

    }

}
