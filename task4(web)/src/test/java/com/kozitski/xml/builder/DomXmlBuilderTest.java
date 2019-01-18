package com.kozitski.xml.builder;

import com.kozitski.xml.entity.Tariff;
import com.kozitski.xml.exception.XMLParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class DomXmlBuilderTest {

    @Test(dataProvider = "dataBuildTariffsTestTrue")
    public void buildTariffsTestTrue(int actual, String xmlPath) throws XMLParseException {

        XmlBuilder domBuilder = new DomXmlBuilder();
        List<Tariff> tariffs = domBuilder.buildTariffs(xmlPath);
        int expected = tariffs.size();

        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataBuildTariffsTestTrue")
    public Object[][] dataBuildTariffsTestTrue(){
        return new Object[][]{
                {5, "src/test/resources/data/Life.xml"},
                {4, "src/test/resources/data/MTS.xml"},
                {7, "src/test/resources/data/Velcome.xml"}
        };
    }

    @Test(dataProvider = "dataBuildTariffsTestFalse", expectedExceptions = XMLParseException.class)
    public void BuildTariffsTestFalse(int actual, String xmlPath) throws XMLParseException {

        XmlBuilder domBuilder = new DomXmlBuilder();
        List<Tariff> tariffs = domBuilder.buildTariffs(xmlPath);
        int expected = tariffs.size();

        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataBuildTariffsTestFalse")
    public Object[][] dataBuildTariffsTestFalse(){
        return new Object[][]{
                {0, "src/test/resources/data/Life.xmlfe"},
                {0, "src/test/efefresources/data/MTS.xml"},
                {0, "src/test/resources/dataefef/Velcome.xml"}
        };
    }

}