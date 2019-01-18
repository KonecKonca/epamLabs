package com.kozitski.task3.service;

import com.kozitski.task3.entity.Wagon;
import com.kozitski.task3.exception.LogisticBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class WagonActivity {
    private static final int TIME_OF_TRIP = 3;


public static final LogisticBaseActivity DURING_TRIP = wagon -> {
    try {
        System.out.println("\t" + Thread.currentThread().getName() + ": wagon" + wagon.getWagonId() + " in treap");
        TimeUnit.SECONDS.sleep(TIME_OF_TRIP);

    } catch (InterruptedException e) {
        throw new LogisticBaseException("Error int period of wagon TRIP", e);
    }

    return 0;


};

public static final LogisticBaseActivity TRY_CALL_BASE = wagon -> {

    while (true){
        if(wagon.getBase().offer(wagon)){
            break;
        }
    }

    System.out.println("\t" + Thread.currentThread().getName() + ": wagon" + wagon.getWagonId() + " at base");
    return 0;

};

public static final LogisticBaseActivity GIVE_PRODUCT = wagon -> {

    int result = wagon.getBase().giveProduct();
    System.out.println("\t" + Thread.currentThread().getName() + ": wagon" + wagon.getWagonId() + " is give products");

    return result;
};

public static final LogisticBaseActivity GET_PRODUCT = wagon -> {

    wagon.getBase().getProduct();
    System.out.println("\t" + Thread.currentThread().getName() + ": wagon" + wagon.getWagonId() + " is get products");

    return Wagon.NUMBER_OF_GIVE_PRODUCTS;
};



}
