package com.kozitski.triangle.util.search;

import com.kozitski.triangle.entity.Triangle;
import com.kozitski.triangle.repository.Specification;
import com.kozitski.triangle.service.TriangleOperation;
import com.kozitski.triangle.util.constants.PathsForConfigFiles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// Basic set of lambdas for Searching in repository
public class SearchRequirement {
    private long maxId;
    private long minId;
    private long id;

    private double firstXCoordinate;
    private double firstYCoordinate;
    private double secondXCoordinate;
    private double secondYCoordinate;
    private double thirdXCoordinate;
    private double thirdYCoordinate;

    private double xMinCoordinate;
    private double xMaxCoordinate;
    private double yMinCoordinate;
    private double yMaxCoordinate;

    private double perimeter;
    private double square;
    private double minPerimeter;
    private double maxPerimeter;
    private double minSquare;
    private double maxSquare;

    private static final double xStartOfCoordinate = 0;
    private static final double yStartOfCoordinate = 0;
    private double minDistance;
    private double maxDistance;

    private SearchRequirement(Builder builder) {
        this.maxId = builder.maxId;
        this.minId = builder.minId;
        this.id = builder.id;

        this.firstXCoordinate = builder.firstXCoordinate;
        this.firstYCoordinate = builder.firstYCoordinate;
        this.secondXCoordinate = builder.secondXCoordinate;
        this.secondYCoordinate = builder.secondYCoordinate;
        this.thirdXCoordinate = builder.thirdXCoordinate;
        this.thirdYCoordinate = builder.thirdYCoordinate;

        this.xMinCoordinate = builder.xMinCoordinate;
        this.xMaxCoordinate = builder.xMaxCoordinate;
        this.yMinCoordinate = builder.yMinCoordinate;
        this.yMaxCoordinate = builder.yMaxCoordinate;

        this.perimeter = builder.perimeter;
        this.square = builder.square;
        this.minPerimeter = builder.minPerimeter;
        this.maxPerimeter = builder.maxPerimeter;
        this.minSquare = builder.minSquare;
        this.maxSquare = builder.maxSquare;

        this.minDistance = builder.minDistance;
        this.maxDistance = builder.maxDistance;
    }

    public static final Specification<Triangle> ALL_RECTANGULAR_TRIANGLES = TriangleOperation::isRectangular;
    public static final Specification<Triangle> ALL_EQUILATERAL_TRIANGLES = TriangleOperation::isEquilateral;
    public static final Specification<Triangle> ALL_ISOSCELES_TRIANGLES = TriangleOperation::isIsosceles;
    public static final Specification<Triangle> ALL_OBTUSE_TRIANGLES = TriangleOperation::isObtuse;

    public final Specification<Triangle> ALL_WITH_EQUALS_ID = t -> t.getTriangleId() == id;
    public final Specification<Triangle> ALL_WITH_IN_RANGE_ID = t -> t.getTriangleId() <= maxId && t.getTriangleId() >= minId;

    public final Specification<Triangle> ALL_WITH_FIRST_COORDINATE = t -> t.getPoint(0).getCoordinateX() == firstXCoordinate && t.getPoint(0).getCoordinateY() == firstYCoordinate;
    public final Specification<Triangle> ALL_WITH_SECOND_COORDINATE = t -> t.getPoint(1).getCoordinateX() == secondXCoordinate && t.getPoint(1).getCoordinateY() == secondYCoordinate;
    public final Specification<Triangle> ALL_WITH_THIRD_COORDINATE = t -> t.getPoint(2).getCoordinateX() == thirdXCoordinate && t.getPoint(2).getCoordinateY() == thirdYCoordinate;
    public final Specification<Triangle> ALL_WITH_POINTS_INT_RANGE = t -> {
     return     t.getPoint(0).getCoordinateX() >= xMinCoordinate &&
                t.getPoint(0).getCoordinateX() <= xMaxCoordinate &&
                t.getPoint(1).getCoordinateX() >= xMinCoordinate &&
                t.getPoint(1).getCoordinateX() <= xMaxCoordinate &&
                t.getPoint(2).getCoordinateX() >= xMinCoordinate &&
                t.getPoint(2).getCoordinateX() <= xMaxCoordinate &&

                t.getPoint(0).getCoordinateY() >= yMinCoordinate &&
                t.getPoint(0).getCoordinateY() <= yMaxCoordinate &&
                t.getPoint(1).getCoordinateY() >= yMinCoordinate &&
                t.getPoint(1).getCoordinateY() <= yMaxCoordinate &&
                t.getPoint(2).getCoordinateY() >= yMinCoordinate &&
                t.getPoint(2).getCoordinateY() <= yMaxCoordinate;
    };

