package com.kozitski.task2.time;

import com.kozitski.task2.composite.impl.TextComponent;
import com.kozitski.task2.service.TextComponentCreator;
import com.kozitski.task2.service.TextOperation;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class TimeTest {
    private static final int NUMBER_OF_CREATED_COMPOSITES = 1_000;
    private static final int ALLOWED_TIME = 10_000;

    @Test
    public void createTestCompositeTest(){
        long start = System.currentTimeMillis();
        for(int i = 0; i < NUMBER_OF_CREATED_COMPOSITES; i++){
            TextComponentCreator.create();
        }
        long end = System.currentTimeMillis();

        boolean actual = true;
        long time = end - start;
        boolean excepted = (time < ALLOWED_TIME);

        assertEquals(actual, excepted);
    }

    @Test
    public void sortTestCompositeTest(){
        List<TextComponent> components = new ArrayList<>();
        for(int i = 0; i < NUMBER_OF_CREATED_COMPOSITES; i++){
            components.add(TextComponentCreator.create());
        }

        long start = System.currentTimeMillis();
        for(TextComponent component : components){
            TextOperation.reverseSortLexemesByOrderSymbol(component, "a");
        }
        long end = System.currentTimeMillis();

        boolean actual = true;
        long time = end - start;
        boolean excepted = (time < ALLOWED_TIME);

        assertEquals(actual, excepted);
    }

}
