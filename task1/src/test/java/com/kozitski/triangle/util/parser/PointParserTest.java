package com.kozitski.triangle.util.parser;

import com.kozitski.triangle.util.constants.PathsForConfigFiles;
import com.kozitski.triangle.util.parser.AllEnteredPoints;
import com.kozitski.triangle.util.parser.PointParser;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class PointParserTest {
    public static final String DATA_PATH = "src/main/resources/data/";
    private AllEnteredPoints allEnteredPoints;

    @Test
    public void getAllPointsTrue(){
        int actual = 0;
        allEnteredPoints = PointParser.getAllPoints(DATA_PATH + "correctDAta.txt");
        int expected = allEnteredPoints.invalidStringsSize();

        assertEquals(actual, expected);
    }
    @Test
    public void getAllPointsFalse(){
        int actual = 0;
        allEnteredPoints = PointParser.getAllPoints(DATA_PATH + "incorrectDAta.txt");
        int  expected = allEnteredPoints.invalidStringsSize();

        assertNotEquals(actual, expected);
    }

    @Test()
    public void getTriangleTrue(){
        PointParser.getAllPoints(DATA_PATH + "incorrectData.txt");
        PointParser.getTriangle();
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void getTriangleFalse(){
        PointParser.getAllPoints(DATA_PATH + "lessPoints.txt");
        PointParser.getTriangle();
    }

    @Test
    public void testGetAllPoints() {
        PointParser.getAllPoints(PathsForConfigFiles.VALIDATION_CONFIGURATIONS.getPath());
    }
    @Test(expectedExceptions = RuntimeException.class)
    public void testGetTriangleFalse() {
        PointParser.getTriangle();
    }
    @Test
    public void testGetTriangleTrue() {
        PointParser.getAllPoints(DATA_PATH + "incorrectDAta.txt");
        PointParser.getTriangle();
    }

}
