package com.kozitski.xml.builder;

import com.kozitski.xml.entity.*;
import com.kozitski.xml.exception.XMLParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomXmlBuilder implements XmlBuilder {
    private static final Logger LOGGER = LogManager.getLogger(DomXmlBuilder.class);

    @Override
    public List<Tariff> buildTariffs(String xmlPath) throws XMLParseException {

        ArrayList<Tariff> tariffs = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new XMLParseException(e);
        }
        Document document;

        try {
            document = builder.parse(new File(xmlPath));
        } catch (SAXException | IOException e) {
            throw new XMLParseException(e);
        }

        NodeList limitTariffList = document.getElementsByTagName(convertToXmlFormat(TariffXsdElement.LIMIT_TARIFF));
        NodeList unlimitTariffList = document.getElementsByTagName(convertToXmlFormat(TariffXsdElement.UNLIMIT_TARIFF));

        for (int index = 0; index < limitTariffList.getLength(); index++) {
            tariffs.add(buildLimitTariff(limitTariffList.item(index)));
        }

        for (int index = 0; index < unlimitTariffList.getLength(); index++) {
            tariffs.add(buildUnlimitTariff(unlimitTariffList.item(index)));
        }

        LOGGER.info("DOM parser successfully parse xml document");

        return tariffs;
    }

    private LimitTariff buildLimitTariff(Node node) {
        LimitTariff limitTariff = new LimitTariff();
        NodeList limitTariffNodes = node.getChildNodes();
        buildTariff(limitTariff, node);

        int priceInMinuteExternalCallsNumber = 9;
        String priceExtrnal = getElement(limitTariffNodes, priceInMinuteExternalCallsNumber);
        limitTariff.setExternalCallPrice(Double.parseDouble(priceExtrnal));

        int priceInMinuteInnerCallsNumber = 11;
        String priceInner = getElement(limitTariffNodes, priceInMinuteInnerCallsNumber);
        limitTariff.setInternalCallPrice(Double.parseDouble(priceInner));

        int priceForMbInternetNumber = 13;
        String priceInternet = getElement(limitTariffNodes, priceForMbInternetNumber);
        limitTariff.setMbPrice(Double.parseDouble(priceInternet));

        return limitTariff;
    }
    private UnlimitTariff buildUnlimitTariff(Node node) {
        UnlimitTariff unlimitTariff = new UnlimitTariff();
        NodeList unlimitTariffyNodes = node.getChildNodes();
        buildTariff(unlimitTariff, node);

        String attributeIsExternallCalls = getAttribure(node, TariffXsdElement.IS_EXTERNAL_CALLS);
        unlimitTariff.setHasExternalCall(BuilderUtilConstant.TRUE_VALUE.equalsIgnoreCase(attributeIsExternallCalls));

        String attributeInnerCalls = getAttribure(node,TariffXsdElement.IS_INNER_CALLS);
        unlimitTariff.setHasInternalCall(BuilderUtilConstant.TRUE_VALUE.equalsIgnoreCase(attributeInnerCalls));

        String attributeIsInternet = getAttribure(node,TariffXsdElement.IS_INTERNET);
        unlimitTariff.setHasInternet(BuilderUtilConstant.TRUE_VALUE.equalsIgnoreCase(attributeIsInternet));

        int payrollNumber = 9;
        String payroll = getElement(unlimitTariffyNodes, payrollNumber);
        unlimitTariff.setPayroll(Double.parseDouble(payroll));

        return unlimitTariff;
    }
    private void buildTariff(Tariff tariff, Node node) {
        NodeList tariffNodes = node.getChildNodes();

        int nameNumber = 1;
        String name = getElement(tariffNodes, nameNumber);
        tariff.setName(name);

        int operatorNameNumber = 3;
        String operatorName = getElement(tariffNodes, operatorNameNumber);
        tariff.setOperatorName(OperatorName.valueOf(operatorName));

        int smsPriceNumber = 5;
        String smsPrice = getElement(tariffNodes, smsPriceNumber);
        tariff.setSmsPrice(Integer.valueOf(smsPrice));


        int parametersNumber = 7;
        tariff.setTariffParameters(buildParameters(tariffNodes.item(parametersNumber)));

    }
    private TariffParameters buildParameters(Node parametersNode) {
        TariffParameters parameters = new TariffParameters();
        NodeList parametersNodes = parametersNode.getChildNodes();

        String attributeIsExternallCalls = getAttribure(parametersNode, TariffXsdElement.FAVOURITE_NUMBER);
        parameters.setFavouriteNumber(attributeIsExternallCalls);

        int tarificationNumber = 1;
        String tarification = getElement(parametersNodes, tarificationNumber).toUpperCase();
        parameters.setTarifficationType(TarifficationType.valueOf(tarification));

        int payForConnectionNumber = 3;
        String payForConnection = getElement(parametersNodes, payForConnectionNumber);
        parameters.setPayForConnection(Double.parseDouble(payForConnection));

        return parameters;
    }

    private String getElement(NodeList list,int number){
        return list.item(number).getTextContent();
    }
    private String getAttribure(Node node, Enum attribute){
        String xmlAttribute = convertToXmlFormat(attribute);
        return ((Element) node).getAttribute(xmlAttribute);
    }
    private String convertToXmlFormat(Enum element){
        String stringElement = element.toString();
        return stringElement.replace(BuilderUtilConstant.ON_REPLACE,BuilderUtilConstant.WHAT_REPLACE).toLowerCase();
    }

}