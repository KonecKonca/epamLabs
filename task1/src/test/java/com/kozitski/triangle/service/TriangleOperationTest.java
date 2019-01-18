package com.kozitski.triangle.service;


import com.kozitski.triangle.entity.PointForTriangle;
import com.kozitski.triangle.entity.Triangle;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TriangleOperationTest {
    private Triangle triangle;

    @BeforeClass
    public void initial(){
        triangle = Triangle.getInstance(PointForTriangle.getInstance(0, 0)
            , PointForTriangle.getInstance(0, 7), PointForTriangle.getInstance(7, 0));
    }

    @Test(dataProvider = "dataForTrianglePerimeter")
    public void perimeterTest(double actual, Triangle triangle){
        double expected = TriangleOperation.calculatePerimeter(triangle);
        assertEquals(actual, expected,0.3);
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
    public void squareTest(double actual, Triangle triangle){
        double expected =  TriangleOperation.calculateSquare(triangle);
        assertEquals(actual, expected,0.3);
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
    @Test
    public void isRectangularTrue(){
        assertTrue(TriangleOperation.isRectangular(triangle));
    }
    @Test
    public void isRectangularFalse(){
        assertFalse(TriangleOperation.isRectangular(Triangle.getInstance(PointForTriangle.getInstance(0, 0),
                PointForTriangle.getInstance(-2, 3), PointForTriangle.getInstance(100, 0))));
    }
    @Test
    public void isEquilateralTrue(){
        assertTrue(TriangleOperation.isEquilateral(Triangle.getInstance(PointForTriangle.getInstance(5, 7),
                PointForTriangle.getInstance(2, 2), PointForTriangle.getInstance(8, 2))));
    }
    @Test
    public void isEquilateralFalse(){
        assertFalse(TriangleOperation.isEquilateral(triangle));
    }
    @Test
    public void isIsoscelesTrue(){
        assertTrue(TriangleOperation.isIsosceles(triangle));
    }
    @Test
    public void isIsoscelesFalse(){
        assertFalse(TriangleOperation.isIsosceles(Triangle.getInstance(PointForTriangle.getInstance(5, 7),
                PointForTriangle.getInstance(2, 2), PointForTriangle.getInstance(8, 22))));
    }
    @Test
    public void isObtuseTrue(){
        assertTrue(TriangleOperation.isObtuse(Triangle.getInstance(PointForTriangle.getInstance(5, 7),
                PointForTriangle.getInstance(2, 2), PointForTriangle.getInstance(8, 22))));
    }
    @Test
    public void isObtuseFalse(){
        assertFalse(TriangleOperation.isObtuse(triangle));
    }

}
