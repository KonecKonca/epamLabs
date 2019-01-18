package com.kozitski.xml.time;

import com.kozitski.xml.builder.ParserBuilderType;
import com.kozitski.xml.builder.XmlBuilder;
import com.kozitski.xml.builder.XmlParserBuilderFactory;
import com.kozitski.xml.exception.XMLParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TimeTest {
    private static final String XML_PATH = "src/test/resources/data/Life.xml";

    @Test(dataProvider = "parserTimeTestData")
    public void parserTimeTest(int allowedTime, ParserBuilderType parserBuilderType) throws XMLParseException {
        long start = System.currentTimeMillis();

        XmlBuilder builder = new XmlParserBuilderFactory().createBuilder(parserBuilderType);
        builder.buildTariffs(XML_PATH);

        long end = System.currentTimeMillis();

        boolean actual = true;
        long time = end - start;
        boolean excepted = (time < allowedTime);

        assertEquals(actual, excepted);
    }

    @DataProvider(name = "parserTimeTestData")
    public Object[][] parserTimeTestData(){
        return new Object[][]{
                {500, ParserBuilderType.DOM},
                {500, ParserBuilderType.SAX},
                {500, ParserBuilderType.STAX}

        };
    }

}
