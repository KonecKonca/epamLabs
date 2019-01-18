package com.kozitski.task2.service;

import com.kozitski.task2.composite.impl.TextComponent;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.Assert.*;

public class TextComponentCreatorTest {

    @Test
    public void testCreate() {
        TextComponent textComponent = TextComponentCreator.create();
        String expected = textComponent.toString();

        String actual = "\n" +
                "\tIt has survived - not only (five) centuries, but also the leap into 52 electronic typesetting, remaining 0 essentially 9 unchanged. " +
                "It was popularised in the 5 with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like " +
                "Aldus PageMaker including versions of lorem Ipsum. \n" +
                "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. " +
                "The point of using 78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', " +
                "making it look like readable English. \n" +
                "\tIt is a 1202 established fact that a reader will be of a page when looking at its layout. \n" +
                "\tBye. ";


        assertEquals(actual, expected);
    }

}