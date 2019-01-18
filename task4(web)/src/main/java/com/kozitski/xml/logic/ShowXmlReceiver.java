package com.kozitski.xml.logic;

import com.kozitski.xml.builder.ParserBuilderType;
import com.kozitski.xml.builder.XmlParserBuilderFactory;
import com.kozitski.xml.entity.LimitTariff;
import com.kozitski.xml.entity.Tariff;
import com.kozitski.xml.entity.UnlimitTariff;
import com.kozitski.xml.exception.XMLParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ShowXmlReceiver{
    private static Logger LOGGER = LogManager.getLogger(ShowXmlReceiver.class);

    private List<LimitTariff> limitTariffs;
    private List<UnlimitTariff> unlimitTariffs;
    private ParserBuilderType xmlBuilderType = ParserBuilderType.DOM;

    public void init(String parserType, String loadXmlPath) throws XMLParseException {
        chooseBuilder(parserType);

        List<Tariff> list = new XmlParserBuilderFactory().createBuilder(xmlBuilderType).buildTariffs(loadXmlPath);

        unlimitTariffs = new ArrayList<>();
        limitTariffs = new ArrayList<>();

        for(Tariff tariff : list){
            if(tariff instanceof UnlimitTariff){
                unlimitTariffs.add((UnlimitTariff)tariff);
            }
            else {
                limitTariffs.add((LimitTariff)tariff);
            }
        }

        LOGGER.info("Xml files parsed successfully");

    }
    private void chooseBuilder(String parserType){
        xmlBuilderType = ParserBuilderType.valueOf(parserType.toUpperCase());
    }

    public List<LimitTariff> getLimitTariffs() {
        return new ArrayList<>(limitTariffs);
    }
    public List<UnlimitTariff> getUnlimitTariffs() {
        return new ArrayList<>(unlimitTariffs);
    }

}
