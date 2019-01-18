package com.kozitski.task3.entity;

import com.kozitski.task3.exception.LogisticBaseException;
import com.kozitski.task3.util.parameter.DataForBaseInitialization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase{
    private static final Logger LOGGER = LogManager.getLogger(LogisticBase.class);
    private static final int INITIAL_CAPACITY = 1000;
    private static final int BASE_CAPACITY = 5;
    private static AtomicInteger currentCapacity = new AtomicInteger(0);

    private static LogisticBase base;
    private static ReentrantLock lock = new ReentrantLock();

    private static ReentrantLock offerLock = new ReentrantLock();
    private static AtomicBoolean isCreate = new AtomicBoolean(false);
    private Semaphore terminal = new Semaphore(3);

    private Queue<Wagon> wagons;  // offer/poll
    private ArrayDeque<Product> products;  // push/pollLast

    private LogisticBase() {
        wagons = new PriorityQueue<>();
        products = new ArrayDeque<>();

        init();
    }
    private void init(){
        DataForBaseInitialization initialization = DataForBaseInitialization.getInstance();
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            products.add(initialization.createProduct());
        }
    }
    public static LogisticBase getInstance() {
        if(!isCreate.get()){
            try {
                lock.lock();
                if(base == null){
                    base = new LogisticBase();
                    isCreate.set(true);
                }
            }
            finally {
                lock.unlock();
            }
        }
        return base;
    }

    public boolean offer(Wagon wagon) {

        try {
            offerLock.lock();

            if(currentCapacity.get() >= BASE_CAPACITY){
                return false;
            }
            else {
                currentCapacity.incrementAndGet();
                return wagons.offer(wagon);
            }
        }
        finally {
            offerLock.unlock();
        }

    }

    // change
    public void getProduct() throws LogisticBaseException {

        try {
            terminal.acquire();
            Wagon currentWagon = wagons.poll();
            for (int i = 0; i < Objects.requireNonNull(currentWagon).getProductsSize(); i++) {
                products.push(currentWagon.getProduct(i));
            }

            TimeUnit.SECONDS.sleep(2);

        }
        catch (InterruptedException e) {
            throw new LogisticBaseException("Problems with terminal", e);
        } finally {
            terminal.release();
            currentCapacity.decrementAndGet();
        }

    }
    public int giveProduct(){

        try {
            terminal.acquire();
            int number = 0;
            Wagon currentWagon = wagons.poll();
            for (int i = 0; i < Wagon.NUMBER_OF_GIVE_PRODUCTS &&  products.size() > 0; i++) {
                Objects.requireNonNull(currentWagon).add(products.pollLast());
                number = i;
            }

            TimeUnit.SECONDS.sleep(2);
            return ++number;
        }
        catch (InterruptedException e) {
            LOGGER.error("Problems with terminal", e);
            return 0;
        }
        finally {
            terminal.release();
            currentCapacity.decrementAndGet();
        }

    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Base contains next products: ");
        stringBuilder.append("\n");
        for(Product product : products){
            stringBuilder.append(product);
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

}
