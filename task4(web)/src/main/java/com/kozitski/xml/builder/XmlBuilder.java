package com.kozitski.xml.builder;

import com.kozitski.xml.entity.Tariff;
import com.kozitski.xml.exception.XMLParseException;

import java.util.List;

public interface XmlBuilder {

    List<Tariff> buildTariffs(String xmlPath) throws XMLParseException;

}
