package com.kozitski.xml.logic;

import com.kozitski.xml.builder.ParserBuilderType;
import com.kozitski.xml.builder.XmlParserBuilderFactory;
import com.kozitski.xml.command.ParserType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChooseParserReceiver {
    private List<String> parsers;

    public ChooseParserReceiver() {
        parsers = new ArrayList<>(Arrays.asList(ParserType.stringValues()));
    }

    public boolean isParser(String parserName){
        return parsers.contains(parserName.toUpperCase());
    }

}
