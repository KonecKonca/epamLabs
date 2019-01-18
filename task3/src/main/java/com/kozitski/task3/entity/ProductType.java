package com.kozitski.task3.entity;

public enum ProductType {
    PERISHABLE(1), NOT_PERISHABLE(0);

    int rate;

    ProductType(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
