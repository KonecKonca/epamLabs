package com.kozitski.xml.builder;

import com.kozitski.xml.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.kozitski.xml.builder.TariffXsdElement.*;
import static com.kozitski.xml.builder.BuilderUtilConstant.*;

public class StaxXmlBuilder implements XmlBuilder {
    private static final Logger LOGGER = LogManager.getLogger(StaxXmlBuilder.class);

    private List<Tariff> tariffs = new ArrayList<>();
    private XMLInputFactory inputFactory;

    public StaxXmlBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public List<Tariff> buildTariffs(String xmlPath){
        build(xmlPath);

        LOGGER.info("StaxXmlBuilder parser successfully parse xml document");

        return tariffs;
    }

    private void build(String fileName)  {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (TariffXsdElement.valueOf(name.toUpperCase().replace(WHAT_REPLACE, ON_REPLACE)) == TARIFFS) {

                        while (reader.hasNext()){
                            int nextType = reader.next();
                            if(nextType == XMLStreamConstants.START_ELEMENT){

                                name = reader.getLocalName();
                                if (TariffXsdElement.valueOf(name.toUpperCase().replace(WHAT_REPLACE, ON_REPLACE)) == LIMIT_TARIFF) {
                                    Tariff tariff = buildLimitTariff(reader);
                                    tariffs.add(tariff);
                                }
                                else if (TariffXsdElement.valueOf(name.toUpperCase().replace(WHAT_REPLACE, ON_REPLACE)) == UNLIMIT_TARIFF) {
                                    Tariff tariff = buildUnlimitTariff(reader);
                                    tariffs.add(tariff);
                                }

                            }
                        }
                    }

                }
            }
        }
        catch (XMLStreamException e) {
            LOGGER.error("StAX parsing error! ", e);
        }
        catch (FileNotFoundException e) {
            LOGGER.error("File " + fileName + " not found! ", e);
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error("Impossible close file " +fileName+ " : ", e);
            }
        }

    }

    private Tariff buildLimitTariff(XMLStreamReader reader) throws XMLStreamException {
        LimitTariff tariff = new LimitTariff();

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TariffXsdElement.valueOf(name.toUpperCase().replace(WHAT_REPLACE, ON_REPLACE))) {
                        case NAME:
                            tariff.setName(getXMLText(reader));
                            break;
                        case OPERATOR_NAME:
                            name = getXMLText(reader);
                            tariff.setOperatorName(OperatorName.valueOf(name));
                            break;
                        case SMS_PRICE:
                            tariff.setSmsPrice(Double.parseDouble(getXMLText(reader)));
                            break;
                        case PRICE_IN_MINUTE_INNER_CALLS:
                            tariff.setInternalCallPrice(Double.parseDouble(getXMLText(reader)));
                            break;
                        case PRICE_IN_MINUTE_EXTERNAL_CALLS:
                            tariff.setExternalCallPrice(Double.parseDouble(getXMLText(reader)));
                            break;
                        case PRICE_FOR_MB_INTERNET:
                            tariff.setMbPrice(Double.parseDouble(getXMLText(reader)));
                            break;
                        case PARAMETERS:
                            tariff.setTariffParameters(buildParameters(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (TariffXsdElement.valueOf(name.toUpperCase().replace(WHAT_REPLACE, ON_REPLACE)) == LIMIT_TARIFF ||
                            TariffXsdElement.valueOf(name.toUpperCase().replace(WHAT_REPLACE, ON_REPLACE)) == UNLIMIT_TARIFF) {
                        return tariff;
                    }
                    break;
            }
        }

        throw new XMLStreamException("Unknown element in tag (Tariff)");
    }
    private Tariff buildUnlimitTariff(XMLStreamReader reader) throws XMLStreamException {
        UnlimitTariff tariff = new UnlimitTariff();

        String hasInternal = reader.getAttributeValue(0);
        String hasExternal = reader.getAttributeValue(1);
        String hasInternet = reader.getAttributeValue(2);

        tariff.setHasInternalCall(TRUE_VALUE.equalsIgnoreCase(hasInternal));
        tariff.setHasExternalCall(TRUE_VALUE.equalsIgnoreCase(hasExternal));
        tariff.setHasInternet(TRUE_VALUE.equalsIgnoreCase(hasInternet));

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:

                    name = reader.getLocalName();
                    switch (TariffXsdElement.valueOf(name.toUpperCase().replace(WHAT_REPLACE, ON_REPLACE))) {
                        case NAME:
                            tariff.setName(getXMLText(reader));
                            break;
                        case OPERATOR_NAME:
                            name = getXMLText(reader);
                            tariff.setOperatorName(OperatorName.valueOf(name));
                            break;
                        case SMS_PRICE:
                            tariff.setSmsPrice(Double.parseDouble(getXMLText(reader)));
                            break;
                        case IS_INNER_CALLS:
                            tariff.setHasInternalCall(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case IS_EXTERNAL_CALLS:
                            tariff.setHasExternalCall(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case IS_INTERNET:
                            tariff.setHasInternet(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case PAYROLL:
                            tariff.setPayroll(Double.parseDouble(getXMLText(reader)));
                            break;
                        case PARAMETERS:
                            tariff.setTariffParameters(buildParameters(reader));
                            break;
                    }

                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (TariffXsdElement.valueOf(name.toUpperCase().replace(WHAT_REPLACE, ON_REPLACE)) == LIMIT_TARIFF ||
                            TariffXsdElement.valueOf(name.toUpperCase().replace(WHAT_REPLACE, ON_REPLACE)) == UNLIMIT_TARIFF) {
                        return tariff;
                    }
                    break;
            }

        }

        throw new XMLStreamException("Unknown element in tag (Tariff)");
    }
    private TariffParameters buildParameters(XMLStreamReader reader) throws XMLStreamException {
        TariffParameters tariffParameters = new TariffParameters();
        int type;
        String name;

        String number = reader.getAttributeValue(0);
        tariffParameters.setFavouriteNumber(number);

        while(reader.hasNext()){
            type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TariffXsdElement.valueOf(name.toUpperCase().replace(WHAT_REPLACE, ON_REPLACE))){
                        case TARIFFICATION:
                            tariffParameters.setTarifficationType(TarifficationType.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                        case PAY_FOR_CONNECTION:
                            tariffParameters.setPayForConnection(Double.parseDouble(getXMLText(reader)));
                            break;
                        case FAVOURITE_NUMBER:
                            tariffParameters.setFavouriteNumber(getXMLText(reader));
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (TariffXsdElement.valueOf(name.toUpperCase().replace(WHAT_REPLACE, ON_REPLACE)) == PARAMETERS ) {
                        return tariffParameters;
                    }
            }
        }

        throw new XMLStreamException("Unknown element in tag (Tariff)");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {

        String text = null;
        if(reader.hasNext()){
            reader.next();
            text = reader.getText();
        }

        return text;
    }
    public List<Tariff> getTariffs() {
        return new ArrayList<>(tariffs);
    }

}
