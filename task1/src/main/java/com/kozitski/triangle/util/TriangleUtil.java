package com.kozitski.triangle.util;

import com.kozitski.triangle.entity.PointForTriangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;

public class TriangleUtil {
    private static double precision = 0.01;
    public static void setPrecision(double PRECISION) {
        TriangleUtil.precision = PRECISION;
    }

    private static final Logger LOGGER = LogManager.getLogger(TriangleUtil.class);

    public static ArrayList<Double> calculateSides(PointForTriangle point1, PointForTriangle point2, PointForTriangle point3){
        return new ArrayList<>(Arrays.asList(
                Math.hypot(point1.getCoordinateX() - point2.getCoordinateX(), point1.getCoordinateY() - point2.getCoordinateY()),
                Math.hypot(point1.getCoordinateX() - point3.getCoordinateX(), point1.getCoordinateY() - point3.getCoordinateY()),
                Math.hypot(point2.getCoordinateX() - point3.getCoordinateX(), point2.getCoordinateY() - point3.getCoordinateY())
        ));
    }
    public static ArrayList<Double> calculateAngles(double side1, double side2, double side3){
        return new ArrayList<>(Arrays.asList(
                acos((pow(side2, 2) + pow(side3, 2) - pow(side1, 2)) / (2 * side2 * side3)),
                acos((pow(side1, 2) + pow(side3, 2) - pow(side2, 2)) / (2 * side1 * side3)),
                acos((pow(side1, 2) + pow(side2, 2) - pow(side3, 2)) / (2 * side1 * side2))

        ));
    }
    public static boolean isCanBeTriangle(PointForTriangle point1, PointForTriangle point2,PointForTriangle point3){
        List<Double> sides = calculateSides(point1, point2, point3);

        if(!(isFirstDoubleBigger(sides.get(0) + sides.get(1), sides.get(2)) || isFirstDoubleBigger(sides.get(0) + sides.get(2), sides.get(1))
                || isFirstDoubleBigger(sides.get(1) + sides.get(2), sides.get(0)))){
            return true;
        }
        LOGGER.info("Current combination of points can NOT be triangle");
        return false;
    }
    private static boolean isFirstDoubleBigger(double value1, double value2){
        return (Math.abs(value1 - value2) < precision);
    }

}
