package com.kozitski.triangle.service.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PointForTriangleIdGenerator {
    private static final long MAX_ID_BOUNDER = 66_000_666;
    private static long counter = 1;
    private static final Logger LOGGER = LogManager.getLogger(PointForTriangleIdGenerator.class);

    private PointForTriangleIdGenerator() { }

    public static long getId(){
        if(counter < MAX_ID_BOUNDER){
            return counter++;
        }
        else {
            LOGGER.error("ID for Point is not generated");
            throw new RuntimeException("Id value is bigger than, allowed: " + counter);
        }
    }

}
