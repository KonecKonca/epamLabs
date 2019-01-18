package com.kozitski.triangle.entity;

import com.kozitski.triangle.exception.PointException;
import com.kozitski.triangle.service.generator.PointForTriangleIdGenerator;
import com.kozitski.triangle.validator.PointForTriangleValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PointForTriangle {
    private long pointForTriangleId;
    private double coordinateX;
    private double coordinateY;

    private static final Logger LOGGER = LogManager.getLogger(PointForTriangle.class);

    private PointForTriangle() {}

    public static PointForTriangle getInstance(double coordinateX, double coordinateY){
        PointForTriangle point = new PointForTriangle();
        point.pointForTriangleId = PointForTriangleIdGenerator.getId();

        PointForTriangleValidator validator = new PointForTriangleValidator();
        try {
            validator.validate(coordinateX, coordinateY);
        } catch (PointException e) {

            LOGGER.error("Validation of PointForTriangle was failed: ", e);

            IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
            illegalArgumentException.addSuppressed(e);
            throw illegalArgumentException;
        }

        point.coordinateX = coordinateX;
        point.coordinateY = coordinateY;
        return point;
    }

    public double getCoordinateX() {
        return coordinateX;
    }
    public double getCoordinateY() {
        return coordinateY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointForTriangle)) return false;
        PointForTriangle that = (PointForTriangle) o;
        return getCoordinateX() == that.getCoordinateX() &&
                getCoordinateY() == that.getCoordinateY();
    }
    @Override
    public int hashCode() {
        int hashCode = 31 * ((int)coordinateX);
        hashCode += (31 * (int) coordinateY );

        return hashCode;
    }

    @Override
    public String toString() {
        return "(" + coordinateX + ", " + coordinateY + ")";
    }

}
