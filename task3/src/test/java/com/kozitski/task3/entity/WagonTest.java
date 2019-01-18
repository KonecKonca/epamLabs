package com.kozitski.task3.entity;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.*;

public class WagonTest {
    private List<Product> products;
    @BeforeClass
    public void init() {
        products = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            products.add(new Product("xxxx" + new Random().nextInt(100), new BigDecimal(333), ProductType.PERISHABLE));
        }
    }

    @Test(dataProvider = "dataForWagonCompareTestTrue")
    public void wagonCompareTestTrue(int actual, List<Product> products){
        Wagon actualWagon = new Wagon();
        actualWagon.addAll(products);

        Wagon expectedWagon = new Wagon();
        expectedWagon.addAll(products);

        int expected = actualWagon.compareTo(expectedWagon);

        assertEquals(actual, expected);
    }
    @DataProvider(name = "dataForWagonCompareTestTrue")
    public Object[][] dataForWagonCompareTestTrue(){
        return new Object[][]{
                {0, Arrays.asList(products.get(1), products.get(2))},
                {0, Arrays.asList(products.get(2))},
                {0, Arrays.asList(products.get(3))},
                {0, Arrays.asList(products.get(4), products.get(5))},
                {0, Arrays.asList(products.get(0), products.get(2))},
                {0, Arrays.asList(products.get(0), products.get(1), products.get(4), products.get(5))}
        };
    }

    @Test(dataProvider = "dataForWagonCompareTestFalse")
    public void wagonCompareTest(List<Product> actualProducts, List<Product> expectedProducts){
        Wagon actualWagon = new Wagon();
        actualWagon.addAll(actualProducts);

        Wagon expectedWagon = new Wagon();
        expectedWagon.addAll(expectedProducts);

        int actual = 0;

        int expected = actualWagon.compareTo(expectedWagon);

        assertNotEquals(actual, expected);
    }
    @DataProvider(name = "dataForWagonCompareTestFalse")
    public Object[][] dataForWagonCompareTestFalse(){
        return new Object[][]{
                {Arrays.asList(products.get(1), products.get(3)), Arrays.asList(products.get(1))},
                {Arrays.asList(products.get(0)), Arrays.asList(products.get(2), products.get(5))},
                {Arrays.asList(products.get(4)), Arrays.asList(products.get(3), products.get(2), products.get(2))},
                {Arrays.asList(products.get(4)), Arrays.asList(products.get(4), products.get(5))},
                {Arrays.asList(products.get(0), products.get(1), products.get(4), products.get(5)), Arrays.asList(products.get(0), products.get(2))},
                {Arrays.asList(products.get(0), products.get(2)), Arrays.asList(products.get(0), products.get(1), products.get(4), products.get(5))}
        };
    }

}