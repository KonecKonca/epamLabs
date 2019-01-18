package com.kozitski.xml.command;

public enum ParserType {

    DOM, SAX, STAX;

    public static String[] stringValues(){
        ParserType[] states = values();
        String[] names = new String[states.length];

        for (int i = 0; i < states.length; i++) {
            names[i] = states[i].name();
        }

        return names;
    }

}
