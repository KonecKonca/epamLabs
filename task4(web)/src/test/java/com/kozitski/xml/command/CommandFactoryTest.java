package com.kozitski.xml.command;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommandFactoryTest {

    @Test(dataProvider = "executeDataTrue")
    public void executeTestTrue(String commandName, Class<?> actual){

        Command command = CommandFactory.chooseCommand(commandName);
        Class<?> expected = command.getClass();
        assertEquals(actual, expected);

    }
    @DataProvider(name = "executeDataTrue")
    public Object[][] executeDataTrue(){
        return new Object[][]{
            {"CHOOSE_PARSER_COMMAND", ChooseParserCommand.class},
            {"CHOOSE_XML_COMMAND", ShowXmlCommand.class}
        };
    }

    @Test(dataProvider = "executeDataFalse")
    public void executeTestFalse(String commandName, Class<?> actual){

        Command command = CommandFactory.chooseCommand(commandName);
        Class<?> expected = command.getClass();
        assertEquals(actual, expected);

    }
    @DataProvider(name = "executeFalse")
    public Object[][] executeDataFalse(){
        return new Object[][]{
                {"CHOOSE_PARSER_COMMAND!!", ChooseParserCommand.class},
                {"CHOOSE_XML_CfeefOMMAND", ChooseParserCommand.class},
                {"CHOOSE_XMFEfefMMAND", ChooseParserCommand.class},
                {"C4654OMMAND", ChooseParserCommand.class},
                {"", ChooseParserCommand.class}
        };
    }

}