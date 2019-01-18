package com.kozitski.triangle.service;

import com.kozitski.triangle.entity.Triangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.lang.Math.*;


public class TriangleOperation {
    private static final Logger LOGGER = LogManager.getLogger(TriangleOperation.class);

    public static double calculatePerimeter(Triangle triangle){
        return (triangle.getSide(0) + triangle.getSide(1) + triangle.getSide(2));
    }
    public static double calculateSquare(Triangle triangle){
        double p = calculatePerimeter(triangle)/2;
        return sqrt(p * (p - triangle.getSide(0)) * (p - triangle.getSide(1)) * (p - triangle.getSide(2)));
    }

    public static boolean isRectangular(Triangle triangle){
        if((Math.round(Math.toDegrees(triangle.getAngle(0))) == 90)
            || (Math.round(Math.toDegrees(triangle.getAngle(1))) == 90)
                ||(Math.round(Math.toDegrees(triangle.getAngle(2))) == 90)){
            LOGGER.debug("isRectangular = true");
         return true;
        }
        LOGGER.debug("isRectangular = false");
        return false;
    }
    public static boolean isEquilateral(Triangle triangle){
        if((Math.abs(triangle.getSide(0) - triangle.getSide(1)) < (triangle.getSide(0)/20)) &&
                (Math.abs(triangle.getSide(1) - triangle.getSide(2)) < (triangle.getSide(1)/20))){
            LOGGER.debug("isEquilateral = true");
            return true;
        }
        LOGGER.debug("isEquilateral = false");
        return false;
    }
    public static boolean isIsosceles(Triangle triangle){
        if((Math.round(triangle.getSide(0)) == Math.round(triangle.getSide(1))) ||
                        (Math.round(triangle.getSide(0)) == Math.round(triangle.getSide(2))) ||
                        (Math.round(triangle.getSide(1)) == Math.round(triangle.getSide(2)))){
            LOGGER.debug("isIsosceles = true");
            return true;
        }
        LOGGER.debug("isIsosceles = false");
        return false;
    }
    public static boolean isObtuse(Triangle triangle){
        if((Math.round(Math.toDegrees(triangle.getAngle(0))) > 90)
                || (Math.round(Math.toDegrees(triangle.getAngle(1))) > 90)
                ||(Math.round(Math.toDegrees(triangle.getAngle(2))) > 90)){
            LOGGER.debug("isObtuse = true");
            return true;
        }
        LOGGER.debug("isObtuse = false");
        return false;
    }

}
