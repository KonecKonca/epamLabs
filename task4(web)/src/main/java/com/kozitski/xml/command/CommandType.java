package com.kozitski.xml.command;

import com.kozitski.xml.logic.ChooseParserReceiver;
import com.kozitski.xml.logic.ShowXmlReceiver;

public enum CommandType {
    CHOOSE_PARSER_COMMAND(new ChooseParserCommand(new ChooseParserReceiver())),
    CHOOSE_XML_COMMAND(new ShowXmlCommand(new ShowXmlReceiver()));

    private Command command;

    CommandType(Command command){
        this.command = command;
    }
    public Command getCommand() {
        return command;
    }

}
