package com.kozitski.triangle.util;

import com.kozitski.triangle.entity.PointForTriangle;
import com.kozitski.triangle.entity.Triangle;
import com.kozitski.triangle.service.TriangleOperation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;


public class TriangleUtilTest {
    private ArrayList<PointForTriangle> points;

    @BeforeClass
    public void init(){
        points = new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(0, 1),
                PointForTriangle.getInstance(-5, 45), PointForTriangle.getInstance(20, 90)));
    }

    @Test(dataProvider = "dataForIsCanBeTriangleTest")
    public void isCanBeTriangleTestTrue(boolean actual, ArrayList<PointForTriangle> points){
        boolean expected = TriangleUtil.isCanBeTriangle(points.get(0), points.get(1), points.get(2));

        assertEquals(actual, expected);
    }
    @Test(dataProvider = "dataForIsCanBeTriangleTest")
    public void isCanBeTriangleTestFalse(boolean actual, ArrayList<PointForTriangle> points){
        boolean expected = TriangleUtil.isCanBeTriangle(points.get(0), points.get(2), points.get(2));


        assertNotEquals(actual, expected);
    }
    @DataProvider(name = "dataForIsCanBeTriangleTest")
    public Object[][] dataForIsCanBeTriangleTestTrue(){
        return new Object[][]{
                {true,  new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 1), PointForTriangle.getInstance(1, 0)))},
                {true,  new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 2), PointForTriangle.getInstance(2, 0)))},
                {true,  new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 3), PointForTriangle.getInstance(3, 0)))},
                {true,  new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 4), PointForTriangle.getInstance(4, 0)))},
                {true,  new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 5), PointForTriangle.getInstance(5, 0)))},
                {true,  new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 6), PointForTriangle.getInstance(6, 0)))},
                {true,  new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(0, 0), PointForTriangle.getInstance(0, 7), PointForTriangle.getInstance(7, 0)))}
        };
    }

    @Test(dataProvider = "dataForIsCanBeTriangleTest")
    public void calculateSides(boolean actual, ArrayList<PointForTriangle> points){
        TriangleUtil.calculateSides(points.get(0), points.get(1), points.get(2));
    }
    @Test(dataProvider = "dataForIsCanBeTriangleTest")
    public void calculateAngles(boolean actual, ArrayList<PointForTriangle> points){
        List<Double> sides = TriangleUtil.calculateSides(points.get(0), points.get(1), points.get(2));
        TriangleUtil.calculateAngles(sides.get(0), sides.get(1), sides.get(2));
    }

}
