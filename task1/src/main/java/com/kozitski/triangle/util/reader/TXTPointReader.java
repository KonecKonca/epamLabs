package com.kozitski.triangle.util.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TXTPointReader {

    private static final Logger LOGGER = LogManager.getLogger(TXTPointReader.class);

    public ArrayList<String> readAllPoints(String path){
        ArrayList<String> allPoint;

        try {
            allPoint = new ArrayList<>(Files.readAllLines(Paths.get(path)));
        } catch (IOException e) {
            LOGGER.fatal("file exists");
            throw new RuntimeException("File exists on the next path: " + path);
        }

        return allPoint;
    }

}
