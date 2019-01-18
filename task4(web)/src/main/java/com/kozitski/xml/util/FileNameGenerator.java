package com.kozitski.xml.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class FileNameGenerator {
    private static Logger LOGGER = LogManager.getLogger(FileNameGenerator.class);

    private static  ReentrantLock lock = new ReentrantLock();
    private static final String FILE_NAME_PREFIX = "xmlFile";
    private static final String FILE_NAME_POSTFIX = ".xml";
    private static AtomicInteger nameCounter = new AtomicInteger(0);

    private FileNameGenerator() {}

    public static String generateName(){

        try {
            lock.lock();
            return FILE_NAME_PREFIX + nameCounter.incrementAndGet() + FILE_NAME_POSTFIX;
        }
        finally {
            LOGGER.info("File name generated");
            lock.unlock();
        }

    }
    public static String getLastName(){

        try {
            lock.lock();
            return FILE_NAME_PREFIX + nameCounter.get() + FILE_NAME_POSTFIX;
        }
        finally {
            lock.unlock();
        }

    }

}
