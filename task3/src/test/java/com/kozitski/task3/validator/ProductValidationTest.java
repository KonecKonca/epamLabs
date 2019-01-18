package com.kozitski.task3.validator;

import com.kozitski.task3.entity.Product;
import com.kozitski.task3.entity.ProductType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class ProductValidationTest {

    @Test(dataProvider = "dataForProductValidationTrueTest")
    public void productValidationTrueTest(String name, double value){
        Product product = new Product(name, new BigDecimal(value), ProductType.PERISHABLE);
    }
    @DataProvider(name = "dataForProductValidationTrueTest")
    public Object[][] dataForProductValidationTrueTest(){
        return new Object[][]{
                {"    43!   ", 36546},
                {"987654", 1},
                {"12324", 2},
                {"dfggr   `325t4765870080", 36},
                {"dfg efef 353 gr", 365},
                {"dfggr efef", 56},
                {"deffeefefe", 6546},
        };
    }

    @Test(dataProvider = "dataForProductValidationTrueFalse", expectedExceptions = RuntimeException.class)
    public void productValidationFalseTest(String name, double value){
        Product product = new Product(name, new BigDecimal(value), ProductType.PERISHABLE);
    }
    @DataProvider(name = "dataForProductValidationTrueFalse")
    public Object[][] dataForProductValidationTrueFalse(){
        return new Object[][]{
                {"    43   ", 36546},
                {"      ", 1},
                {"", 2},
                {"!0", 36},
                {"dfg efef 353 gr", 3699995},
                {"dfggr efef", null},
                {null, 545.3},
        };
    }

}
