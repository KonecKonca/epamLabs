package com.kozitski.task2.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ExpressionConverterTest {

    @Test(dataProvider = "dataForReplaceNumberTest")
    public void testReplaceNumberExpressionsByNumber(String actual, String string){
        ExpressionConverter converter = new ExpressionConverter();
        String expected = converter.replaceExpressionsByNumber(string);

        assertEquals(actual, expected);
    }
    @DataProvider(name = "dataForReplaceNumberTest")
    public Object[][] dataForReplaceNumberTest(){
        return new Object[][]{
                {"erggerggt 16 eg egf egee ", "erggerggt 4<<2 eg egf egee "},
                {"erggerggt 0 eg egf egee ", "erggerggt 6&903>>3 eg egf egee "},
                {"erggerggt 2 eg egf egee ", "erggerggt 2&3^2 eg egf egee "},
                {"erggerggt 160 eg egf egee ", "erggerggt 5<<5 eg egf egee "},
        };
    }

}