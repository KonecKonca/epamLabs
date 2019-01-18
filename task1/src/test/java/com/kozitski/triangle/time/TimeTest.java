package com.kozitski.triangle.time;

import com.kozitski.triangle.entity.PointForTriangle;
import com.kozitski.triangle.entity.Triangle;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class TimeTest {


    @Test
    public void pointsTimeTest(){
        long start = System.currentTimeMillis();
        for(int i = 0; i < 20_000; i++){
            PointForTriangle.getInstance(0, 0);
        }
        long end = System.currentTimeMillis();

        boolean actual = true;
        long time = end - start;
        boolean excepted = (time < 15_000);

        assertEquals(actual, excepted);
    }

    @Test
    public void trianglesTimeTest(){
        ArrayList<PointForTriangle> point = new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(0, 23),
                PointForTriangle.getInstance(-7, 3), PointForTriangle.getInstance(2, 39)));

        long start = System.currentTimeMillis();
        for(int i = 0; i < 20_000; i++){
            Triangle.getInstance(point.get(0), point.get(1), point.get(2));
        }

        long end = System.currentTimeMillis();
        boolean actual = true;
        long time = end - start;
        boolean excepted = (time < 15_000);

        assertEquals(actual, excepted);
    }

}
