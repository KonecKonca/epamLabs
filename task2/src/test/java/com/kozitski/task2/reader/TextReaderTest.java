package com.kozitski.task2.reader;

import org.testng.annotations.Test;

public class TextReaderTest {

    @Test(expectedExceptions = RuntimeException.class)
    public void testReadAllTextFalse() {
        TextReader.readAllText(TextReader.INPUT_DATA_PATH + "343r");
    }

    @Test()
    public void testReadAllTextTrue() {
        TextReader.readAllText(TextReader.INPUT_DATA_PATH);
    }

}