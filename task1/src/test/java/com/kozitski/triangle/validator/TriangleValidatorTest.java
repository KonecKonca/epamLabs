package com.kozitski.triangle.validator;

import com.kozitski.triangle.entity.PointForTriangle;
import com.kozitski.triangle.exception.PointException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.Assert.*;

public class TriangleValidatorTest {
    private TriangleValidator validator;

    @BeforeClass
    public void init(){
        validator = new TriangleValidator();
    }

    @Test(dataProvider = "dataForTrueValidation")
    public void trueValidate(boolean useless, ArrayList<PointForTriangle> points) throws PointException {
            validator.validate(points.get(0), points.get(1), points.get(2));
    }
    @DataProvider(name = "dataForTrueValidation")
    public Object[][] dataForTrueValidation(){
        return new Object[][]{
                {true, new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(0, 1),
                        PointForTriangle.getInstance(2, 44), PointForTriangle.getInstance(-23, 33)))},
                {true, new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(0, 1),
                        PointForTriangle.getInstance(44, 65), PointForTriangle.getInstance(-26, 77)))},
                {true, new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(8, 66),
                        PointForTriangle.getInstance(88, 5), PointForTriangle.getInstance(-2, 43)))},
                {true, new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(0, 1),
                        PointForTriangle.getInstance(25, -44), PointForTriangle.getInstance(-29, 33)))},
                {true, new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(0, -1),
                        PointForTriangle.getInstance(-12, 4), PointForTriangle.getInstance(-3, 33)))},
        };
    }
    @Test(dataProvider = "dataForFalseValidation", expectedExceptions = PointException.class)
    public void falseValidate(boolean useless, ArrayList<PointForTriangle> points) throws PointException {
        validator.validate(points.get(0), points.get(1), points.get(2));
    }
    @DataProvider(name = "dataForFalseValidation")
    public Object[][] dataForFalseValidation(){
        return new Object[][]{
                {true, new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(0, 221),
                        PointForTriangle.getInstance(2, 44), PointForTriangle.getInstance(0, 221)))},
                {true, new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(0, 1),
                        PointForTriangle.getInstance(-26, 77), PointForTriangle.getInstance(-26, 77)))},
                {true, new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(8, 66),
                        PointForTriangle.getInstance(8, 66), PointForTriangle.getInstance(-233, 43)))},
                {true, new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(-23, 3),
                        PointForTriangle.getInstance(25, -34), PointForTriangle.getInstance(-23, 3)))},
                {true, new ArrayList<>(Arrays.asList(PointForTriangle.getInstance(0, -1),
                        PointForTriangle.getInstance(0, -1), PointForTriangle.getInstance(-33, 33)))},
        };
    }

}