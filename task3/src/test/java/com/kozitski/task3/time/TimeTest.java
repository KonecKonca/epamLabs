package com.kozitski.task3.time;

import com.kozitski.task3.entity.LogisticBase;
import com.kozitski.task3.exception.LogisticBaseException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TimeTest {
    private static final long TIME_LOGISTIC_BASE_INIT = 5_000;

    @Test
    public void timeTest(){
        long start = System.currentTimeMillis();
        LogisticBase.getInstance();
        long end = System.currentTimeMillis();

        boolean actual = true;
        long time = end - start;
        boolean excepted = (time < TIME_LOGISTIC_BASE_INIT);

        assertEquals(actual, excepted);
    }

}
