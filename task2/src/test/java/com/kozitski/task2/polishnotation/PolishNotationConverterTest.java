package com.kozitski.task2.polishnotation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PolishNotationConverterTest {

    @Test(dataProvider = "dataForCalculatePolishNotationTest")
    public void CalculatePolishNotationTest(String actual, String string){
        PolishNotationConverter converter = new PolishNotationConverter();
        String expected = converter.calculatePolishNotation(string);

        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForCalculatePolishNotationTest")
    public Object[][] dataForSortingByNumOfSentences(){
        return new Object[][]{
                {"1 4 +", "1+4"},
                {"1 8 & 2", "1&8^2"},
                {"5 1 2 & 3 4 1 5 ^ 6 47 & | & 3 | | 2 | & 1 | |", "5|(1&2&(3|(4&(1^5|6&47)|3)|2)|1)"},
                {"7 5 ^ 1 2 2 5 2 >> 71 & | << & | 1200 |", "(7^5|1&2<<(2|5>>2&71))|1200"},
                {"6 ~ 9 3 4 & |", "~6&9|(3&4)"},
                {"3 5 >>", "3>>5"},
        };
    }

}