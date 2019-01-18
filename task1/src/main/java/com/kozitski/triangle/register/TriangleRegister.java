package com.kozitski.triangle.register;

import com.kozitski.triangle.entity.Triangle;
import com.kozitski.triangle.repository.TriangleRepository;
import com.kozitski.triangle.service.TriangleOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TriangleRegister implements Observer<Triangle>{
    private static TriangleRegister register;
    public static TriangleRegister getInstance(){
        if(register != null){
            return register;
        }
        register = new TriangleRegister();
        LOGGER.info("is created");
        return register;
    }
    private static final Logger LOGGER = LogManager.getLogger(TriangleRegister.class);

    private Map<Long, Double> perimeters;
    private Map<Long, Double> squares;

    private TriangleRegister() {
        perimeters = new HashMap<>();
        squares = new HashMap<>();
    }

    @Override
    public void handleEvent(Triangle triangle) {
        perimeters.put(triangle.getTriangleId(), TriangleOperation.calculatePerimeter(triangle));
        squares.put(triangle.getTriangleId(), TriangleOperation.calculateSquare(triangle));
        LOGGER.info("TriangleRegister is updated");
    }

    public Double getPerimeter(long triangleId) {
        return perimeters.get(triangleId);
    }
    public void putPerimeter(long triangleId, double perimeter){
        perimeters.put(triangleId, perimeter);
    }
    public Double getSquare(long triangleId) {
        return squares.get(triangleId);
    }
    public void putSquare(long triangleId, double square){
        squares.put(triangleId, square);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("TriangleRegister:\n\tperimeters: ");
        stringBuilder.append(perimeters);
        stringBuilder.append("\n\tsquares: ");
        stringBuilder.append(squares);

        return stringBuilder.toString();
    }

}
