package com.kozitski.task3.entity;

import com.kozitski.task3.exception.LogisticBaseException;
import com.kozitski.task3.service.LogisticBaseActivity;
import com.kozitski.task3.service.WagonActivity;
import com.kozitski.task3.util.generator.WagonIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Wagon implements Callable<Integer>, Comparable<Wagon>{
    private static final Logger LOGGER = LogManager.getLogger(Wagon.class);
    private long wagonId = WagonIdGenerator.generateId();
    private List<Product> products;

    private LogisticBaseActivity activity;
    private LogisticBase base;

    private boolean isEnded = false;
    public static final int NUMBER_OF_GIVE_PRODUCTS = 10;
    private static final int NUMBER_OF_TRIPS = 1;
    private static final int TIME_OF_WAIT = 100;
    private int numberOfDoneTrips;
    private int numberOfTransportedProduct;

    public Wagon() {
        base = LogisticBase.getInstance();
        activity = WagonActivity.DURING_TRIP;
        products = new ArrayList<>();
    }

    @Override
    public Integer call() throws LogisticBaseException {

        while (numberOfDoneTrips < NUMBER_OF_TRIPS){

            try {
                TimeUnit.MILLISECONDS.sleep(TIME_OF_WAIT);
            }
            catch (InterruptedException e) {
                throw new LogisticBaseException("Error in waiting period", e);
            }

            numberOfTransportedProduct += activity.activity(this);
            changeActivity();
        }

        isEnded = true;

        Thread.currentThread().interrupt();

        return numberOfTransportedProduct;

    }

    private void changeActivity(){

        if (activity == WagonActivity.DURING_TRIP){
            activity = WagonActivity.TRY_CALL_BASE;
        }

        else if(activity == WagonActivity.TRY_CALL_BASE){
            Random random = new Random();
            if(random.nextBoolean()){
                activity = WagonActivity.GET_PRODUCT;
            }
            else {
                activity = WagonActivity.GIVE_PRODUCT;
            }
        }

        else if(activity == WagonActivity.GIVE_PRODUCT || activity == WagonActivity.GET_PRODUCT){
            numberOfDoneTrips++;

            activity = WagonActivity.DURING_TRIP;
        }

    }

    @Override
    public int compareTo(Wagon o) {
        int counterOfPerishable1 = 0;
        int counterOfPerishable2 = 0;

        for(Product product : products){
            if(product.getType().getRate() == 1){
                counterOfPerishable1++;
            }
        }

        for(Product product : ((Wagon) o).products){
            if(product.getType().getRate() == 1){
                counterOfPerishable2++;
            }
        }

        return counterOfPerishable2 - counterOfPerishable1;
    }

    public boolean add(Product product) {
        return this.products.add(product);
    }
    public boolean addAll(Collection<? extends Product> c) {
        return products.addAll(c);
    }
    public Product getProduct(int index) {
        return products.get(index);
    }
    public int getProductsSize() {
        return products.size();
    }
    public LogisticBase getBase() {
        return base;
    }
    public long getWagonId() {
        return wagonId;
    }
    public boolean getIsEnded() {
        return isEnded;
    }

    @Override
    public String toString() {
        return "Wagon : id-" +
                wagonId +
                ", " +
                products;
    }

}
