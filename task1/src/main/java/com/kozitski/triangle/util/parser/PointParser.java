package com.kozitski.triangle.util.parser;

import com.kozitski.triangle.entity.PointForTriangle;
import com.kozitski.triangle.entity.Triangle;
import com.kozitski.triangle.util.reader.TXTPointReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PointParser {
    private static AllEnteredPoints allEnteredPoints;
    private static final String SPACE_DELIMITER = "\\p{Blank}+";
    private static final Logger LOGGER = LogManager.getLogger(PointParser.class);

    public static AllEnteredPoints getAllPoints(String path){
        allEnteredPoints = new AllEnteredPoints();

        TXTPointReader txtPointReader = new TXTPointReader();
        ArrayList<String> pointList = txtPointReader.readAllPoints(path);

        for(String str : pointList){
            List<String> currentPoint = new ArrayList<>(Arrays.asList(str.split(SPACE_DELIMITER)));
            try {
                double x = Double.parseDouble(currentPoint.get(0));
                double y = Double.parseDouble(currentPoint.get(1));
                allEnteredPoints.addPoint(PointForTriangle.getInstance(x, y));
            }
            catch (NumberFormatException | IndexOutOfBoundsException e){
                allEnteredPoints.addInvalidStrings(currentPoint.toString());
                LOGGER.error("Invalid String was met in TXT file");
            }
        }

        LOGGER.info("Result of reading TXT file: " + allEnteredPoints);

        return allEnteredPoints;
    }
    public static Triangle getTriangle(){
        if(allEnteredPoints != null){
            if(allEnteredPoints.pointsSize() >= 3){
                return Triangle.getInstance(allEnteredPoints.getPoint(0), allEnteredPoints.getPoint(1), allEnteredPoints.getPoint(2));
            }
        }
        LOGGER.fatal("Less than 3 point entered");
        throw  new RuntimeException("Triangle can not be created: Less than 3 points");
    }

}
