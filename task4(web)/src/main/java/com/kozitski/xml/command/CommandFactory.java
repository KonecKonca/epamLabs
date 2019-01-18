package com.kozitski.xml.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CommandFactory {
    private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);

    public static Command chooseCommand(String commandName){
        Command command = CommandType.CHOOSE_PARSER_COMMAND.getCommand();

        if(commandName == null || commandName.isEmpty()){
            return command;
        }

        try {
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
            command = type.getCommand();
        }
        catch (IllegalArgumentException e){
            LOGGER.error("Command is not found");
            return CommandType.CHOOSE_PARSER_COMMAND.getCommand();
        }

        LOGGER.info("Command defined correctly");

        return command;
    }

}
