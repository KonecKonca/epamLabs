package com.kozitski.triangle.entity;


import com.kozitski.triangle.exception.PointException;
import com.kozitski.triangle.register.Observable;
import com.kozitski.triangle.register.Observer;
import com.kozitski.triangle.register.TriangleRegister;
import com.kozitski.triangle.repository.TriangleRepository;
import com.kozitski.triangle.service.TriangleOperation;
import com.kozitski.triangle.service.generator.TriangleIdGenerator;
import com.kozitski.triangle.util.TriangleUtil;
import com.kozitski.triangle.validator.TriangleValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Triangle implements Observable<Observer<Triangle>>{
    private long triangleId;
    // I decided that arrays of sides and angels are not duplicates of array of points
    // (It is good decision if in future will rarely change points, and often use values of sides and angels)
    private List<Double> sides = new ArrayList<>(3);
    private List<Double> angles = new ArrayList<>(3);
    private List<PointForTriangle> points = new ArrayList<>(3);

    private List<Observer<Triangle>> observers = new ArrayList<>();
    private static final Logger LOGGER = LogManager.getLogger(Triangle.class);

    private Triangle(){
        observers.add(TriangleRegister.getInstance());
    }
    public static Triangle getInstance(PointForTriangle point1, PointForTriangle point2, PointForTriangle point3){
        TriangleValidator validator = new TriangleValidator();
        try {
            validator.validate(point1, point2, point3);
        } catch (PointException e) {
            throw new IllegalArgumentException(e);
        }

        Triangle triangle = new Triangle();
        triangle.triangleId = TriangleIdGenerator.getId();
        triangle.points.add(point1);
        triangle.points.add(point2);
        triangle.points.add(point3);
        triangle.sides.addAll(TriangleUtil.calculateSides(point1, point2, point3));
        triangle.angles.addAll(TriangleUtil.calculateAngles(triangle.sides.get(0), triangle.sides.get(1), triangle.sides.get(2)));

        TriangleRepository.getTriangleRepository().add(triangle);
        TriangleRegister.getInstance().putSquare(triangle.triangleId, TriangleOperation.calculateSquare(triangle));
        TriangleRegister.getInstance().putPerimeter(triangle.triangleId, TriangleOperation.calculatePerimeter(triangle));

        return triangle;
    }
    public void changePoint(int numberOfPoint, PointForTriangle point){
        if(numberOfPoint <= 2 && numberOfPoint >= 0 && point != null){

            List<Integer> pointPositions = new ArrayList<>(2);
            if(numberOfPoint == 0){
                pointPositions.add(1);
                pointPositions.add(2);
            }
            else if(numberOfPoint == 1){
                pointPositions.add(0);
                pointPositions.add(2);
            }
            else{
                pointPositions.add(0);
                pointPositions.add(1);
            }

            TriangleValidator validator = new TriangleValidator();
            try {
                validator.validate(points.get(pointPositions.get(0)), points.get(pointPositions.get(1)), point);
            } catch (PointException e) {
                throw new IllegalArgumentException(e);
            }

            points = new ArrayList<>(Arrays.asList(point,points.get(pointPositions.get(0)), points.get(pointPositions.get(1))));
            sides = TriangleUtil.calculateSides(points.get(0), points.get(1), points.get(2));
            angles = TriangleUtil.calculateAngles(sides.get(0), sides.get(1), sides.get(2));

            notifyObservers();

        }
        else{
            LOGGER.error("Changing of point for triangle is failed");
        }
    }

    @Override
    public void addObserver(Observer<Triangle> observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer<Triangle> observer) {
        observers.remove(observer);
    }
    @Override
    public void notifyObservers() {
        for(Observer<Triangle> observer : observers){
            observer.handleEvent(this);
        }
    }

    public Double getSide(int index) {
        return sides.get(index);
    }
    public Double getAngle(int index) {
        return angles.get(index);
    }
    public PointForTriangle getPoint(int index){
        return points.get(index);
    }
    public long getTriangleId() {
        return triangleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(sides, triangle.sides) &&
                Objects.equals(angles, triangle.angles);
    }
    @Override
    public int hashCode() {
    int hashcode = 0;
    for(double value : sides){
        hashcode += 31 * value;
    }
    for(double value : angles){
        hashcode += 31 * value;
    }

    return hashcode;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Triangle: " + "triangleId=" + triangleId + "\n\tpoints: ");
        for(PointForTriangle point : points){
            stringBuilder.append(point);
        }
        stringBuilder.append("\n\tsides: ");
        stringBuilder.append(sides);
        return stringBuilder.toString();
    }

}