    public final Specification<Triangle> ALL_WITH_PERIMETER_EQUALS = t -> Double.compare(TriangleOperation.calculatePerimeter(t), perimeter) == 0;
    public final Specification<Triangle> ALL_WITH_SQUARE_EQUALS = t -> Double.compare(TriangleOperation.calculateSquare(t), square) == 0;
    public final Specification<Triangle> ALL_WITH_PERIMETER_IN_RANGE = t -> TriangleOperation.calculatePerimeter(t) >= minPerimeter && TriangleOperation.calculatePerimeter(t) <= maxPerimeter;
    public final Specification<Triangle> ALL_WITH_SQUARE_IN_RANGE = t -> TriangleOperation.calculateSquare(t) >= minSquare && TriangleOperation.calculateSquare(t) <= maxSquare;

    public final Specification<Triangle> ALL_IN_RANGE_DISTANCE = t -> {
        return  Math.hypot(t.getPoint(0).getCoordinateX(), t.getPoint(0).getCoordinateY()) >= minDistance &&
                Math.hypot(t.getPoint(0).getCoordinateX(), t.getPoint(0).getCoordinateY()) <= maxDistance &&

                Math.hypot(t.getPoint(1).getCoordinateX(), t.getPoint(1).getCoordinateY()) <= maxDistance &&
                Math.hypot(t.getPoint(1).getCoordinateX(), t.getPoint(1).getCoordinateY()) <= maxDistance &&

                Math.hypot(t.getPoint(2).getCoordinateX(), t.getPoint(2).getCoordinateY()) <= maxDistance &&
                Math.hypot(t.getPoint(2).getCoordinateX(), t.getPoint(2).getCoordinateY()) <= maxDistance;
    };


    public static class Builder{
        private static final Logger LOGGER = LogManager.getLogger(SearchRequirement.class);

        private long maxId = 100;
        private long minId = 1;
        private long id = 1;

        private double firstXCoordinate = 2;
        private double firstYCoordinate = 2;
        private double secondXCoordinate = 2;
        private double secondYCoordinate = 2;
        private double thirdXCoordinate = 2;
        private double thirdYCoordinate = 2;


        private double xMinCoordinate = -10;
        private double xMaxCoordinate = 10;
        private double yMinCoordinate = -10;
        private double yMaxCoordinate = 10;

        private double perimeter = 10;
        private double square = 10;
        private double minPerimeter = 1;
        private double maxPerimeter = 100;
        private double minSquare = 1;
        private double maxSquare = 100;

        private static final double xStartOfCoordinate = 0;
        private static final double yStartOfCoordinate = 0;
        private double minDistance = 10;
        private double maxDistance = 100;

        public Builder() {
            Properties properties;
            properties = new Properties();
            try(FileInputStream fileInputStream = new FileInputStream(PathsForConfigFiles.SEARCH_CONFIGURATIONS.getPath())){
                properties.load(fileInputStream);

                maxId = Long.parseLong(properties.getProperty("maxId"));
                minId = Long.parseLong(properties.getProperty("minId"));
                id = Long.parseLong(properties.getProperty("id"));

                firstXCoordinate = Double.parseDouble(properties.getProperty("firstXCoordinate"));
                firstYCoordinate = Double.parseDouble(properties.getProperty("firstYCoordinate"));
                secondXCoordinate = Double.parseDouble(properties.getProperty("secondXCoordinate"));
                secondYCoordinate = Double.parseDouble(properties.getProperty("secondYCoordinate"));
                thirdXCoordinate = Double.parseDouble(properties.getProperty("thirdXCoordinate"));
                thirdYCoordinate = Double.parseDouble(properties.getProperty("thirdYCoordinate"));


                xMinCoordinate = Double.parseDouble(properties.getProperty("xMinCoordinate"));
                xMaxCoordinate = Double.parseDouble(properties.getProperty("xMaxCoordinate"));
                yMinCoordinate = Double.parseDouble(properties.getProperty("yMinCoordinate"));
                yMaxCoordinate = Double.parseDouble(properties.getProperty("yMaxCoordinate"));

                perimeter = Double.parseDouble(properties.getProperty("perimeter"));
                square = Double.parseDouble(properties.getProperty("square"));
                minPerimeter = Double.parseDouble(properties.getProperty("minPerimeter"));
                maxPerimeter = Double.parseDouble(properties.getProperty("maxPerimeter"));
                minSquare = Double.parseDouble(properties.getProperty("minSquare"));
                maxSquare = Double.parseDouble(properties.getProperty("maxSquare"));

                minDistance = Double.parseDouble(properties.getProperty("minDistance"));
                maxDistance = Double.parseDouble(properties.getProperty("maxDistance"));

            } catch (IOException | NumberFormatException e) {
                LOGGER.error("SearchRequirement is not created (property file exists, or include incorrect data)", e);
            }

        }

        private void validateLong(long value){
            try {
                Long.parseLong("" + value);
            }
            catch (NumberFormatException e){
                LOGGER.error("Entered incorrect parameter for searching", e);
            }
        }
        private void validateDouble(double value){
            try {
                Double.parseDouble("" + value);
            }
            catch (NumberFormatException e){
                LOGGER.error("Entered incorrect parameter for searching", e);
            }
        }

        public SearchRequirement build(){
            return new SearchRequirement(this);
        }

