package com.kozitski.xml.entity;

public class Tariff {
    private String name;
    private OperatorName operatorName;
    private double smsPrice;
    private TariffParameters tariffParameters;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public OperatorName getOperatorName() {
        return operatorName;
    }
    public void setOperatorName(OperatorName operatorName) {
        this.operatorName = operatorName;
    }
    public double getSmsPrice() {
        return smsPrice;
    }
    public void setSmsPrice(double smsPrice) {
        this.smsPrice = smsPrice;
    }
    public TariffParameters getTariffParameters() {
        return tariffParameters;
    }
    public void setTariffParameters(TariffParameters tariffParameters) {
        this.tariffParameters = tariffParameters;
    }

    @Override
    public String toString() {
        return  "Name: " + name + "\n" +
                "OperatorName: " + operatorName + "\n" +
                "smsPrice: " + smsPrice + "\n" +
                "TariffParameters: " + tariffParameters + "\n";

    }

}
