package com.kozitski.triangle.validator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.kozitski.triangle.exception.PointException;
import com.kozitski.triangle.util.constants.PathsForConfigFiles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PointForTriangleValidator {
    private Properties properties;

    private static final Logger LOGGER = LogManager.getLogger(PointForTriangleValidator.class);

    public PointForTriangleValidator() {
        properties = new Properties();
        try(FileInputStream fileInputStream = new FileInputStream(PathsForConfigFiles.VALIDATION_CONFIGURATIONS.getPath())){
            properties.load(fileInputStream);
        } catch (IOException e) {

            LOGGER.error("PointForTriangleValidator is not created (property file exists)");

        }
    }
    public void validate(double x, double y) throws PointException {
        int xMinValue = Integer.parseInt(properties.getProperty("pointXMinValue"));
        int xMaxValue = Integer.parseInt(properties.getProperty("pointXMaxValue"));
        int yMinValue = Integer.parseInt(properties.getProperty("pointYMinValue"));
        int yMaxValue = Integer.parseInt(properties.getProperty("pointYMaxValue"));

        LOGGER.info("POINTS BOUNDS for PointForTriangleValidator from validationConfigurations.properties was got successfully");

        if((x < xMinValue || x > xMaxValue) || (y < yMinValue || y > yMaxValue)){
            throw  new PointException("Input values are invalid: " + x + ", " + y);
        }

    }

}
