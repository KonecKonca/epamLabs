package com.kozitski.task2.service;

import com.kozitski.task2.composite.impl.TextComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TextOperationTest {
    private TextComponent text = TextComponentCreator.create();

    @Test(dataProvider = "dataForSortingByNumOfSentences")
    public void sortParagraphsByNumOFSentencesTest(int position, int expected){
        TextComponent sortedText = TextOperation.sortParagraphsByNumOFSentences(text);
        int actual = sortedText.getComponent(position).getComponentsSize();

        assertEquals(actual, expected);
    }
    @DataProvider(name = "dataForSortingByNumOfSentences")
    public Object[][] dataForSortingByNumOfSentences(){
        return new Object[][]{
                {0, 1},
                {1, 1},
                {2, 2},
                {3, 2}
        };
    }

    @Test
    public void sortByLengthOfWordsTest(){
        TextComponent sortedText = TextOperation.sortByLengthOfWordsText(text);
        String expected = sortedText.toString();
        String actual = "\n" +
                "\t0 9 It 52 has not but the only (five) also leap into survived centuries remaining unchanged electronic typesetting essentially - , , . " +
                "5 It in of of was the the and with more with like Lorem Ipsum Aldus lorem Ipsum sheets release desktop Letraset passages recently software versions " +
                "PageMaker including containing publishing popularised , . \n" +
                "\ta a a It is be by of at the its long fact that will page when reader layout content looking readable distracted established . " +
                "a of 78 is it of as to it The has that here) here' look like point using Ipsum using normal making letters opposed " +
                "(Content content English readable more-or-less distribution , , , . \n" +
                "\ta a a It is be of at its 1202 fact that will page when reader layout looking established . \n" +
                "\tBye . ";

        assertEquals(actual, expected);
    }

    @Test
    public void reverseSortLexemesByOrderSymbolTest(){
        TextComponent sortedText = TextOperation.reverseSortLexemesByOrderSymbol(text, "a");
        String expected = sortedText.toString();
        String actual = "\n" +
                "\talso essentially has leap remaining unchanged. (five) - 0 52 9 but centuries, electronic into It not only survived the typesetting, " +
                "PageMaker passages, Aldus and containing Letraset popularised release software was 5 desktop in including Ipsum Ipsum. " +
                "It like lorem Lorem more of of publishing recently sheets the the versions with with \n" +
                "\treadable a a a at distracted established fact layout. page reader that be by content is It its long looking of the when will " +
                "readable a as has making normal that (Content 78 content distribution English. here', here), Ipsum is it it letters, like look more-or-less of of opposed point The to using using \n" +
                "\ta a a at established fact layout. page reader that 1202 be is It its looking of when will \n" +
                "\tBye. ";

        assertEquals(actual, expected);
    }

}
