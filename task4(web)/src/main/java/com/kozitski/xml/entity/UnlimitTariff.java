package com.kozitski.xml.entity;

public class UnlimitTariff extends Tariff {
    private boolean hasInternalCall;
    private boolean hasExternalCall;
    private boolean hasInternet;
    private double payroll;


    public void setHasInternalCall(boolean hasInternalCall) {
        this.hasInternalCall = hasInternalCall;
    }
    public void setHasExternalCall(boolean hasExternalCall) {
        this.hasExternalCall = hasExternalCall;
    }
    public void setHasInternet(boolean hasInternet) {
        this.hasInternet = hasInternet;
    }
    public void setPayroll(double payroll) {
        this.payroll = payroll;
    }

    public boolean isHasInternalCall() {
        return hasInternalCall;
    }
    public boolean isHasExternalCall() {
        return hasExternalCall;
    }
    public boolean isHasInternet() {
        return hasInternet;
    }
    public double getPayroll() {
        return payroll;
    }

    @Override
    public String toString() {
        return  "UNLIMIT TARIFF\n" +
                super.toString() +
                "HasInternalCall: " + hasInternalCall + "\n" +
                "HasExternalCall: " + hasExternalCall + "\n" +
                "HasInternet: " + hasInternet + "\n"+
                "Payroll: " + payroll + "\n";

    }

}
