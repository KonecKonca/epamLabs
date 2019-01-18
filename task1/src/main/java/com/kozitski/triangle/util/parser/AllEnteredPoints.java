package com.kozitski.triangle.util.parser;

import com.kozitski.triangle.entity.PointForTriangle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Class wrapper for transmitting data about parsing file
public class AllEnteredPoints {
    private List<PointForTriangle> points;
    private List<String> invalidStrings;

    public AllEnteredPoints() {
        points = new ArrayList<>();
        invalidStrings = new ArrayList<>();
    }

    public PointForTriangle getPoint(int index) {
        return points.get(index);
    }
    public boolean addPoint(PointForTriangle point) {
        return points.add(point);
    }
    public int pointsSize() {
        return points.size();
    }

    public void addInvalidStrings(String element) {
        invalidStrings.add(element);
    }
    public int invalidStringsSize() {
        return invalidStrings.size();
    }

    @Override
    public String toString() {
        return "AllEnteredPoints{" +
                "points=" + points +
                ", invalidStrings=" + invalidStrings +
                '}';
    }

}
