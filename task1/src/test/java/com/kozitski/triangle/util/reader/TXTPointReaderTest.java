package com.kozitski.triangle.util.reader;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kozitski.triangle.util.parser.PointParserTest.DATA_PATH;

public class TXTPointReaderTest {
    private TXTPointReader reader;

    @BeforeClass
    public void init(){
        reader = new TXTPointReader();
    }

    @Test
    public void testReadAllPointsTrue() {
        reader.readAllPoints(DATA_PATH + "incorrectDAta.txt");
    }
    @Test(expectedExceptions = RuntimeException.class)
    public void testReadAllPointsFalse() {
        reader.readAllPoints(DATA_PATH + "incogerfergerwgerctDAta.txt");
    }

}