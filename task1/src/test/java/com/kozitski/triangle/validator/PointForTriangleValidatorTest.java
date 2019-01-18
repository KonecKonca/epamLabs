package com.kozitski.triangle.validator;

import com.kozitski.triangle.entity.PointForTriangle;
import com.kozitski.triangle.exception.PointException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PointForTriangleValidatorTest {
    private PointForTriangleValidator validator;

    @BeforeClass
    public void initial(){
        validator = new PointForTriangleValidator();
    }

    @Test(dataProvider = "dataForTrueValidation")
    public void testValidateTrue(double value1, double value2) throws PointException {
        validator.validate(value1, value2);
    }
    @DataProvider(name = "dataForTrueValidation")
    public Object[][] dataForTrueValidation(){
        return new Object[][]{
                {23, 34},
                {4, -45},
                {6, 5},
                {23, 65},
                {-5, 45}
        };
    }
    @Test(dataProvider = "dataForFalseValidation", expectedExceptions = PointException.class)
    public void testValidateFalse(double value1, double value2) throws PointException {
        validator.validate(value1, value2);
    }
    @DataProvider(name = "dataForFalseValidation")
    public Object[][] dataForFalseValidation(){
        return new Object[][]{
                {23343, 345454},
                {-4544, -45},
                {6, 45445},
                {23, 645455},
                {-50000, 45}
        };
    }
}