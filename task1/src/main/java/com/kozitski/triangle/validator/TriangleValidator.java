package com.kozitski.triangle.validator;

import com.kozitski.triangle.entity.PointForTriangle;
import com.kozitski.triangle.exception.PointException;
import com.kozitski.triangle.util.TriangleUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.kozitski.triangle.util.constants.PathsForConfigFiles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TriangleValidator {
    private Properties properties;

    private static final Logger LOGGER = LogManager.getLogger(TriangleValidator.class);

    public TriangleValidator() {
        properties = new Properties();
        try(FileInputStream fileInputStream = new FileInputStream(PathsForConfigFiles.VALIDATION_CONFIGURATIONS.getPath())){
            properties.load(fileInputStream);
        } catch (IOException e) {

            LOGGER.fatal("PointForTriangleValidator is not created (property file exists)");

        }
    }

    public void validate(PointForTriangle point1, PointForTriangle point2, PointForTriangle point3) throws PointException {
        if(!TriangleUtil.isCanBeTriangle(point1, point2, point3)){
            throw new PointException("These points are invalid for triangle: " + point1 + " " + point2 + " " + point3);
        }

        List<Double> sides = TriangleUtil.calculateSides(point1, point2, point3);
        List<Double> angles = TriangleUtil.calculateAngles(sides.get(0), sides.get(1), sides.get(2));

        validateSides(sides.get(0), sides.get(1), sides.get(2));
        validateAngles(angles.get(0), angles.get(1), angles.get(2));

        LOGGER.info("Triangle validation was successful");
    }
    private void validateSides(double side1, double side2, double side3) throws PointException {
        int minSide =  Integer.parseInt(properties.getProperty("sidesLoverBound"));
        int maxSide = Integer.parseInt(properties.getProperty("sidesHigherBound"));

        LOGGER.info("SIDES BOUNDS was got successfully");

        if((side1 < minSide || side2 < minSide || side3 < minSide) ||
                (side1 > maxSide || side2 > maxSide || side3 > maxSide) ||
                checkSidesMathCondition(side1, side2, side3)){
            throw  new PointException("Input sides are invalid: " + side1 + ", " + side2 + ", " + side3);
        }
    }
    private boolean checkSidesMathCondition(double side1, double side2, double side3){
        return !(((side1 + side2) > side3) || ((side1 + side3) > side2) || ((side3 + side2) > side1));
    }
    private void validateAngles(double angle1, double angle2, double angle3) throws PointException {
        int minAngle =  Integer.parseInt(properties.getProperty("anglesLoverBound"));
        int maxAngle = Integer.parseInt(properties.getProperty("anglesHigherBound"));

        LOGGER.info("ANGLES BOUNDS was got successfully");

        if((angle1 < minAngle || angle2 < minAngle || angle3 < minAngle) ||
                (angle1 > maxAngle || angle2 > maxAngle || angle3 > maxAngle)){
            throw  new PointException("Input values are invalid: " + angle1 + ", " + angle2 + ", " + angle3);
        }
    }

}
