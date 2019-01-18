package com.kozitski.xml.builder;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class XmlBuilderFactoryTest {

    @Test(dataProvider = "dataFabricTest")
    public void fabricTest(Class actual, ParserBuilderType parserBuilderType){
        Class expected = new XmlParserBuilderFactory().createBuilder(parserBuilderType).getClass();

        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataFabricTest")
    public Object[][] dataFabricTest(){
        return new Object[][]{
            {DomXmlBuilder.class, ParserBuilderType.DOM_PARSER_BUILDER},
            {SaxXmlBuilder.class, ParserBuilderType.SAX_PARSER_BUILDER},
            {StaxXmlBuilder.class, ParserBuilderType.STAX_PARSER_BUILDER},
            {DomXmlBuilder.class, ParserBuilderType.DOM},
            {SaxXmlBuilder.class, ParserBuilderType.SAX},
            {StaxXmlBuilder.class, ParserBuilderType.STAX}
        };
    }



}
