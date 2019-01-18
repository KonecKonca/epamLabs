package com.kozitski.task3.util.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.kozitski.task3.util.parameter.ProductParameter;
import com.kozitski.task3.util.parameter.WagonParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class WagonIdGenerator {
    private static final Logger LOGGER = LogManager.getLogger(WagonIdGenerator.class);
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final String WAGON_VALIDATION_PATH = "data/validation/wagon.yml";

    static {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        WagonParameter wagonParameter;
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            wagonParameter = mapper.readValue(new File(Objects.requireNonNull(loader.getResource(WAGON_VALIDATION_PATH)).getFile()), WagonParameter.class);
            MAX_ID_BOUNDER = wagonParameter.getMaxWagonId();

        }
        catch (IOException e) {
            LOGGER.error("Yaml file for indication allowed ID is not exist", e);
            MAX_ID_BOUNDER = 1500;
        }


        counter = new AtomicLong(0);
    }

    private static long MAX_ID_BOUNDER;
    private static AtomicLong counter;

    private WagonIdGenerator() { }

    public static long generateId(){
        LOCK.lock();

        if(counter.get() < MAX_ID_BOUNDER){
            LOCK.unlock();
            return counter.incrementAndGet();
        }
        else {
            LOGGER.fatal("ID for Wagon is not generated");
            throw new RuntimeException("Id Wagon is bigger than, allowed: " + counter);
        }
    }

}
