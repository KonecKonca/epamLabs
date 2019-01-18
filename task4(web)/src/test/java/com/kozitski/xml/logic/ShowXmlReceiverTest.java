package com.kozitski.xml.logic;

import com.kozitski.xml.builder.ParserBuilderType;
import com.kozitski.xml.exception.XMLParseException;
import com.kozitski.xml.validator.SchemeValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ShowXmlReceiverTest {



    @Test(dataProvider = "limitTariffData")
    public void getLimitTariffsWithDomTest(int actual, String xmlPath) throws XMLParseException {
        ShowXmlReceiver showXmlReceiver = new ShowXmlReceiver();
        showXmlReceiver.init(ParserBuilderType.DOM.name() ,xmlPath);

        int expected = showXmlReceiver.getLimitTariffs().size();

        assertEquals(actual, expected);

    }
    @Test(dataProvider = "limitTariffData")
    public void getLimitTariffsWithSaxTest(int actual, String xmlPath) throws XMLParseException {
        ShowXmlReceiver showXmlReceiver = new ShowXmlReceiver();
        showXmlReceiver.init(ParserBuilderType.SAX.name() ,xmlPath);

        int expected = showXmlReceiver.getLimitTariffs().size();

        assertEquals(actual, expected);

    }
    @Test(dataProvider = "limitTariffData")
    public void getLimitTariffsWithStaxTest(int actual, String xmlPath) throws XMLParseException {
        ShowXmlReceiver showXmlReceiver = new ShowXmlReceiver();
        showXmlReceiver.init(ParserBuilderType.STAX.name() ,xmlPath);

        int expected = showXmlReceiver.getLimitTariffs().size();

        assertEquals(actual, expected);

    }
    @DataProvider(name = "limitTariffData")
    public Object[][] limitTariffData(){
        return new Object[][]{
            {3 , "src/test/resources/data/Life.xml"}, // Life
            {2 , "src/test/resources/data/MTS.xml"}, // Mts
            {5 , "src/test/resources/data/Velcome.xml"} // Velcome
        };
    }


    @Test(dataProvider = "unlimitTariffData")
    public void getUnlimitTariffsWithDomTest(int actual, String xmlPath) throws XMLParseException {
        ShowXmlReceiver showXmlReceiver = new ShowXmlReceiver();
        showXmlReceiver.init(ParserBuilderType.DOM.name(),xmlPath);

        int expected = showXmlReceiver.getUnlimitTariffs().size();

        assertEquals(actual, expected);
    }
    @Test(dataProvider = "unlimitTariffData")
    public void getUnlimitTariffsWithSaxTest(int actual, String xmlPath) throws XMLParseException {
        ShowXmlReceiver showXmlReceiver = new ShowXmlReceiver();
        showXmlReceiver.init(ParserBuilderType.SAX.name(),xmlPath);

        int expected = showXmlReceiver.getUnlimitTariffs().size();

        assertEquals(actual, expected);
    }
    @Test(dataProvider = "unlimitTariffData")
    public void getUnlimitTariffsWithStaxTest(int actual, String xmlPath) throws XMLParseException {
        ShowXmlReceiver showXmlReceiver = new ShowXmlReceiver();
        showXmlReceiver.init(ParserBuilderType.STAX.name(),xmlPath);

        int expected = showXmlReceiver.getUnlimitTariffs().size();

        assertEquals(actual, expected);
    }
    @DataProvider(name = "unlimitTariffData")
    public Object[][] unlimitTariffData(){
        return new Object[][]{
                {2 , "src/test/resources/data/Life.xml"}, // Life
                {2 , "src/test/resources/data/MTS.xml"}, // Mts
                {2 , "src/test/resources/data/Velcome.xml"} // Velcome
        };
    }




}