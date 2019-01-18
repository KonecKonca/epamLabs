package com.kozitski.task3.util.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.kozitski.task3.util.parameter.ProductParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class ProductIdGenerator {
    private static final Logger LOGGER = LogManager.getLogger(ProductIdGenerator.class);
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final String ALLOWED_ID_PATH = "data/validation/product.yml";

    static {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        ProductParameter productParameter;
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            productParameter = mapper.readValue(new File(Objects.requireNonNull(loader.getResource(ALLOWED_ID_PATH)).getFile()), ProductParameter.class);
            MAX_ID_BOUNDER = productParameter.getMaxProductId();

        }
        catch (IOException e) {
            LOGGER.error("Yaml file for indication allowed ID is not exist", e);
            MAX_ID_BOUNDER = 1500;
        }


        counter = new AtomicLong(0);
    }

    private static long MAX_ID_BOUNDER;
    private static AtomicLong counter;

    private ProductIdGenerator() { }

    public static long generateId(){
        LOCK.lock();

        if(counter.get() < MAX_ID_BOUNDER){
            LOCK.unlock();
            return counter.incrementAndGet();
        }
        else {
            LOGGER.fatal("ID for Product is not generated");
            throw new RuntimeException("Id value is bigger than, allowed: " + counter);
        }

    }

}
