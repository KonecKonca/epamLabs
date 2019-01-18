package com.kozitski.task2.interpreter;

import com.kozitski.task2.service.ExpressionConverter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class InterpreterTest {
    private PolishNotationInterpreter interpreter;
    private InterpreterClient interpreterClient;

    @BeforeClass
    public void init(){
        interpreter = new PolishNotationInterpreter();
        interpreterClient = new InterpreterClient();
    }

    @Test(dataProvider = "dataInterpreterTest")
    public void interpreterTest(int actual, String string){
        List<MathExpression> mathExpressions = interpreter.parse(string);

        int expected = interpreterClient.handleExpression(mathExpressions);

        assertEquals(actual, expected);
    }
    @DataProvider(name = "dataInterpreterTest")
    public Object[][] dataInterpreterTest(){
        return new Object[][]{
                {4, "73 4 | 4 &"},
                {54, "74 4 ^ 7 & 9 *"},
                {0, "34 4 &"},
                {0, "4 4 | 8 &"},
                {77, "73 4 |"},
                {4, "4 4 |"},
        };
    }



}
