package com.kozitski.triangle.register;

import com.kozitski.triangle.entity.PointForTriangle;
import com.kozitski.triangle.entity.Triangle;
import com.kozitski.triangle.repository.TriangleRepository;
import com.kozitski.triangle.service.TriangleOperation;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class TriangleRegisterTest {

    @Test(dataProvider = "dataForTrianglePerimeter")
    public void putGetPerimeterTest(double actual, Triangle triangle){
        TriangleRegister register = TriangleRegister.getInstance();
        register.putPerimeter(triangle.getTriangleId(), TriangleOperation.calculatePerimeter(triangle));

        double expected = register.getPerimeter(triangle.getTriangleId());
        assertEquals(actual, expected, 0.3);
    }
    @DataProvider(name = "dataForTrianglePerimeter")
    public Object[][] dataForPerimeter(){
        return new Object[][]{
                {3.4, Triangle.getInstance(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 1), PointForTriangle.getInstance(1, 0))},
                {6.8, Triangle.getInstance(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 2), PointForTriangle.getInstance(2, 0))},
                {10.2, Triangle.getInstance(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 3), PointForTriangle.getInstance(3, 0))},
                {13.8, Triangle.getInstance(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 4), PointForTriangle.getInstance(4, 0))},
                {17.04, Triangle.getInstance(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 5), PointForTriangle.getInstance(5, 0))},
                {20.5, Triangle.getInstance(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 6), PointForTriangle.getInstance(6, 0))},
                {23.96, Triangle.getInstance(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 7), PointForTriangle.getInstance(7, 0))}
        };
    }

    @Test(dataProvider = "dataForTriangleSquare")
    public void putGetSquareTest(double actual, Triangle triangle){
        TriangleRegister register = TriangleRegister.getInstance();
        register.putPerimeter(triangle.getTriangleId(), TriangleOperation.calculateSquare(triangle));

        double expected = register.getSquare(triangle.getTriangleId());
        assertEquals(actual, expected, 0.3);
    }
    @DataProvider(name = "dataForTriangleSquare")
    public Object[][] dataForSquare(){
        return new Object[][]{
                {0.5, Triangle.getInstance(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 1), PointForTriangle.getInstance(1, 0))},
                {2, Triangle.getInstance(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 2), PointForTriangle.getInstance(2, 0))},
                {4.5, Triangle.getInstance(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 3), PointForTriangle.getInstance(3, 0))},
                {8, Triangle.getInstance(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 4), PointForTriangle.getInstance(4, 0))},
                {12.5, Triangle.getInstance(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 5), PointForTriangle.getInstance(5, 0))},
                {18, Triangle.getInstance(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 6), PointForTriangle.getInstance(6, 0))},
                {24.5, Triangle.getInstance(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 7), PointForTriangle.getInstance(7, 0))}
        };
    }

}