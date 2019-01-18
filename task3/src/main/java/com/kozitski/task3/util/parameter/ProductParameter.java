package com.kozitski.task3.util.parameter;

public class ProductParameter{
    private long maxProductId;

    private int nameMinSize;
    private int nameMaxSize;

    private double minPrice;
    private double maxPrice;


    public long getMaxProductId() {
        return maxProductId;
    }

    public int getNameMinSize() {
        return nameMinSize;
    }

    public int getNameMaxSize() {
        return nameMaxSize;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxProductId(long maxProductId) {
        this.maxProductId = maxProductId;
    }

    public void setNameMinSize(int nameMinSize) {
        this.nameMinSize = nameMinSize;
    }

    public void setNameMaxSize(int nameMaxSize) {
        this.nameMaxSize = nameMaxSize;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }
}