        public Builder setMaxId(long maxId) {
            validateLong(maxId);
            this.maxId = maxId;
            return this;
        }
        public Builder setMinId(long minId) {
            validateLong(minId);
            this.minId = minId;
            return this;
        }
        public Builder setId(long id) {
            validateLong(id);
            this.id = id;
            return this;
        }

        public Builder setFirstXCoordinate(double firstXCoordinate) {
            validateDouble(firstXCoordinate);
            this.firstXCoordinate = firstXCoordinate;
            return this;
        }
        public Builder setFirstYCoordinate(double firstYCoordinate) {
            validateDouble(firstYCoordinate);
            this.firstYCoordinate = firstYCoordinate;
            return this;
        }
        public Builder setSecondXCoordinate(double secondXCoordinate) {
            validateDouble(secondXCoordinate);
            this.secondXCoordinate = secondXCoordinate;
            return this;
        }
        public Builder setSecondYCoordinate(double secondYCoordinate) {
            validateDouble(secondYCoordinate);
            this.secondYCoordinate = secondYCoordinate;
            return this;
        }
        public Builder setThirdXCoordinate(double thirdXCoordinate) {
            validateDouble(thirdXCoordinate);
            this.thirdXCoordinate = thirdXCoordinate;
            return this;
        }
        public Builder setThirdYCoordinate(double thirdYCoordinate) {
            validateDouble(thirdYCoordinate);
            this.thirdYCoordinate = thirdYCoordinate;
            return this;
        }

        public Builder setxMinCoordinate(double xMinCoordinate) {
            validateDouble(xMinCoordinate);
            this.xMinCoordinate = xMinCoordinate;
            return this;
        }
        public Builder setxMaxCoordinate(double xMaxCoordinate) {
            validateDouble(xMaxCoordinate);
            this.xMaxCoordinate = xMaxCoordinate;
            return this;
        }
        public Builder setyMinCoordinate(double yMinCoordinate) {
            validateDouble(yMinCoordinate);
            this.yMinCoordinate = yMinCoordinate;
            return this;
        }
        public Builder setyMaxCoordinate(double yMaxCoordinate) {
            validateDouble(yMaxCoordinate);
            this.yMaxCoordinate = yMaxCoordinate;
            return this;
        }

        public Builder setPerimeter(double perimeter) {
            validateDouble(perimeter);
            this.perimeter = perimeter;
            return this;
        }
        public Builder setSquare(double square) {
            validateDouble(square);
            this.square = square;
            return this;
        }
        public Builder setMinPerimeter(double minPerimeter) {
            validateDouble(minPerimeter);
            this.minPerimeter = minPerimeter;
            return this;
        }
        public Builder setMaxPerimeter(double maxPerimeter) {
            validateDouble(maxPerimeter);
            this.maxPerimeter = maxPerimeter;
            return this;
        }
        public Builder setMinSquare(double minSquare) {
            validateDouble(minSquare);
            this.minSquare = minSquare;
            return this;
        }
        public Builder setMaxSquare(double maxSquare) {
            validateDouble(maxSquare);
            this.maxSquare = maxSquare;
            return this;
        }

        public Builder setMinDistance(double minDistance) {
            validateDouble(minDistance);
            this.minDistance = minDistance;
            return this;
        }
        public Builder setMaxDistance(double maxDistance) {
            validateDouble(maxDistance);
            this.maxDistance = maxDistance;
            return this;
        }
    }

    public long getMaxId() {
        return maxId;
    }
    public long getMinId() {
        return minId;
    }
    public long getId() {
        return id;
    }
    public double getFirstXCoordinate() {
        return firstXCoordinate;
    }
    public double getFirstYCoordinate() {
        return firstYCoordinate;
    }
    public double getxMinCoordinate() {
        return xMinCoordinate;
    }
    public double getxMaxCoordinate() {
        return xMaxCoordinate;
    }
    public double getyMinCoordinate() {
        return yMinCoordinate;
    }
    public double getyMaxCoordinate() {
        return yMaxCoordinate;
    }
    public double getPerimeter() {
        return perimeter;
    }
    public double getSquare() {
        return square;
    }
    public double getMinPerimeter() {
        return minPerimeter;
    }
    public double getMaxPerimeter() {
        return maxPerimeter;
    }
    public double getMinSquare() {
        return minSquare;
    }
    public double getMaxSquare() {
        return maxSquare;
    }
    public static double getxStartOfCoordinate() {
        return xStartOfCoordinate;
    }
    public static double getyStartOfCoordinate() {
        return yStartOfCoordinate;
    }
    public double getMinDistance() {
        return minDistance;
    }
    public double getMaxDistance() {
        return maxDistance;
    }
    public double getSecondXCoordinate() {
        return secondXCoordinate;
    }
    public double getSecondYCoordinate() {
        return secondYCoordinate;
    }
    public double getThirdXCoordinate() {
        return thirdXCoordinate;
    }
    public double getThirdYCoordinate() {
        return thirdYCoordinate;
    }

}
